from __future__ import print_function
import re

start_file = open('bonus.in')
instructions = start_file.read().strip().splitlines()


cpy = re.compile(r'cpy (-*\d+|[a-d]) (.)')
inc = re.compile(r'inc (.)')
dec = re.compile(r'dec (.)')
jump = re.compile(r'jnz (.+) (.+)')
out = re.compile(r'out (.+)')

print(instructions)
registers = {'a' : 0, 'b' : 0, 'c' : 0, 'd' : 0}
output = ''
i = 0
while i < len(instructions):
    line = instructions[i]
    if jump.match(line):
        reg, amt = jump.findall(line)[0]
        val = registers[reg] if reg in registers else int(reg)
        amt = registers[amt] if amt in registers else int(amt)
        delta = 1 if val == 0 else int(amt)
        i += delta
    else:
        print(line)
        if cpy.match(line):
            val, reg = cpy.findall(line)[0]
            registers[reg] = registers[val] if val in registers else int(val)
        elif inc.match(line):
            registers[inc.findall(line)[0]] += 1
        elif dec.match(line):
            registers[dec.findall(line)[0]] -= 1
        elif out.match(line):
            curr = out.findall(line)[0]
            curr = int(curr) if curr.isdigit() else registers[curr]
            output += chr(curr)
        else:
            print("This shouldn't happen")
        i += 1


print(output)
out_file = open('bonus.out', 'w')
out_file.write(output)