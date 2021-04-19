class Human:
    def __init__(self, name):
        self.name = ""
        # 私有属性,只能在累内部使用
        self.__age = -1

    # 私有方法，只能在类内部调用
    def __security(self):
        print("私有方法")
