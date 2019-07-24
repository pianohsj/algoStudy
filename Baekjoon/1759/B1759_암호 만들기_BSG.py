from itertools import combinations
L,C = map(int,input().split()) # L, C값 할당
a = sorted(input().split())# 문자 받아서 크기순 정렬
for passwd in combinations(a, L): #크기순 정렬 해서 뽑는 것은 combination
    answer = ""
    vowel, consonant = 0, 0#모음 자음 카운팅
    for i in range(len(passwd)): #
        if passwd[i] in "aeiou":
            vowel += 1
        else:
            consonant += 1
        answer+= answer[i]
    if vowel >= 1 and consonant >= 2:
        print(answer)

