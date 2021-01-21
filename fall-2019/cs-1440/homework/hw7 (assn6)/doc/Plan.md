# 1. Requirements

We have been asked to design an application that will crawl a website and look for any links. The application will also allow for specifying a depth so that links within our links are also crawled.


# 2. Design

## Input

Application will not prompt for any input. Instead, the user will need to provide an arguments processed by the application. The first argument will be an absolute URL which the application crawls. The second argument is option but is a specified recursion depth. If the second argumnet is not specified, a default value of 3 is used.

## Output

Application will output the each URL that the crawler has visited. If there are any issues resolving the page, an error will be reported.

# 3. Implementation

## crawler.py
```
- import needed Packages
- define crawl function
  - Check if depth is greater than max depth, if so, exit out of function
  - If url has already been visisted, exit out of function
  - Otherwise, try crawling the URL, get a list of links on the page and for each link, recursively call the crawl function on them. If there are any errors, return them.

- Check the number of arguments provided.
  - If no arguments provided, return message saying a URL is needed
  - If 1 argument is given, check that it is a valid absolute URLs
  - If 2 arguments provided, set the max depth to the second argument provided
- Create an empty set to track visited websites
- Call crawler on provided site.
```
# 4. Verification

### Test Case 1: crawling https://www.stephenbeckstrand.com

#### Input:
```
python3 src/crawler.py https://www.stephenbeckstrand.com
```

### Output: Crawled sites under stephenbeckstrand.com with a recursion level of 3

### Passed?: Yes
