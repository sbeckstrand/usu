# Crawler user manual

### description

This program is designed to crawl a specified website for any links and output visited sites to the console. With this application you can also specify a depth to check links so if you have a depth of 3, it will check links under links up to 3 levels deep.

### Usage

To launch the application, you call the crawler driver file directly at src/crawl.py. When you use the application you are required to provide an initial site with an absolute URL. You can also specify the recursion depth, though this is optional. If a depth is not provided, a default value of 3 is used instead.

Example:

```
:> python3 src/crawler.py https://google.com 3
```
