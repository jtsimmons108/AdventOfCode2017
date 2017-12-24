import re, hashlib, itertools
from collections import *


inpt = open('day20.in').read().strip()
data = inpt.splitlines()
total = 0
points = []

class Point(object):
    def __init__(self, p_id, p_list, v_list, a_list):
        self.p_id = p_id
        self.x, self.y, self.z = p_list
        self.vx, self.vy, self.vz = v_list
        self.ax, self.ay, self.az = a_list
        
    def move(self):
        self.vx += self.ax
        self.vy += self.ay
        self.vz += self.az
        self.x += self.vx
        self.y += self.vy
        self.z += self.vz
        
    def get_distance(self):
        return abs(self.x) + abs(self.y) + abs(self.z)
    def get_acc(self):
        return abs(self.ax) + abs(self.ay) + abs(self.az)
    def __repr__(self):
        return '{} {} {} {}'.format(self.p_id, self.x, self.y, self.z)
    
    def __eq__(self, other):
        return self.x == other.x and self.y == other.y and self.z == other.z
    
points = []

for id, line in enumerate(data):
    line = line.split(', ')
    point = [list(map(int, x[3:-1].split(","))) for x in line]
    points.append(Point(id, *point))

print(sorted(points, key = lambda x: x.get_acc()))

for _ in range(100):
    for p in points:
        p.move()
    i = 0
    while i < len(points):
        dup = False
        j = i + 1
        while j < len(points):
            if points[i] == points[j]:
                del points[j]
                dup = True
            else:
                j += 1
        if dup:
            del points[i]
        else:
            i += 1
print(len(points))
        
print(sorted(points, key = lambda x: x.get_distance()))

    