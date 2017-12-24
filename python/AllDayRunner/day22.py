from collections import *

inpt = open('day22.in').read().strip()
grid = [[char for char in line] for line in inpt.splitlines()]
total = 0
r, c = len(grid)//2, len(grid[0])//2
directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]
direction = 0
locations = defaultdict(int)

for rx in range(len(grid)):
    for cx in range(len(grid[0])):
        locations[(rx, cx)] = 2 if grid[rx][cx] == '#' else 0

for _ in range(10000000):
    if locations[(r, c)] == 0:
        direction = (direction - 1) % 4
    elif locations[(r,c)] == 1:
        total += 1
    elif locations[(r,c)] == 2:
        direction = (direction + 1) % 4
    else:
        direction = (direction + 2) % 4

    locations[(r,c)] = (locations[(r,c)] + 1) % 4
    dr, dc = directions[direction]
    r += dr
    c += dc

print(total)