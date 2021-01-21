#!/usr/bin/python3
# Written by Stephen Beckstrand of Ducky Corp

# Import needed Packages
    # Sys is used to evaluate arguments
    # requests is used to evaluate the response headers of the URL provided.
    # BeautifulSoup is used to structure our HTML in a way where it is easy to parse and check if there are links to subpages
    # urlparse -
    # urljoin -

import requests
from bs4 import BeautifulSoup
from urllib.parse import urlparse, urljoin
import sys

def crawl(url, depth, maxdepth, visited):

    # Check if depth is larger than specified maxDepth, if so return to exit out of method.
    if (depth > maxdepth):
        return
    elif (url in visited):
        # print("%s already visisted" % url)
        return
    # Otherwise, if our depth has not yet reached the specified limit, evaluate the URL.
    else:
        try:
            print(("\t" * depth) + url)
            visited.append(url)
            # print(visited)
            # print(("\t" * depth) + url)
            response = requests.get(url)
            if (response.ok == False):
                print(("\t" * depth) + "crawl(%s): %d %s" % (url, response.status_code, response.reason))

            html = BeautifulSoup(response.text, 'html.parser')
            links = html.find_all('a')
            for a in links:
                link = a.get('href')
                if link:
                    absoluteURL = urljoin(url + "/", link.split("#")[0])

                    if absoluteURL.startswith('http'):
                        if (absoluteURL not in visited):
                            crawl(absoluteURL, depth + 1, maxDepth, visited)
        except Exception as e:
            print(("\t" * depth) + "crawl(): %s" % e)
        return




# Check if a url argument was supplied. If not, return an error and exit application
if (len(sys.argv) < 2):
    print("Error: no absolute URL supplied")
    sys.exit(1)
else:
    url = sys.argv[1]


# Check that the URL is valid and that an absolute URL value was provided.
parsed = urlparse(url)
if (parsed.scheme == '' or parsed.netloc == ''):
    print("error: Invalid URL supplied. \nPlease supply an absolute URL to this program")

# Check if maxDepth value provided by user, if not, use default value.
maxDepth = 3
if (len(sys.argv) > 2):
    try:
        maxDepth = int(sys.argv[2])
    except:
        print("Please provide a valid integer value for the maximum depth")
        sys.exit(2)

plural = 's'
if maxDepth == 1:
    plural = ''

visited = []
print("Crawling from %s to a maximum depth of %d link%s" % (url, maxDepth, plural))
crawl(url, 0, maxDepth, visited)
