import time

total = 0
for day in range(1,15):
    exec('import day{}'.format(day))
    start = time.time()
    part1, part2 = eval('day{}.run()'.format(day))
    end = time.time()
    total += end - start
    print('Day {}\n-----\nPart 1: {}\nPart 2: {}\n  Time: {:.3f} sec\n'.format(day, part1, part2, end - start ))

print('Total time: {:.4f}'.format(total))


