import time
target = 361527


direction = 0
places = [(0, 0)]
i = 1
step_length = 1
step = 0
stepped_once = False
values = {(0,0):1}
found = False
part2 = 0
while i < target:

    x, y = places[-1]

    if direction == 0:
        places.append((x + 1, y))
    elif direction == 1:
        places.append((x, y + 1))
    elif direction == 2:
        places.append((x - 1, y))
    else:
        places.append((x, y - 1))

    #Part 2
    new_value = sum([values[(x + a, y + b)] for a in [-1,0,1] for b in [-1,0,1] if (x + a, y + b) in values])
    values[(x,y)] = new_value
    if not found and new_value > 361527:
        part2 = new_value
        found = True

    step += 1
    i += 1
    if step == step_length:
        if stepped_once:
            step_length += 1
            stepped_once = False
        else:
            stepped_once = True
        direction = (direction + 1) % 4
        step = 0

print('Part1:', sum(map(abs, places[-1])))
print('Part2:', part2)