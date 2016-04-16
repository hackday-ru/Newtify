#!/usr/bin/env bash
curl -XPOST http://localhost:9200/_bulk --data-binary @sample
