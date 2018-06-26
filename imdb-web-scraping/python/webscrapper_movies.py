from lxml import html

import re
import requests
import time

movies_ids = open("movies_ids.dat").read().splitlines()
print movies_ids
headers = {
    'Accept-Language': 'en-US,en;q=0.9,el;q=0.8',
    'User-agent': 'Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36'
}

for movie_id in movies_ids:
    url = 'https://m.imdb.com/title/%s/fullcredits/cast' % movie_id
    print 'actorId: ', movie_id
    page = requests.get(url, headers=headers)
    tree = html.fromstring(page.content)
    
    actor_ids = tree.xpath('//a[contains(@class, "btn-full subpage")]/@href')
    names = tree.xpath('//div[@class="media-body media-vertical-align"]//h4/text()')
    characters = tree.xpath('//p[@class="h4 unbold"]/text()')

    # Get only the id from the link
    actor_ids = [re.match(r"/name/(.*)/\?.*$", entry).group(1) for entry in actor_ids]


    # Strip newLines
    characters = [entry.replace('\n',' ').strip() for entry in characters]

    for i in range(0, len(actor_ids)):
        print actor_ids[i]
        print names[i]
        print characters[i]
        print ''
    
    print 'END_OF_CONTENT'
    print ''
    print ''

