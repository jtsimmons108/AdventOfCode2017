'''

All algorithms of moving in hexagonal cooridnate system adapted from:
https://www.redblobgames.com/grids/hexagons/

'''


def run():
    moves = {'n': (1, -1, 0), 's': (-1, 1, 0), 'ne': (0, -1, 1), 'nw': (1,0,-1), 'sw': (0, 1, -1), 'se': (-1, 0, 1)}

    directions = open('day11.in').read().strip().split(',')

    point = [0, 0, 0]
    max_distance = 0

    for move in directions:
        point = [point[i] + moves[move][i] for i in range(3)]
        max_distance = max(max_distance, sum(map(abs, point))//2)

    return sum(map(abs, point))//2,  max_distance







