import json, dateutil.parser

data = json.load(open('./leaderboard.json'))
out_file = open('../../leaderboard.txt', 'w')
print('Leaderboard for:', data['event'],'\n')
members = data['members']
for member, stars, local_score in sorted([(name, int(members[name]['stars']), int(members[name]['local_score'])) for name in members.keys()], key = lambda x: (-x[2])):
    user = members[member]
    print(user['name'], ':', stars, '*', '\t\tscore:', local_score)
    out_file.write(user['name'] + ':'+  str(stars) + '*' +  '\t\tscore: ' + str(+ local_score) + '\n')
    daily_info = members[member]['completion_day_level']
    for day in sorted(daily_info.keys(), key = lambda x: int(x)):
        part_one_time_string, part_two_time_string = '',''
        if '1' in daily_info[day].keys():
            part_one_time_string = dateutil.parser.parse(daily_info[day]['1']['get_star_ts']).strftime('%I:%M:%S %p %m/%d/%y')
        if '2' in daily_info[day].keys():
            part_two_time_string = dateutil.parser.parse(daily_info[day]['2']['get_star_ts']).strftime('%I:%M:%S %p %m/%d/%y')
        if len(day) == 1:
            day = '0' + day
        print('Day', day, ':  Part 1: ', part_one_time_string, '\tPart 2:', part_two_time_string)
        out_file.write('Day ' +  str(day) +  ':  Part 1: ' + part_one_time_string +  '\tPart 2: ' +  part_two_time_string+ '\n')
    print('\n')
    out_file.write('\n')
out_file.close()

