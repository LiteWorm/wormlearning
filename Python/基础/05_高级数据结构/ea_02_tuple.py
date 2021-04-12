# 元组：不支持修改

user_tuple = ("zhangsan", 18, True)
print(user_tuple)


print("name: %s , age %d, man %d" % user_tuple)

print_info = "name: %s , age %d, man %d" % user_tuple
print(print_info)