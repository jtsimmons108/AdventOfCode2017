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
i = 0
while data != 'abcdefghijklmnop' or i == 0:
    data = dance(data, inpt)
    if i == 0:
        print('Part 1: {}'.format(data))
    i += 1

extra = 1000000000 % i
for _ in range(extra):
    data = dance(data, inpt)

print('Part 2: {}'.format(data))



