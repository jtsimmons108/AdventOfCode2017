import re, itertools, hashlib, time


inpt = open('day5.in').read().strip()

data = list(map(int, inpt.strip().splitlines()))
total = 0
i = 0
start = time.time()
while 0 <= i < len(data):
    next = i + data[i]
    if data[i] >= 3:
        data[i] -= 1
    else:
        data[i] += 1
    i = next
    total += 1


print(time.time() - start)
print(total)