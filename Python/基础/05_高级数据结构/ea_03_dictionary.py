# 字典

dict_info = {"name": "zhangsan",
             "age": 18,
             "gender": True,
             "height": 1.75
             }
print(dict_info)

# 列出字段对应的所有的key值
print(dict_info.keys())

# 根据指定的key 获取对一个的value值
print(dict_info.get("name"))

dict_info = dict_info.fromkeys(dict_info, 10)
print(dict_info)

for k in dict_info:
    print(k, dict_info[k])

print(len(dict_info))

