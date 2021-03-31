import requests

# 根据筛选条件筛选traces
def calculate(service):
    url = "http://10.176.122.15:32688/api/traces"
    params = {"limit": 20, "lookback": "1h", "service": service}
    # 直接根据traceId获取trace
    # url = "http://10.176.122.15:32688/api/trace"
    headers = {"Content-Type": "application/json"}
    response = requests.get(url, params=params)
    # print(response.url)
    # print(response.json()["data"])
    data= response.json()["data"]
    print(len(data))
    # print(data[0])
    for j in range(len(data)):
        dic = data[j]["processes"]
        idxs = {}
        spans = data[j]["spans"]
        for i in range(len(data[j]["spans"])):
            if spans[i]["traceID"] == spans[i]["spanID"]:
                print("### " + dic[spans[i]["processID"]]["serviceName"] + ": " + spans[i]["operationName"] )
                time = spans[i]["logs"][-1]["timestamp"] - spans[i]["startTime"]
                continue
            key = dic[spans[i]["processID"]]["serviceName"] + ": " + spans[i]["operationName"]
            if spans[i]["operationName"] in ["GET", "POST", "PUT", "DELETE"]:
                continue
            if key in idxs:
                idxs[key] += 1
            else:
                idxs[key] = 1
        for key in idxs.keys():
            print(key, idxs[key])
        print("totaltime: " + str(time/1000000) + "s")
        print()

rootdir = r"C:\Users\Kana\Desktop\PROJECT\train-ticket"

# 计算所用功能下函数被调用次数（所有功能权重仅为1时）
def get_sum_of_getting_invoked_times_of_function():
    sum = {}
    with open(rootdir + "//用户端功能调用情况.md", 'r', encoding='utf-8')as f:
        lines = f.readlines()
    f.close()
    # print(len(lines))
    with open(rootdir + "//管理端功能调用情况.md", 'r', encoding='utf-8')as f:
        lines += f.readlines()
    f.close()
    # print(len(lines))
    for line in lines:
        for i in range(len(line)-1):
            if line[i] == 't' and line[i+1] == 's':
                tmp = line[i:]
                tmp = tmp.split()
                key = tmp[0] + ' ' + tmp[1]
                if len(tmp) == 3:
                    idx = int(tmp[2])
                else:
                    idx = 1
                if key in sum:
                    sum[key] += idx
                else:
                    sum[key] = idx
                break
    sum = sorted(sum.items(), key=lambda k: k[1],reverse=True)
    for s in sum:
        print(s[0], s[1])


# 查找含有目标改造函数的功能
def find_features_by_function_name(function_name):
    with open(rootdir + "//用户端功能调用情况.md", 'r', encoding='utf-8')as f:
        lines = f.readlines()
    f.close()
    with open(rootdir + "//管理端功能调用情况.md", 'r', encoding='utf-8')as f:
        lines += f.readlines()
    f.close()
    dict = {}
    # 对应好功能与函数的数据结构并输出
    for line in lines:
        if line[0] == '#' and line[1] == '#' and line[2] == ' ':
            key = line[3:-1]
            dict[key] = []
        else:
            for i in range(len(line) - 1):
                if line[i] == 't' and line[i + 1] == 's':
                    tmp = line[i:]
                    tmp = tmp.split()
                    dict[key].append(tmp[0] + " " + tmp[1])
                    break
    # for d in dict.keys():
    #     print(d, dict[d])
    # 根据function_name查找features
    for d in dict.keys():
        if function_name in dict[d]:
            print(d, dict[d][0])


if __name__ == '__main__':
    # service = "ts-execute-service"
    # calculate(service)
    # get_sum_of_getting_invoked_times_of_function()
    find_features_by_function_name("ts-station-service: queryForStationId")
