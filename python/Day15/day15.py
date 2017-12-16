import re, hashlib, itertools
from collections import *


inpt = open('day15.in').read().strip()
factor = 2147483647

gen_a = 703
gen_b = 516
factor_a = 16807
factor_b = 48271
total = 0
count = 0
def get_next(value, factor, mod):
    return (value * factor) % mod

while count < 5000000:
    while gen_a % 4 != 0:
        gen_a = get_next(gen_a, factor_a, factor)
    while gen_b % 8 != 0:
        gen_b = get_next(gen_b, factor_b, factor)
    if (gen_a & 0xffff) == (gen_b & 0xffff):
        total += 1
    count += 1
    gen_a = get_next(gen_a, factor_a, factor)
    gen_b = get_next(gen_b, factor_b, factor)

print(total)



