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

def run():
    inpt = list(map(int, "63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24".split(',')))
    start, skip = 0, 0
    data = list(range(256))
    part1 = 0
    for move in inpt:
        data = twist(data, start, move)
        start = (start + move + skip) % len(data)
        skip += 1

    part1 = data[0]*data[1]

    inpt = list(map(ord, "63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24")) + [17, 31, 73, 47, 23]
    data = list(range(256))
    start, skip = 0, 0
    for _ in range(64):
        for move in inpt:
            data = twist(data, start, move)
            start = (start + move + skip) % len(data)
            skip += 1

    hashes = []
    for i in range(0,16):
        num = 0
        for n in data[16 * i: 16 * (i + 1)]:
            num ^= n
        hashes.append(num)

    return part1,  ''.join(list(map(lambda x: "{:02x}".format(x), hashes)))


