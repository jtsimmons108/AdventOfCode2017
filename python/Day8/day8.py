import re
from collections import *
import time


inpt = open('day8.in').read().strip()
data = inpt.splitlines()
registers = defaultdict(int)

def make_jump(left, op, right):
    left = registers[left]
    return eval('{} {} {}'.format(left, op, right))


instruction = re.compile(r'(\w+) (inc|dec) (-?\d+) if (\w+) (.+) (-?\d+)')
max_vals = []

for line in data:
    reg1, inc, val1, reg2, op, val2 = instruction.match(line).groups()
    val1, val2 = int(val1), int(val2)
    if make_jump(reg2, op, val2):
        if inc == 'dec':
            val1 *= -1
        registers[reg1] += val1

    max_vals.append(max(registers.values()))

print('Part 1:', max(registers.values()))
print('Part 2:', max(max_vals))