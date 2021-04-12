name_list = ["zhangsan", "lisi", "wangwu"]
# 查看某一数据出现的次数
print(name_list.count("zhangsan"))
# 新增/插入数据
name_list.insert(1, "nihao")  # 在指定位置插入一个元素
name_list.append("append")  # 在列表末尾新增一个元素
name_list.extend(["app1", "app2"])  # 在列表末尾增加一批元素
print(name_list)

# 删除元素
name_list.remove("nihao")  # 从列表中删除指定元素
name_list.pop(3)  # 从列表中删除指定索引的元素
# name_list.clear()  # 清空list
print(name_list)


# 排序
name_list.reverse()  # 翻转字符串
print(name_list)
name_list.sort()  # 升序
print(name_list)
name_list.sort(reverse=True)
print(name_list)  # 降序

# 寻找指定字符串的索引
print(name_list.index("zhangsan"))

# 遍历list
for name in name_list:
    print(name)
