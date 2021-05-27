import null as null
import pandas as pd
import os

# 数据读取
os.chdir('D:\Aspire\git\eadlon\wormlearning\Python\数据清洗\data')
wtdata = pd.read_csv('train.csv')
holydata = pd.read_csv('holy_data.txt')

# 重命名列明，方便后续编程处理
wtdata.rename(columns={'A厂': "A_Factory", 'B厂': 'B_Factory'}, inplace=True)
holydata.rename(columns={'假期长度': 'holy_len', '第几天': 'holy_day', '假期类型': 'holy_type'}, inplace=True)

# 将日期格式从字符串转换为日期格式
wtdata['date'] = pd.to_datetime(wtdata['日期'], format='%Y/%m/%d', errors='coerce')
holydata['date'] = pd.to_datetime(holydata['日期'], format='%Y-%m-%d', errors='coerce')

# 将假期类型由汉字转换为数值
holydata.loc[holydata['holy_type'] == '调休', 'holy_type'] = '0'
holydata.loc[holydata['holy_type'] == '春节', 'holy_type'] = '1'
holydata.loc[holydata['holy_type'] == '元旦', 'holy_type'] = '2'
holydata.loc[holydata['holy_type'] == '清明', 'holy_type'] = '3'
holydata.loc[holydata['holy_type'] == '五一', 'holy_type'] = '4'
holydata.loc[holydata['holy_type'] == '端午', 'holy_type'] = '5'
holydata.loc[holydata['holy_type'] == '中秋', 'holy_type'] = '6'
holydata.loc[holydata['holy_type'] == '国庆', 'holy_type'] = '7'

# 加假期类型由字符串转换为int
holydata['holy_type'] = holydata['holy_type'].astype(int, copy=True, errors='raise')

# 删除多余列
wtdata.drop('日期', axis=1, inplace=True)
holydata.drop('日期', axis=1, inplace=True)

print(wtdata.head(10))
print(wtdata.info())
print(holydata.head(10))
print(holydata.info())

# 将假期数据合并到用水数据中
wtusage = pd.merge(left=wtdata, right=holydata, how='left', left_on='date', right_on='date')

# 数据列重排
date = wtusage['date']
del wtusage['date']
wtusage.insert(0, 'date', date)

# 数字空值处理
wtusage.loc[wtusage['holy_len'].isnull(), 'holy_len'] = 0
wtusage.loc[wtusage['holy_day'].isnull(), 'holy_day'] = 0
wtusage.loc[wtusage['holy_type'].isnull(), 'holy_type'] = 0

print(wtusage)
print(wtusage.info())
