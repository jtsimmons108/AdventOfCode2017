import re


data = open('day7.in').read().strip().splitlines()

nodes = {}
weights = {}
info = re.compile(r'^(\w+) \((\d+)\)')
paths = []


for line in data:
    name, num = info.match(line).groups()
    nodes[name] = []
    weights[name] = int(num)
    if '->' in line:
        children = line[line.index('->') + 2:].strip().split(', ')
        nodes[name] = children

for node in nodes.keys():
    if len(list(filter(lambda x: node in x, nodes.values() ))) == 0:
        start = node
        print('Part1 : ', start)

def get_weights_from_node(node):
    children = nodes[node]
    if len(children) == 0:
        return weights[node]
    return weights[node] + sum(list(map(get_weights_from_node, children)))


previous = []
children_weights = sorted([(node, get_weights_from_node(node)) for node in nodes[start]], key=lambda x: -x[1])

while len(set([n[1] for n in children_weights])) != 1:
    wrong_weight = children_weights[0][1] + children_weights[-1][1] - children_weights[1][1]
    start = list(filter(lambda x: x[1] == wrong_weight, children_weights))[0][0]
    previous = children_weights
    children_weights = sorted([(node, get_weights_from_node(node)) for node in nodes[start]], key=lambda x: -x[1])

bad_node = previous[0][0]
diff = previous[1][1] - previous[0][1]
print('Part 2:', bad_node, 'should weigh', weights[bad_node] + diff)