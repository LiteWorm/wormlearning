def print_info(name: str, gender: bool = True):
    """

    :param name: 姓名
    :param gender: 性别：
                    True：男
                    False： 女
    """

    gender_text = "男生"

    if not gender:
        gender_text = "女生"
    print("%s 是 %s " % (name, gender_text))


print_info("xiaoming")

