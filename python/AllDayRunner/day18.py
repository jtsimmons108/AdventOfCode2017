from collections import *

inpt = open('day18.in').read().strip()
instructions = inpt.splitlines()


def get_value(val, registers):
    if val in registers:
        return registers[val]
    return int(val)


def process_instruction(index, registers, queue, other_queue):
    move, *values = instructions[index].split()
    if move == 'jgz':
        val, offset = map(lambda r: get_value(r, registers), values)
        if val > 0:
            return index + offset
    elif move == 'snd':
        other_queue.append(get_value(values[0], registers))
        registers['sent'] += 1
    elif move == 'rcv':
        if len(queue) > 0:
            registers[values[0]] = queue.popleft()
            registers['waiting'] = False
        else:
            registers['waiting'] = True
            return index
    else:
        reg, val = values
        val = get_value(val, registers)
        if move == 'set':
            registers[reg] = val
        elif move == 'add':
            registers[reg] += val
        elif move == 'mul':
            registers[reg] *= val
        elif move == 'mod':
            registers[reg] %= val
    return index + 1


register_one = {'ID': 0, 'sent': 0, 'waiting':False, 'p': 0}
register_two = {'ID': 1, 'sent': 0, 'waiting': False, 'p': 1}
register_one_queue = deque()
register_two_queue = deque()
index_one, index_two = 0,0

while not register_one['waiting'] or not register_two['waiting']:
    index_one = process_instruction(index_one, register_one, register_one_queue, register_two_queue)
    index_two = process_instruction(index_two, register_two, register_two_queue, register_one_queue)

print(register_two['sent'])