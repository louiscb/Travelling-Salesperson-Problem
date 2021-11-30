import random

num = 10000

print(num)
for x in range(num):
    print(round(random.uniform(0, 100), 2), round(random.uniform(0, 100), 2))