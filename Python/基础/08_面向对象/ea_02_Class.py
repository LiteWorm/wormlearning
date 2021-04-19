class TestClass:
    def __init__(self, name: str):
        """
        自定义初始化方法
        :param name: 姓名
        """

        # 创建属性并初始化
        self.name = name
        print(self.name)

    def test(self, name: str):
        print(name)

    def __str__(self):
        """
        在执行print时默认调用该方法
        :return:
        """
        return "测试strin返回"

    def __del__(self):
        """
        在对象销毁时执行该方法
        """
        print(self.name, "3333")


test_class2 = TestClass("test02")

print(test_class2)

print("*" * 50)
test_class2.__del__()
print("-" * 50)

print(test_class2.__doc__)

