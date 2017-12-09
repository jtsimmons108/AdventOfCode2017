
data = open('day9.in').read().strip()

score, level, num_garbage, i = [0] * 4
in_garbage = False

while i < len(data):
    c = data[i]
    if in_garbage:
        if c == '!':
            i += 1
        elif c == '>':
            in_garbage = False
        else:
            num_garbage += 1
    else:
        if c == '{':
            level += 1
        elif c == '}':
            score += level
            level -= 1
        elif c == '<':
            in_garbage = True
    i += 1

print('Part 1: {}\nPart 2: {}'.format(score, num_garbage))

