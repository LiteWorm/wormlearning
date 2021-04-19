class Player(object):
    # 定义类属性
    instance = None
    # 是否执行过初始化动作
    init_flag = False

    def __init__(self, name):
        if Player.init_flag:
            return
        self.name = name
        Player.init_flag = True
        print("【初始化】%s 播放器的地址为：%d" % (self.name, id(self)))

    # 创建对象时，__new__方法会被自动调用
    def __new__(cls, *args, **kwargs):
        if cls.instance is None:
            cls.instance = super().__new__(cls)
        return cls.instance

    def __str__(self):
        return "%s 播放器的地址为：%d" % (self.name, id(self))


player = Player("播放器")
print(player)

player2 = Player("播放器2")
print(player2)
