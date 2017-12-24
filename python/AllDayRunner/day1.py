

def run():
    num = open('day1.in').read().strip()
    sum1, sum2 = 0, 0
    
    for i in range(len(num)):
        #Part1
        if num[i] == num[(i+1)%len(num)]:
            sum1 += int(num[i])
        #Part2
        if num[i] == num[(i + len(num) // 2) % len(num)]:
            sum2 += int(num[i])
    
    return sum1, sum2