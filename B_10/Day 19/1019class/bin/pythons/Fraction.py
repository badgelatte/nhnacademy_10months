class Fraction(object): # class 클래스Name (상속할 것을 입력) -> 상속이 된다
    def __init__(self, num, denom): # -> __init은 필수
        self.num = num              # 분자
        self.denom = denom          # 분모
    
    def __str__(self):
        return str(self.num) + "/" + str(self.denom)
    
    def __add__(self, other):
        top = self.num * other.denom + self.denom * other.num
        bottom = self.denom * other.denom
        return Fraction(top, bottom)
    
    def __sub__(self, other):
        top = self.num * other.denom - self.denom * other.num
        bottom = self.denom * other.denom
        return Fraction(top, bottom)
    
    def __float__(self):            # 계산기처럼 결과 값보려고, 소수점으로 띄우기 위해
        return self.num/self.denom
    
    def ration(self, common = 1):
        rNum = abs(self.num)        # rNum = self.num의 절댓값
        rDenom = abs(self.denom)    # rDenom = self.denom의 절댓값

        for i in range(2, rNum):
            if rNum % i == 0 and rDenom % i ==0:
                common = i

        # print(locals())
        rNum = rNum // common
        rDenom = rDenom // common

        if self.num < 0 or self.denom < 0:
            rNum = rNum * -1
        return Fraction(rNum, rDenom)


f = Fraction(2,3)
# print(f) # -> 결과 값 : <__main__.Fraction object at ~~~~~> => 그러므로 toString해줘라

g = Fraction(2,3)
z = f + g
w = z - g

print("f + g = ", z)
print("z - g = ", w)
print(float(w))

a = Fraction(18, -27)

print("ration 적용 전: ", a)
print("ration 적용 후: ", Fraction.ration(a))   # Fraction.ration(a), a.ration() 이렇게 사용해야한다
