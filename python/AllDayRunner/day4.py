def run():
    inpt = open('day4.in').read().strip()
    data = inpt.splitlines()
    total1, total2 = 0, 0
    for line in data:
        info = line.split()
        info2 = [''.join(sorted(x)) for x in info]
        if len(info) == len(set(info)):
            total1 += 1
        if len(info2) == len(set(info2)):
            total2 += 1
    return total1, total2
