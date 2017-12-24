import sympy
from collections import *

inpt = open('day23.in').read().strip()
data = inpt.splitlines()
registers = defaultdict(int)

i = 0


def get_value(val):
    if val.isalpha():
        return registers[val]
    return int(val)


total = 0

while i < len(data):
    instruction, reg, val = data[i].split()
    if instruction == 'set':
        registers[reg] = get_value(val)
    elif instruction == 'sub':
        registers[reg] -= get_value(val)
    elif instruction == 'mul':
        registers[reg] *= get_value(val)
        total += 1
    else:
        if get_value(reg) != 0:
            i += get_value(val) - 1
    i += 1

print('Part 1: {}'.format(total))

b = 81 * 100 + 100000
c = b + 17000
h = 0
for val in range(b, c + 1, 17):
    for num in range(2, int(val**0.5)):
        if val % num == 0:
            h += 1
            break
print('Part 2: {}'.format(h))
