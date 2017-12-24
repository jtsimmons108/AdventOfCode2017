def dance(data, instructions):
    for line in instructions:
        if line[0] == 's':
            amt = int(line[1:])
            data = data[-amt:] + data[:-amt]
        elif line[0] == 'x':
            first, second = map(int, line[1:].split("/"))
            data = list(data)
            data[first], data[second] = data[second], data[first]
            data = ''.join(data)
        else:
            first, second =  line[1:].split("/")
            data = data.replace(first, 'x').replace(second, first).replace('x', second)
    return data


inpt = open('day16.in').read().strip().split(",")
data = 'abcdefghijklmnop'
seen = []
while data not in seen:
    seen.append(data)
    data = dance(data, inpt)

print('Part 1: {}'.format(seen[1]))
print('Part 2: {}'.format(seen[1000000000 % len(seen)]))



