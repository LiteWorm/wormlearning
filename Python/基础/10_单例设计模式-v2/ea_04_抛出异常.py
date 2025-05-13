def input_passwd():
    pwd = input("请输入密码：")
    if len(pwd) >= 8:
        return pwd
    print("主动抛出异常！")
    exr = Exception("密码长度不足!")
    raise exr


try:
    print(input_passwd())
except Exception as exr:
    print(exr)
