import re, hashlib, itertools, sympy, numpy as np
from collections import *

inpt = open('day25.in').read().strip()
data = inpt.splitlines()
total = 0

nums = defaultdict(int)

index = 0
state = 'A'
for _ in range(12172063):
    if state == 'A':
        if nums[index] == 0:
            nums[index] = 1
            index += 1
            state = 'B'
        else:
            nums[index] = 0
            index -= 1
            state = 'C' 
    elif state == 'B':
        if nums[index] == 0:
            nums[index] = 1
            index -= 1
            state = 'A'
        else:
            nums[index] = 1
            index -= 1
            state = 'D'
    elif state == 'C':
        if nums[index] == 0:
            nums[index] = 1
            index += 1
            state = 'D'
        else:
            nums[index] = 0
            index += 1
            state = 'C'
    elif state == 'D':
        if nums[index] == 0:
            nums[index] = 0
            index -= 1
            state = 'B'
        else:
            nums[index] = 0
            index += 1
            state = 'E'
    elif state == 'E':
        if nums[index] == 0:
            nums[index] = 1
            index += 1
            state = 'C'
        else:
            nums[index] = 1
            index -= 1
            state = 'F'
    else:
        if nums[index] == 0:
            nums[index] = 1
            index -= 1
            state = 'E'
        else:
            nums[index] = 1
            index += 1
            state = 'A' 

print(sum(nums.values()))

