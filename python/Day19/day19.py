import re, hashlib, itertools
from collections import *

inpt = open('day19.in').read()
grid = [[char for char in line] for line in inpt.splitlines()]
row, column = 0, grid[0].index('|')
direction = 'south'
moves = {'south': (1, 0), 'east':(0,1), 'north':(-1, 0), 'west':(0, -1)}
encountered = ''
steps = 0

while grid[row][column] != ' ':
    steps += 1
    if grid[row][column].isalpha():
        encountered += grid[row][column]
    elif grid[row][column] == '+':
        if direction in ['south', 'north']:
            if grid[row][column + 1] != ' ':
                direction = 'east'
            else:
                direction = 'west'
        else:
            if grid[row + 1][column] != ' ':
                direction = 'south'
            else:
                direction = 'north'

    dr, dc = moves[direction]
    row += dr
    column += dc


print('Part 1: {}'.format(encountered))
print('Part 2: {}'.format(steps))






