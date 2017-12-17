import re, hashlib, itertools
from collections import *


inpt = open('day17.in').read().strip()
data = inpt.splitlines()

nums = [0]
target1 = 2017
index = 0
for val in range(1, target1 + 1):
    index = (index + 349) % val + 1
    nums.insert(index, val)

index = 0
after_zero = 0
target2 = 50000000
for val in range(1, target2 + 1):
    index = (index + 349) % val + 1
    if index == 1:
        after_zero = val

print('Part 1: {}'.format(nums[nums.index(target1) + 1]))
print('Part 2: {}'.format(after_zero))
