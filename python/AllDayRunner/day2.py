import itertools, re, time


def run():
    inpt = open('day2.in').read().strip()
    data = inpt.splitlines()
    
    total_part1, total_part2 = 0, 0
    for line in data:
        nums = re.split(r'\s+', line)
        nums = sorted(list(map(int, nums)))
        total_part1 += nums[-1] - nums[0]
        for i in range(len(nums)):
            j = len(nums) - 1
            while j > i + 1 and nums[j] % nums[i] != 0:
                j -= 1
            if nums[j] % nums[i] == 0:
                total_part2 += nums[j] // nums[i]
                break
    
    return total_part1, total_part2
