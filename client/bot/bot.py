import sys
import telepot
import json
import re
import requests
from telepot.delegate import per_chat_id, create_open
from goose import Goose

"""
$ python2.7 bot.py <token>
Receives urls from user and sends parsed article items to Main Server.
"""


class MessageExtractArticle(telepot.helper.ChatHandler):
    def __init__(self, seed_tuple, timeout):
        super(MessageExtractArticle, self).__init__(seed_tuple, timeout)

    def on_chat_message(self, initial_msg):
        message = initial_msg['text']

        urls = re.findall('http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\(\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+', message)

        if not urls:
            # self.sender.sendMessage("Please, provide a valid url.")
            server_url = 'http://localhost:8080/search'
            payload = {'q': message}
            response = requests.get(server_url, params=payload)
            print("Response: " + str(response))

            responseUrls = response.text.split(',')[:3]

            for responseUrl in responseUrls:
                self.sender.sendMessage(responseUrl)
            return

        json_items = []
        for url in urls:
            article = Goose().extract(url)
            item = {"text": article.cleaned_text, "link_title": article.title, "url": url,
                    "user_id": initial_msg['chat']['id']}
            json_data = json.dumps(item)
            json_items.append(json_data)

            print("Received url:" + str(item["url"]))
            print("Parsed title:" + item["link_title"].encode('utf-8'))
            print("Parsed json:" + json_data.encode('utf-8'))

            server_url = 'http://localhost:8080/add/article'
            r = requests.post(server_url, data=json_data, headers={"content-type": "application/json"})
            print("Response: " + str(r))

            self.sender.sendMessage(item["link_title"])
            self.sender.sendMessage('Got it!')


TOKEN = sys.argv[1]  # get token from command-line

bot = telepot.DelegatorBot(TOKEN, [
    (per_chat_id(), create_open(MessageExtractArticle, timeout=10)),
])
bot.notifyOnMessage(run_forever=True)
