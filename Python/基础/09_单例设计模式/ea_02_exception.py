
def test():
    while True:
        try:
            int(input("请输入一个整数："))
            break
        except:
            print("请输入正确的整数 ! ")


# test()


def test2():
    try:
        num = int(input("请输入一个整数"))
        res = 8 / num
    except ZeroDivisionError:
        print("输入的为值为0，请输入一个非0数值")
    except ValueError:
        print("请输入一个正确的整数")
    except Exception as result:
        print("未知错误！%s " % result)


#test2()


def test3():
    try:
        num = int(input("请输入一个整数"))
        res = 8 / num
    except ZeroDivisionError:
        print("输入的为值为0，请输入一个非0数值")
    except ValueError:
        print("请输入一个正确的整数")
    except Exception as result:
        print("未知错误！%s " % result)
    else:
        print("无异常时执行")
    finally:
        print("任何情况下都执行")
    print("-" * 50)

test3()
