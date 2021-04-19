def test_num_qute(num):
    """验证数字的引用"""
    print("num = %d" % num)
    num += 10
    print("num = %d" % num)


gl_num = 10

print("gl_num = %d " % gl_num)
test_num_qute(gl_num)
print("gl_num = %d " % gl_num)


def test_str_qute(string):
    """
    验证函数内部直接修改字符串值
    函数执行完成后，原始字符串值不变

    :param string:
    """
    print("string = %s " % string)
    string = "edited string"
    print("string = %s " % string)


gl_string = "test string"

print("gl_string = %s " % gl_string)
test_str_qute(gl_string)
print("gl_string = %s " % gl_string)


def test_str_qute2(string):
    """
    验证函数内部对字符串进行+=操作
    函数执行完成后，原始字符串值不变

    :param string:
    """
    print("string = %s " % string)
    string += string
    print("string = %s " % string)


gl_string = "test += string "
print("gl_string = %s " % gl_string)
test_str_qute2(gl_string)
print("gl_string = %s " % gl_string)


def test_tuple_qute(ttuple):
    """
    测试在函数内部修改元组值，
    只在函数内部有效，
    函数执行完成后，原变量值不变
    :param ttuple:
    """
    print("tuple:", ttuple)
    ttuple = (1, 32, "zs")
    print("tuple:", ttuple)


gl_tuple = ('a', "b", 3, {"1": 2, "4": 4})

print("gl_tuple:", gl_tuple)
test_tuple_qute(gl_tuple)
print("gl_tuple:", gl_tuple)


def test_list_qute(t_list):
    """
    对list在函数内部使用赋值操作
    函数执行完成后，变量值不变
    :param t_list:
    """
    print("list:", t_list)
    t_list = [1, 2, 3, 4]
    print("list:", t_list)


gl_list = ["a", "b", "c"]
print("gl_list:", gl_list)
test_list_qute(gl_list)
print("gl_list:", gl_list)


def test_list_qute2(t_list):
    """
    对list在函数内部执行 "+" 操作
    函数执行完成后，变量值不变
    :param t_list:
    """
    print("list:", t_list)
    t_list = t_list + t_list
    print("list:", t_list)


gl_list = ["a", "b", "c"]
print("gl_list:", gl_list)
test_list_qute2(gl_list)
print("gl_list:", gl_list)


def test_list_qute3(t_list):
    """
    在函数内部对变量进行"+="操作，
    相当于执行list的extend方法；
    函数运行结束后，list的值变为新的内容（地址不变）
    :param t_list:
    """
    print("list:", t_list)
    t_list += t_list
    print("list:", t_list)


gl_list = ["a", "b", "c"]
print("gl_list:", gl_list)
test_list_qute3(gl_list)
print("gl_list:", gl_list)


def test_list_qute4(t_list):
    """
    在函数内部调用list的extend方法对list数据进行操作
    函数执行完成后，list中保存的数据为操作后的数据
    :param t_list:
    """
    print("list:", t_list)
    t_list.extend(t_list)
    print("list:", t_list)


gl_list = ["a", "b", "c"]
print("gl_list:", gl_list)
test_list_qute4(gl_list)
print("gl_list:", gl_list)


def test_dict_qute(t_dict):
    """
    在函数内对字典类型数据进行赋值操作
    函数执行完成后，入参的值不变
    :param t_dict:
    """
    print("dict:", t_dict)
    t_dict = {"zs": 1, "ls": 2}
    print("dict:", t_dict)


gl_dict = {"ww": 3, "er": 4}
print("gl_dict:", gl_dict)
test_dict_qute(gl_dict)
print("gl_dict:", gl_dict)


def test_dict_qute2(t_dict):
    print("dict:", t_dict)
    t_dict.clear()
    print("dict:", t_dict)


gl_dict = {"ww": 3, "er": 4}
print("gl_dict:", gl_dict)
test_dict_qute2(gl_dict)
print("gl_dict:", gl_dict)
