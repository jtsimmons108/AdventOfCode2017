from __future__ import print_function
import numpy as np

start_file = open('bonus.out')
instructions = start_file.read().strip().splitlines()

#screen = [[0]*50 for _ in range(6)]
screen = np.zeros((6,50))

def get_screen_string():
    return '\n'.join([''.join(['#' if screen[r,c] == 1 else ' ' for c in range(50)]) for r in range(6)])


for line in instructions:
    info = line.split()
    if len(info) == 2:
        cols, rows = [int(v) for v in info[1].split('x')]
        for r in range(rows):
            for c in range(cols):
                screen[r, c] = 1
    else:
        distance = int(info[4])
        if info[1] == 'row':
            row = int(info[2].split('=')[1])
            screen[row] = np.roll(screen[row], distance)

        else:
            col = int(info[2].split('=')[1])
            screen.T[col] = np.roll(screen.T[col], distance)


print(np.sum(screen))
print(get_screen_string())

