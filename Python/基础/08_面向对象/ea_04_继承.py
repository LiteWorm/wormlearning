class Animal:
    def __init__(self, name):
        self.name = name

    def run(self):
        print("%s run" % self.name)

    def eat(self):
        print("%s eat" % self.name)

    def drink(self):
        print("%s drink" % self.name)

    def sleep(self):
        print("%s sleep" % self.name)


class Dog(Animal):
    def bark(self):
        print("%s bark" % self.name)


class XiaoTQ(Dog):
    def bark(self):
        super().bark()
        print("bark 方法重写！")


dog = Dog(name="dd")
dog.bark()
dog.drink()
dog.sleep()
dog.eat()
print("&" * 50)
xiaotq = XiaoTQ("ss")
xiaotq.bark()
