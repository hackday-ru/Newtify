import sys
import telepot
import json
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
        url = initial_msg['text']

        article = Goose().extract(url)
        item = {"text": article.cleaned_text, "link_title": article.title, "url": url,
                "user_id": initial_msg['chat']['id']}
        json_data = json.dumps(item)

        print("Received url:" + str(item["url"]))
        print("Parsed title:" + item["link_title"].encode('utf-8'))
        print("Parsed json:" + json_data.encode('utf-8'))

        self.sender.sendMessage(item["link_title"])

        # TODO: send an item to Main Server


TOKEN = sys.argv[1]  # get token from command-line

bot = telepot.DelegatorBot(TOKEN, [
    (per_chat_id(), create_open(MessageExtractArticle, timeout=10)),
])
bot.notifyOnMessage(run_forever=True)
