import json, dateutil.parser, collections

data = json.load(open('./smarty_leaderboard.json'))
out_file = open('../../smarty_leaderboard.txt', 'w')
out_file.write('Leaderboard for:' + data['event'] + '\n')
members = data['members']
place = 1
anonymous = 1


for member, stars, local_score in sorted([(name, int(members[name]['stars']), int(members[name]['local_score'])) for name in members.keys()], key = lambda x: (-x[2])):
    user = members[member]
    name = user['name']
    if name == None:
        name = "Anonymous {}".format(anonymous)
        anonymous += 1
    out_file.write("{:3}) {:15}\t\t{:4}*\t\tscore: {}\n".format(place, name, stars, local_score))
    out_file.write("\t{:^20}\t\t{:^20}\n".format("----Part 1----", "----Part 2----"))
    daily_info = members[member]['completion_day_level']
    for day in sorted(daily_info.keys(), key = lambda x: -int(x)):
        part_one_time_string, part_two_time_string = '',''
        if '1' in daily_info[day].keys():
            part_one_time_string = dateutil.parser.parse(daily_info[day]['1']['get_star_ts']).strftime('%I:%M:%S %p %m/%d/%y')
        if '2' in daily_info[day].keys():
            part_two_time_string = dateutil.parser.parse(daily_info[day]['2']['get_star_ts']).strftime('%I:%M:%S %p %m/%d/%y')
        out_file.write('{:02d}:\t{}\t\t{}\n'.format(int(day), part_one_time_string, part_two_time_string))
    out_file.write('\n\n\n')
    place += 1
out_file.close()
