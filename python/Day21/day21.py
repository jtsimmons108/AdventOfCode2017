import re, hashlib, itertools, numpy as np
from collections import *


def array_to_pattern_string(nparray):
    return '/'.join([''.join(row) for row in nparray])


def get_variations(ip):
    ip = [[c for c in row] for row in ip.split('/')]
    array = np.asarray(ip)
    variations = set()
    for rot in range(4):
        variations.update([array_to_pattern_string(np.rot90(array, rot)),
                           array_to_pattern_string(np.fliplr(np.rot90(array, rot))),
                           array_to_pattern_string(np.flipud(np.rot90(array, rot)))])

    return variations


grid = [['.', '#', '.'], ['.', '.', '#'], ['#', '#', '#']]
inpt = open('day21.in').read().strip()
data = inpt.splitlines()

artist_book = {2: {}, 3: {}}
for line in data:
    input_pattern, output_pattern = line.split(' => ')
    output_pattern = tuple(output_pattern.split('/'))
    for variation in get_variations(input_pattern):
        artist_book[len(output_pattern) - 1][variation] = output_pattern

for _ in range(18):
    if len(grid) % 2 == 0:
        group_size = 2
    else:
        group_size = 3

    groups = len(grid) // group_size
    new_grid = [[] for _ in range(groups * (group_size + 1))]
    current_book = artist_book[group_size]

    for r in range(groups):
        for c in range(groups):
            section = [row[c * group_size:(c + 1) * group_size] for row in grid[r * group_size: (r + 1) * group_size]]
            output = current_book['/'.join([''.join(row) for row in section])]
            for nr in range(group_size + 1):
                new_grid[nr + (group_size + 1) * r].extend(output[nr])
    grid = new_grid

print(sum([row.count('#') for row in grid]))
