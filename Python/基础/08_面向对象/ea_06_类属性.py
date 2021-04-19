class Tools:
    # 类属性
    count = 0

    def __init__(self, name):
        # 实例属性
        self.name = name
        Tools.count += 1

    # 类方法
    @classmethod
    def test(cls):
        print("创建类方法")
        print("Tools 创建实例数：%d " % cls.count)

tool1 = Tools("A")
tool2 = Tools("B")

print(Tools.count)
print(tool2.count)
print(tool1.count)

print("*" * 50)

tool1.count = 20
print(tool1.count)
print(Tools.count)

print("*" * 50)

Tools.test()
tool1.test()
tool2.test()
