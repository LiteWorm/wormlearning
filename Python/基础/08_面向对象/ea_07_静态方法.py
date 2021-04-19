class Dog(object):
    # 静态方法
    @staticmethod
    def test():
        print("run")


# 通过类名直接调用静态方法，不需要创建实例对象
Dog.test()

# 也可以使用对象方式调用
dog = Dog()
dog.test()
