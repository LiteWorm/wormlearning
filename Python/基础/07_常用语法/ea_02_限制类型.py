# 基本类型指定

def test(arg1: str, arg2: int, arg3: float) -> tuple:
    """
    测试指定函数参数和返回值类型
    :param arg1: 指定参数类型为 str
    :param arg2: 指定参数类型为 int
    :param arg3: 指定参数类型为 tuple
    :return: 指定返回值类型为 tuple
    """
    print("arg1:%s, arg2:%d, arg3:%f" % (arg1, arg2, arg3))
    return arg1, arg2, arg3


resule = test("str", 1, 2.0)
print(resule)
print("type:", type(resule))


def test2(arg1: int, arg2: float) -> str:
    """

    :param arg1: 指定入参类型为 int
    :param arg2: 指定入参类型为 float
    :return: 指定返回值类型为 str
             单实际执行是，因python实际为动态执行预研
             最终返回值类型可以为 number类型
    """
    return arg1 + arg2


result = test2(1, 2.0)
print(result, "-----type:", type(result))

print("*" * 50)

from typing import List

Vector = List[float]


def test3(scalar: float, vector: Vector) -> Vector:
    """
    复杂参数类型限定
    :param scalar: 限定第一个入参类型为 `float`
    :param vector: 限定第二个入参类型为自定义的 `Vector`类型
    :return: 限定函数返回值为`Vector`类型
    """
    return [scalar * num for num in vector]


new_scalar = test3(2.0, [1.0, -2.1, 4.7])
print("new_scalar:", new_scalar)
print("type:", type(new_scalar))

print("*" * 50)

from typing import Dict, Tuple, Sequence

# 自定义ConnectionOptions类型，
# 该类型保存的数据全部由 `str:str` 组成的Dict类型
ConnectionOptions = Dict[str, str]
# 自定义Address 类型，
# 该类型保存的数据为一个元组，
# 每个元素都只有两个字段
#   元素的第一个字段类型为str
#   元素的第二个字段类型为int
Address = Tuple[str, int]
# 自定义一个Server类型
# 该类型保存的为一个元组类型的数据
# 元素只有两个字段
#   元素的第一个字段类型为 Address
#   元素的第二个字段类型为 ConnectionOptions
Server = Tuple[Address, ConnectionOptions]


def broadcast_message(message: str, servers: Sequence[Server]) -> None:
    pass


# The static type checker will treat the previous type signature as
# being exactly equivalent to this one.
def broadcast_message(
        message: str,
        servers: Sequence[Tuple[Tuple[str, int], Dict[str, str]]]) -> None:
    pass


print("*" * 50)

from typing import Sequence, TypeVar, Union

T = TypeVar('T')  # Declare type variable


def first(l: Sequence[T]) -> T:  # Generic function
    return l[0]


T = TypeVar('T')  # Can be anything
A = TypeVar('A', str, bytes)  # Must be str or bytes
A = Union[str, None]  # Must be str or None

print("*" * 50)

from typing import NamedTuple


class Employee(NamedTuple):
    name: str
    id: int = 3


employee = Employee('Gaoqi')
assert employee.id == 3
