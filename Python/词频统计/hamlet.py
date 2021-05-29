# 调用jieba库，前提是已经安装好了这个第三方库，
import jieba
# 调用词云制作库wordcloud
import wordcloud
# 调用imageio库，将使用imread函数
import imageio


# 定义一个方法，读取待分词的文本文件
# open方法用于打开一个文件获得文件对象，read() 方法用于从文件读取指定的字节数，如果未给定参数或为负则读取所有。
def gettext():
    txts = open("./Analysis/hamlet.txt", 'r').read()
    text = txts.lower()  # 将所有文本中的英文全部换为小写字母
    for ch in '!"#$%&()*+,-./:;<=>?@[\\]^_`{|}~':
        text = text.replace(ch, ' ')  # 将文本中的特殊字符替换为空格
    return text


# 建立排除停用词字典，排除掉大多数冠词、代词、连接词等语法型词汇
excludes = {"the", "a", "that", "is", "it", "not", "and", "of", "you", "a", "my", "in", "to"}
########################################################
print('-----------------------------')

# 读取分词文件
hamletTxt = gettext()

# str.split(str="", num=string.count(str)).
# split方法通过指定分隔符对字符串进行切片，返回分割后的字符串列表
# str -- 分隔符，默认情况为所有的空字符，包括空格、换行(\n)、制表符(\t)等。
# num -- 分割次数。默认为 -1, 即分隔所有。
words = hamletTxt.split()

# 定义一个字典对象，存放键值对 “词汇：数量”,"that:1200,i:3000"
counts = {}
for word in words:
    counts[word] = counts.get(word, 0) + 1
# 遍历排除字典，删除待排除词汇
for word in excludes:
    del (counts[word])
# 字典转换成列表，方便后面排序
items = list(counts.items())
# 对一个列表，所有键值对的第2个元素进行排序，按照单词的数量进行排序
items.sort(key=lambda x: x[1], reverse=True)
# 循环遍历输出排序结果，range() 函数返回的是一个可迭代对象
# reverse指排序规则，默认是false是从小到大，True的话就是从大到小
# 注意里面的'key='是用来比较的元素，这是list的sort排序的lambda方法,python高级语法
# range方法返回的是一个可迭代对象
# str.format()，它增强了字符串格式化的功能
for i in range(10):
    word, count = items[i]
    print('{0:<10}{1:>5}'.format(word, count))


################################################
# 导入imageio库中的imread函数，并用这个函数读取本地图片，作为词云形状图片
mk = imageio.imread("./Analysis/alice.png")

# 构建并配置词云对象w，注意要加stopwords集合参数，将不想展示在词云中的词放在stopwords集合里
wc = wordcloud.WordCloud(width=1000,
                         height=700,
                         background_color='black',
                         font_path='msyh',
                         mask=mk,
                         scale=15,
                         stopwords={'and', 'to', 'the', 'of', 'is', 'a', 'that'})
# 对来自外部文件的文本进行中文分词，得到string
f = open('./Analysis/hamlet.txt', encoding='utf-8')
txt = f.read()
# 使用jieba精确分词
txtlist = jieba.lcut(txt)
# join() 方法用于将序列中的元素以指定的字符连接生成一个新的字符串，这里以空格连接
string = " ".join(txtlist)
# 将string变量传入w的generate()方法，给词云输入文字
wc.generate(string)
# 将词云图片导出到当前文件夹
wc.to_file('./Analysis/hamlet-pic.png')
