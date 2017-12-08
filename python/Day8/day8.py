import re, hashlib, itertools
from collections import *

inpt = open('day8.in').read().strip()
data = inpt.splitlines()
registers = {}


def make_jump(left, op, right):
    registers.setdefault(left, 0)
    left = registers[left]
    return eval('{} {} {}'.format(left, op, right))


inc = re.compile(r'(.+) inc (.+) if (.+) (.+) (.+)')
dec = re.compile(r'(.+) dec (.+) if (.+) (.+) (.+)')
max_vals = []

for line in data:
    if inc.match(line):
        reg1, val1, reg2, op, val2 = inc.match(line).groups()
        val1, val2 = int(val1), int(val2)
        if make_jump(reg2, op, val2):
            registers.setdefault(reg1, 0)
            registers[reg1] += val1
    elif dec.match(line):
        reg1, val1, reg2, op, val2 = dec.match(line).groups()
        val1, val2 = int(val1), int(val2)
        if make_jump(reg2, op, val2):
            registers.setdefault(reg1, 0)
            registers[reg1] -= val1

    max_vals.append(max(registers.values()))

print('Part 1:', max(registers.values()))
print('Part 2:', max(max_vals))