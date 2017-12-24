
def run():
    scanners = {}

    for line in open('day13.in'):
        depth, cycle = map(int, line.split(": "))
        scanners[depth] = cycle

    severity = sum(map(lambda s: s * scanners[s] if s % (2 * (scanners[s] - 1)) == 0 else 0, scanners.keys()))

    def makes_clean_pass(delay):
        for d, r in scanners.items():
            if (d + delay) % (2 * (r - 1)) == 0:
                return False
        return True

    delay = 0
    while not makes_clean_pass(delay):
        delay += 1

    return severity, delay
