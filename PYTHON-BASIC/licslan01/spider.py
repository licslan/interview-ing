from urllib import request


# 爬虫编写
class Spider:
    # url = 'https://www.licslan.com'
    url = 'https://www.licslan.com'

    @staticmethod
    def __fetch_content():
        r = request.urlopen(Spider.url)
        # bytes ---> string
        html = r.read()
        html = str(html, encoding='utf-8')
        print(html)

    def __analysis(self, html):
        pass

    def go(self):
        self.__analysis(self.__fetch_content())


spider = Spider()
spider.go()
