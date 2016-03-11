# By Yu Chen Hou
# Run with $ scrapy runspider scraper.py -o items.json

import scrapy

class AstronomyEventsSpider(scrapy.Spider):
    name = 'spider'
    start_urls = map(lambda year: 'http://www.seasky.org/astronomy/astronomy-calendar-'+str(year)+'.html', range(2016,2031))

    def parse(self, response):
        # get year
        year = int(response.css('h1::text').extract()[1].rsplit(None, 1)[-1]) # Last word is year
        for el in response.css('li'):
            yield self.parse_event(el, year)

    def parse_event(self, el, year):
        return {
            'date': self.removePeriod(el.css('.date-text::text').extract()[0]),
            'name': self.removePeriod(el.css('.title-text::text').extract()[0]),
            'description': " ".join(el.css('p::text').extract()[1:]),
            'year': year
        }

    def removePeriod(self, str):
        return str.replace('.', '')
