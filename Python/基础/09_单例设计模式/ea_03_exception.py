def demo1():
    return int(input("请输入一个整数："))


def demo2():
    return demo1()


try:
    demo2()
except ZeroDivisionError:
    print("请输入一个非0的整数！")
except ValueError:
    print("请输入一个整数")
except Exception as es:
    print(es)
finally:
    print("异常捕获完成")
