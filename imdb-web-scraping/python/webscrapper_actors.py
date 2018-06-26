from lxml import html

import re
import requests
import time

actors_ids = open("actors_ids.dat").read().splitlines()
headers = {
    'Accept-Language': 'en-US,en;q=0.9,el;q=0.8',
    'User-agent': 'Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36'
}

for actor_details in actors_ids:
    actor_details = actor_details.split(",")
    url = 'https://m.imdb.com/name/%s/filmotype/%s' % (actor_details[0], actor_details[1])
    page = requests.get(url, headers=headers)
    tree = html.fromstring(page.content)
    
    title_id = tree.xpath('//a[contains(@class, "btn-full subpage")]/@href')
    title = tree.xpath('//span[@class="h3"]/text()')
    type_and_release_year = tree.xpath('//div[@class="unbold"]/text()')
    character = tree.xpath('//p[@class="h4 unbold"]/text()')

    # Get only the id from the link
    title_id = [re.match(r"/title/(.*)/\?.*$", entry).group(1) for entry in title_id]

    # Strip newLines
    character = [entry.replace('\n',' ').strip() for entry in character]

    # Check if type_and_release_year is empty on some entries
    len_diff = len(title_id) - len(type_and_release_year)
    if len_diff != 0:
       for i in range(0, len_diff):
            type_and_release_year.insert(i, "empty")

    for i in range(0, len(title_id)):
        print title_id[i]
        print title[i]
        print type_and_release_year[i]
        print character[i]
        print ''
    
    print 'END_OF_CONTENT'
    print ''
    print ''

