import numpy as np
import pandas as pd
import os
import matplotlib.pyplot as plt


# 数据读取
os.chdir('./data')
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

print(wtusage.head(10))
print(wtusage.info())

# 数据拆分
# 2018年数据
# A厂
A_2018 = wtusage.where(wtusage['date'] < pd.to_datetime('2019-01-01', format='%Y-%m-%d', errors='coerce'))
A_2018 = A_2018[~(A_2018['date'].isnull())]
del A_2018['B_Factory']
print(A_2018.head(10))
print(A_2018.info())

# B厂
B_2018 = wtusage.where(wtusage['date'] < pd.to_datetime('2019-01-01', format='%Y-%m-%d', errors='coerce'))
B_2018 = B_2018[~(B_2018['date'].isnull())]
del B_2018['A_Factory']
print(B_2018.head(10))
print(B_2018.info())


# 2019年数据
# A厂
A_2019 = wtusage.where((wtusage['date'] < pd.to_datetime('2020-01-01', format='%Y-%m-%d', errors='coerce')) &
                       (wtusage['date'] >= pd.to_datetime('2019-01-01', format='%Y-%m-%d', errors='coerce')))
A_2019 = A_2019[~(A_2019['date'].isnull())]
del A_2019['B_Factory']
print(A_2019.head(10))
print(A_2019.info())

# B厂
B_2019 = wtusage.where((pd.to_datetime('2020-01-01', format='%Y-%m-%d', errors='coerce') > wtusage['date']) &
                       (wtusage['date'] >= pd.to_datetime('2019-01-01', format='%Y-%m-%d', errors='coerce')))
B_2019 = B_2019[~(B_2019['date'].isnull())]
del B_2019['A_Factory']
print(B_2019.head(10))
print(B_2019.info())


# 2020年数据
# A厂
A_2020 = wtusage.where(wtusage['date'] >= pd.to_datetime('2020-01-01', format='%Y-%m-%d', errors='coerce'))
A_2020 = A_2020[~(A_2020['date'].isnull())]
del A_2020['B_Factory']
print(A_2020.head(10))
print(A_2020.info())

# B厂
B_2020 = wtusage.where(wtusage['date'] >= pd.to_datetime('2020-01-01', format='%Y-%m-%d', errors='coerce'))
B_2020 = B_2020[~(B_2020['date'].isnull())]
del B_2020['A_Factory']
print(B_2020.head(10))
print(B_2020.info())

pltdata = np.array(A_2018)
pltdataB = np.array(A_2018)
print(pltdata)

plt.rcParams['font.sans-serif'] = 'simhei'
plt.rcParams['axes.unicode_minus'] = False
# plt.pie(pltdata[:, 1], data=pltdata[:, 0], autopct="%.1ff%%")
# plt.legend(pltdata[:, 0], loc="upper left")
# plt.show()


# plt.plot(pltdata[:, 0], pltdata[:, 1],   '-*')
# plt.xlabel("用水量")
# plt.show()


pltwtdata = np.array(wtdata)
print(pltwtdata)
plt.plot(pltwtdata[:, 2], pltwtdata[:, 1], '-*')
plt.plot(pltwtdata[:, 2], pltwtdata[:, 0], '-.')
plt.xlabel("用水量")
plt.show()
