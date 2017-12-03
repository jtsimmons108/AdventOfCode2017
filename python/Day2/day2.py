import itertools, re, time


inpt = open('aoc_day_2.in').read().strip()
data = inpt.splitlines()

for times in range(10):
    tt1 = 0
    for _ in range(1000):
        start = time.time()
        total = 0
        for line in data:
            nums = re.split(r'\s+', line)
            nums = list(map(int, nums))
            for i in range(len(nums)):
                for j in range(len(nums)):
                    if i != j and nums[j] % nums[i] == 0:
                        total += nums[j] // nums[i]
        tt1 += time.time() - start

    print(total, tt1 / 1000)


    tt2 = 0
    for _ in range(1000):
        start = time.time()
        total = 0
        for line in data:
            nums = re.split(r'\s+', line)
            nums = sorted(list(map(int, nums)))
            for i in range(len(nums)):
                j = len(nums) - 1
                while j > i + 1 and nums[j] % nums[i] != 0:
                    j -= 1
                if nums[j] % nums[i] == 0:
                    total += nums[j] // nums[i]
                    break

        tt2 += time.time() - start

    print(total, tt2 / 1000)

    tt3 = 0
    for _ in range(1000):
        start = time.time()
        input = [map(int, x.split()) for x in data]
        total = sum([max(i) // min(i) for x in input for i in itertools.combinations(x, 2) if max(i) % min(i) == 0])
        tt3 += time.time() - start
    print(total, tt3 / 1000)

    print(tt1/tt2)
    print()