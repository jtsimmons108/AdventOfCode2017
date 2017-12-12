import re

info = re.compile(r'(\d+) <-> (.+)')


links = {}

for line in open('day12.in'):
    reg, connected = info.findall(line)[0]
    links[int(reg)] = set(map(int, connected.split(", ")))

total = 0
groups = []
for key in links.keys():
    connected = links[key]
    prev_len = 0
    while len(connected) != prev_len:
        prev_len = len(connected)
        for prog in set(connected):
            connected |= links[prog]
    if connected not in groups:
        groups.append(connected)
    if 0 in connected:
        total += 1

print(total)
print(len(groups))
