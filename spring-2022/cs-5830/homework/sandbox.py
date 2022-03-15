# Wikipedia has what they label as their vital articles which are intended to cover what are considered the most important articles.
# Vital articles are broken up into multiple levels (level 1 - 5). First four levels are captured below. 
# Admittedly, the mediawiki API was not used to pull these as they are not stored in their wiki. Instead, they were taken directly from 
# wikipedia's site using PetScan (https://petscan.wmflabs.org/)

level1 = [9228, 18393, 18831, 26700, 29816, 37235, 435268, 682482, 13692155, 29560452]







S = requests.Session()

URL = "https://en.wikipedia.org/w/api.php"

level1String = "9228|18393|18831|26700|29816|37235|435268|682482|13692155|29560452"
level1Data = []


PARAMS = {
    "action": "query",
    "format": "json",
    "pageids": i,
    "prop": "contributors|info",
    "inprop": "watchers",
}
R = S.get(url=URL, params=PARAMS)
level1Data.append(R.json())


print(level1Data)