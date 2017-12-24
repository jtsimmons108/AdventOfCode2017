from collections import deque

inpt = open('day24.in').read().strip()
nodes = [tuple(map(int, x.split('/'))) for x in inpt.split()]
valid_bridges = set()
possible = deque()

for node in nodes:
    if 0 in node:
        zero_index = node.index(0)
        possible.append(((node[zero_index], node[(zero_index + 1) % 2]),))

while len(possible):
    current_bridge = possible.popleft()
    valid_bridges.add(current_bridge)
    exposed = current_bridge[-1][-1]
    extensions = list(filter(lambda x: x not in current_bridge and x[::-1] not in current_bridge and exposed in x, nodes))
    for ex in extensions:
        target_index = ex.index(exposed)
        new_bridge = current_bridge + ((ex[target_index], ex[(target_index + 1) % 2]),)
        possible.append(new_bridge)

print(max([sum(map(sum, bridge)) for bridge in valid_bridges]))
ordered = sorted(valid_bridges, key=lambda b: (len(b), sum(map(sum, b))))
print(len(ordered[-1]), sum(map(sum, ordered[-1])))
