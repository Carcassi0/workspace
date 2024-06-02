import requests
import json
import pandas as pd
import os
import concurrent.futures
import time
from datetime import datetime,timedelta
from pyproj import Proj, transform

latestday = datetime.today() - timedelta(days=2)
latestday_str = latestday.strftime('%Y%m%d')
fourteen_days_ago = latestday - timedelta(days=11)
fourteen_days_ago_str = fourteen_days_ago.strftime('%Y%m%d')
today = datetime.today()
today_str = today.strftime('%Y%m%d')

def fetch_data_from_api(auth_key, base_url, lastModTsBgn, lastModTsEnd, result_type, page_index, page_size):
    url = f"{base_url}?authKey={auth_key}&pageIndex={page_index}&pageSize={page_size}&lastModTsBgn={lastModTsBgn}&lastModTsEnd={lastModTsEnd}&resultType={result_type}"
    response = requests.get(url)
    if response.status_code != 200:
        print(f"Failed to fetch data from API (Status code: {response.status_code})")
        return None
    return json.loads(response.text)

def save_data_to_csv(dataframe, file_path):
    dataframe.to_csv(file_path, encoding='utf-8-sig', index=False)

def process_page(page_data):
    filtered_data = []
    body = page_data['result']['body']['rows'][0]['row']
    for data in body:
        if data.get('opnSfTeamCode') == 3740000 and (data.get('trdStateNm') == '휴업' or data.get('trdStateNm') == '폐업'):
            filtered_data.append(data)
    return filtered_data

def collect_data(auth_key, base_url, lastModTsBgn, lastModTsEnd, result_type, page_size, num_pages_to_fetch):
    with concurrent.futures.ThreadPoolExecutor() as executor:
        futures = []
        for page_index in range(1, num_pages_to_fetch + 1):
            future = executor.submit(fetch_data_from_api, auth_key, base_url, lastModTsBgn, lastModTsEnd, result_type, page_index, page_size)
            futures.append(future)
        filtered_dataset = []
        for future in concurrent.futures.as_completed(futures):
            response_data = future.result()
            if response_data is not None:
                filtered_dataset.extend(process_page(response_data))
    return filtered_dataset


def get_total_pages(auth_key, base_url, lastModTsBgn, lastModTsEnd, result_type, page_size):
    url = f"{base_url}?authKey={auth_key}&pageIndex=1&pageSize={page_size}&lastModTsBgn={lastModTsBgn}&lastModTsEnd={lastModTsEnd}&resultType={result_type}"
    response = requests.get(url)
    if response.status_code != 200:
        print(f"API에서 데이터를 가져오지 못했습니다 (상태 코드: {response.status_code})")
        return None
    
    data = json.loads(response.text)

    if 'paging' not in data['result']['header']:   #'header' not in data['result'] or 
        print("응답에 예상된 페이지네이션 정보가 포함되어 있지 않습니다.")
        return None
    
    total_count = data['result']['header']['paging']['totalCount']
    total_pages = total_count // page_size
    if total_count % page_size > 0:
        total_pages += 1
    
    return total_pages

def katec_to_wgs84(x, y):
    # 중부원점TM(EPSG:5174) 좌표계와 WGS84 좌표계를 정의.
    utm = Proj(init='epsg:5174')
    wgs84 = Proj(init='epsg:4326')  # WGS84 좌표계의 EPSG 코드는 4326.

    # 중부원점TM 좌표를 WGS84로 변환.
    lon, lat = transform(utm, wgs84, x, y)

    return lon, lat


# num_pages_to_fetch = get_total_pages(auth_key, base_url, lastModTsBgn, lastModTsEnd, result_type, page_size)

auth_key = "iBVlPR96oq==3amKOhkX6lLUKMQAev32b6SbRYM6iuQ="
base_url = "http://www.localdata.go.kr/platform/rest/TO0/openDataApi"
lastModTsBgn = fourteen_days_ago_str
lastModTsEnd = latestday_str
page_size = 500 # 최대 500
result_type = "json"
output_directory = "~/workspace"
output_file_path = os.path.join(output_directory, today_str+".csv")
num_pages_to_fetch = get_total_pages(auth_key, base_url, lastModTsBgn, lastModTsEnd, result_type, page_size)

# 데이터 수집
refiltered_dataset = collect_data(auth_key, base_url, lastModTsBgn, lastModTsEnd, result_type, page_size, num_pages_to_fetch)

# 데이터프레임으로 변환
keys_to_extract = ['trdStateNm', 'dcbYmd', 'bplcNm', 'x', 'y', 'rdnWhlAddr']
extracted_values = {key: [] for key in keys_to_extract}
for data in refiltered_dataset:
    for key in keys_to_extract:
        extracted_values[key].append(data.get(key))
extracted_df = pd.DataFrame(extracted_values)

new_columns = ['영업상태명', '폐업일자', '사업장명', '좌표정보(x)', '좌표정보(y)', '도로명전체주소']
extracted_df.columns = new_columns


## 중요!!!! 판다스 라이브러리의 dropna는 문자열 값에는 작동하지 않음! 아마 나머지도?
# 좌표 결측치 제거
# 각 열을 순회하면서 빈 문자열을 가진 행을 삭제
for column in extracted_df.columns:
    extracted_df = extracted_df[extracted_df[column] != '']

# 인덱스 리셋
extracted_df.reset_index(drop=True, inplace=True)

# 좌표값을 숫자로 변환
extracted_df[['좌표정보(x)', '좌표정보(y)']] = extracted_df[['좌표정보(x)', '좌표정보(y)']].astype(float)

# 변환 함수 적용 및 업데이트
extracted_df[['좌표정보(x)', '좌표정보(y)']] = extracted_df.apply(lambda row: pd.Series(katec_to_wgs84(row['좌표정보(x)'], row['좌표정보(y)'])), axis=1)

# # 좌표 변환
# extracted_df[['좌표정보(x)', '좌표정보(y)']] = extracted_df.apply(lambda row: pd.Series(katec_to_wgs84(row['좌표정보(x)'], row['좌표정보(y)'])), axis=1)

# CSV 파일로 저장
save_data_to_csv(extracted_df, output_file_path)
print("CSV file saved to:", output_file_path)

