import re, itertools, hashlib, time

def run():
    inpt = open('day5.in').read().strip()
    results = [0,0]
    for part in (0, 1):
        total = 0
        i = 0
        data = list(map(int, inpt.strip().splitlines()))
        while 0 <= i < len(data):
            next = i + data[i]
            if part == 0:
                data[i] += 1
            else:
                if data[i] >= 3:
                    data[i] -= 1
                else:
                    data[i] += 1
            i = next
            total += 1
        results[part] = total
    return results[0], results[1]



