# 记录所有名片字典列表
card_list = []


def show_menu():
    print("*" * 50)
    print("欢迎使用【名片管理系统】V1.0")
    print("")
    print("1.新增名片")
    print("2.显示全部")
    print("3.查询名片")
    print("0.退出系统")
    print("")
    print("*" * 50)


def new_card():
    """新增名片"""
    print("-" * 50)
    name_str = input("请输入姓名：")
    phone_str = input("请输入电话：")
    qq_str = input("请输入qq号码：")
    email_str = input("请输入邮箱：")

    card_dict = {"name": name_str,
                 "phone": phone_str,
                 "qq": qq_str,
                 "email": email_str}

    card_list.append(card_dict)

    print("%s 名片添加成功！" % name_str)
    print("-" * 50)


def show_card():
    """显示所有名片"""
    print("-" * 50)
    if len(card_list) == 0:
        return
    print("姓名\t\t电话\t\tQQ\t\t邮箱")
    print("=" * 50)
    for card in card_list:
        print("%s\t\t%s\t\t%s\t\t%s" % (card["name"],
                                        card["phone"],
                                        card["qq"],
                                        card["email"]))

    print("-" * 50)


def search_card():
    """查询名片"""
    print("-" * 50)
    name_str = input("请输入要查询人员的姓名")
    for card in card_list:
        if card["name"] == name_str:
            print("姓名\t\t电话\t\tQQ\t\t邮箱")
            print("=" * 50)
            print("%s\t\t%s\t\t%s\t\t%s" % (card["name"],
                                            card["phone"],
                                            card["qq"],
                                            card["email"]))
            print("-" * 50)
            deal_card(card)
            break
    else:
        print("未查询到 %s 的名片信息！" % name_str)


def deal_card(find_dict):

    action_str = input("请选择要执行的操作：1.修改 2.删除 0.返回上级菜单 ")
    if action_str == "1":
        find_dict["name"] = input_card_info(find_dict["name"], "请输入姓名（回车不修改）：")
        find_dict["phone"] = input_card_info(find_dict["phone"], "请输入电话（回车不修改）：")
        find_dict["qq"] = input_card_info(find_dict["qq"], "请输入QQ号码（回车不修改）：")
        find_dict["email"] = input_card_info(find_dict["email"], "请输入邮箱（回车不修改）：")
    elif action_str == "2":
        card_list.remove(find_dict)
        print("删除【%s】名片成功" % find_dict["name"])


def input_card_info(card_info, message):
    """

    :param card_info:
    :param message:
    :return:
    """
    input_info = input(message)
    if len(input_info) > 0:
        return input_info
    else:
        return card_info
