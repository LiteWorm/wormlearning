class A:
    def test(self):
        print("class A")


class B:
    def test(self):
        print("class B")


class C(A, B):
    def testc(self):
        print("class C")


class D(B, A):
    def testd(self):
        print("class D")


c = C()

c.testc()
c.test()
print("*" * 50)
d = D()
d.testd()
d.test()

print("-" * 50)
# 类中查找方法的调用顺序
print(C.__mro__)
print(D.__mro__)

