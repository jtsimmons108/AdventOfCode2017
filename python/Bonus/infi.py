import re
data = open('infi.in').read().strip()
start = [list(map(int, x)) for x in re.findall(r'\[(-*\d+),(-*\d+)\]', data)]
instructions = [list(map(int, x)) for x in re.findall(r'\((-*\d+),(-*\d+)\)', data)]
x1, y1 = start[0]
x2, y2 = start[1]
collisions = []
for i in range(0, len(instructions), 2):
    x1 += instructions[i][0]
    y1 += instructions[i][1]
    x2 += instructions[i + 1][0]
    y2 += instructions[i + 1][1]
    if x1 == x2 and y1 == y2:
        collisions.append((x1, y1))

grid = [[' '] * 50 for _ in range(30)]
for pos in collisions:
    x, y = pos
    grid[y][x] = '#'

print('\n'.join([''.join(row) for row in grid]))

print(len(collisions))