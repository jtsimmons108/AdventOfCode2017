from functools import reduce
from operator import xor
def twist(nums, start, move):
    nums = nums[::]
    end = start + move
    if end > len(nums):
        new = nums[start:] + nums[0:end % len(nums)]
    else:
        new = nums[start:end]
    for i in range(len(new)):
        nums[(start + i) % len(nums)] = new[-1 - i]
    return nums

def get_knot_hash(instructions):
    instructions = list(map(ord, instructions)) + [17, 31, 73, 47, 23]
    start, skip = 0,0
    data = list(range(256))
    for _ in range(64):
        for move in instructions:
            data = twist(data, start, move)
            start = (start + move + skip) % len(data)
            skip += 1

    hashes = []
    for i in range(0, 256, 16):
        hashes.append(reduce(xor, data[i:i+16]))
    return ''.join(["{:02x}".format(num) for num in hashes])

def get_binary_string(knot_hash):
    result = ''
    for char in knot_hash:
        result += '{:04b}'.format(int(char, 16))
    return result

grid = []
for num in range(128):
    grid.append(list(map(int, get_binary_string(get_knot_hash('jxqlasbh-{}'.format(num))))))

print(sum([row.count(1) for row in grid]))