## Elastic search prepration

1. Run es instance: `docker run -p 9200:9200 -p 9300:9300 elasticsearch`

2. Prepare test data: `cat hackernews_archive.json | jq -c '.[] | { index: { _index: "news", _type: "mytype" } },. ' >> parsed_data`

3. Create index: `curl -XPOST http://localhost:9200/_bulk --data-binary @/home/normal/dev/newtify/client/spider/dataset/parsed_data`

4. Search service: `http://localhost:8080/search?q=Zedo`

## Run redis

1. `docker run -p 6379:6379 redis`
