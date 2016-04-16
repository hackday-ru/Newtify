import web
import json

urls = (
    '/send', 'send'
)

if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()


# Send user found url
class send:
    def POST(self):
        data = web.data()
        json_data = json.loads(data)
        print("POST: " + str(json_data["url"]))
        print("POST: " + str(json_data["user_id"]))
