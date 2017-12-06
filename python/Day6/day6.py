import re, hashlib, itertools


inpt = open('day6.in').read().strip()

data = inpt.split('\t')
new = list(map(int, data))
print(new)
seen = []
total = 0
while new not in seen:
    seen.append(new[:])
    m = max(new)
    p = new.index(m)
    new[p] = 0
    i = (p + 1) % len(new)
    while m > 0:
        new[i] += 1
        i = (i + 1) % len(new)
        m -= 1
    total += 1

print(total, total - seen.index(new[:]))