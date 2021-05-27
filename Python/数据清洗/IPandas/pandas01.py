import pandas as pd
import numpy as np
import os
import xlrd

os.chdir('D:\Aspire\git\eadlon\wormlearning\Python\数据清洗\data')

df = pd.read_csv('baby_trade_history.csv')
#print(df.info)
# print(df)

print(df.head(10))

