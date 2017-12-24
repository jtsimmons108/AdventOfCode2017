import re


def run():
    data = open('day7.in').read().strip().splitlines()

    nodes = {}
    weights = {}
    info = re.compile(r'^(\w+) \((\d+)\)')

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

    def get_weights_from_node(node):
        return weights[node] + sum(list(map(get_weights_from_node, nodes[node])))

    children_weights = sorted([(node, get_weights_from_node(node)) for node in nodes[start]], key=lambda x: -x[1])

    while len(set([n[1] for n in children_weights])) != 1:
        wrong_weight = children_weights[0][1] + children_weights[-1][1] - children_weights[1][1]
        start = list(filter(lambda x: x[1] == wrong_weight, children_weights))[0][0]
        previous = children_weights
        children_weights = sorted([(node, get_weights_from_node(node)) for node in nodes[start]], key=lambda x: -x[1])

    bad_node = previous[0][0]
    diff = previous[1][1] - previous[0][1]
    return start, weights[bad_node] + diff
