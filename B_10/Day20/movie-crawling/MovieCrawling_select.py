import requests
from bs4 import BeautifulSoup


class MovieCrowling :
    
    f = open("moviess.txt","w")
    for num in range(9949,9950):
        webpage = requests.get("https://movie.datamotion.co.kr/Movie/?id="+str(num))
        soup = BeautifulSoup(webpage.content, "html.parser")
        count = 0
        if webpage.status_code ==200:
            count += 1

            # 제목, 제작연도
            KoreaTitle = soup.select_one('body > div > main > table:nth-child(1) > tr > td:nth-child(2) > table > tr:nth-child(1) > td > div')
            titles = soup.select_one('body > div > main > table:nth-child(1) > tr > td:nth-child(2) > table > tr:nth-child(1) > td > span').text.strip().split(' ')
            title = "("
            titleCount = 0
            while titleCount < len(titles) - 1:
                title += titles[titleCount] + " "
                titleCount += 1
            title = title[:-1] + ")"
            year = titles[len(titles)-1][-5:-1]

            # 장르
            generalTitle = soup.find('span',style='background-color:lavender;border-radius:5px;color:black;font-size:12px').get_text(strip = True)
            # 시간
            playingTime = soup.find('span', style="background-color:darkgrey;border-radius:5px;color:white;font-size:12px").get_text(strip = True)
            # 개봉일
            
            openDays = soup.select_one('body > div > main > table:nth-child(1) > tr > td:nth-child(2) > table > tr:nth-child(3) > td > span:nth-child(2)').get_text(strip = True).split(' ')
            # openDays = soup.find("span", style="background-color:aquamarine;border-radius:5px;color:black;font-size:12px").get_text(strip = True).split(' ')
            openDay = ""
            if len(openDays) > 2 :
                for i in openDays:
                    openDay += i +" "
            else :
                openDay = openDays[0]
            # 개요
            outline = soup.find('td', style="vertical-align:top;font-size:10pt").text.strip() + "\n"
            
            # 감독
            directors = soup.find('td', style = "height:20px;vertical-align:bottom")
            # type - <class 'list'>   directorName 사이즈 - 7   내용물 - ['', '감독 ', '리처드 글렛저 (Richard Glatzer)', ',', '워시 웨스트모어랜드 (Wash Westmoreland)', ',', '']
            directorNames = soup.select_one('body > div > main > table:nth-child(1) > tr > td:nth-child(2) > table > tr:nth-child(5) > td').get_text().split("\n")
            directorName = ""
            i = 0

            # 감독 내용 정리
            while i < len(directorNames):   
                if "감독" in directorNames[i] :
                    directorName = directorNames[i] + ": "
                elif "," in directorNames[i]:
                    if i >= len(directorNames)- 2 :
                        break
                    else :
                        directorName += directorNames[i] + " "
                else :
                    directorName += directorNames[i]
                i += 1       
            #각본              copy select = body > div > main > table:nth-child(2) > tr > td -> 이게 2가 있고 3이 있다
            scenarios = soup.select_one("body > div > main > table:nth-child(3) > tr > td")
            scenario = ""
            # body > div > main > table:nth-child(6) > tbody > tr:nth-child(1) > td > table
            # 각본 내용 정리
            if scenarios == None :
                scenario = "" #"감독: " + str(scenarios)
            else :
                for i in scenarios.text.split("\n"):
                    if "각본" in i:
                        print(i)
                        scenario = i + ": "
                    else:
                        if len(i) > 1:
                            scenario += i + ", "
                scenario = scenario[:-2] + "\n"

            #출연진
            if scenarios == None :  # 각본 없을 때
                castsbody = soup.select_one('body > div > main > table:nth-child(6) > tr:nth-child(1) > td > table')
            else :                  # 각본 있을 때
                castsbody = soup.select_one('body > div > main > table:nth-child(7) > tr:nth-child(1) > td > table')
            # print(castsbody)
            print('-------------------------------------------------------------')
            castsbody = castsbody.text.strip().split("\n")
            castResult = ""
            print(castsbody)
            # 출연진 정보 한 줄씩 가져와서 정리해서 넣기
            for i in castsbody :
                if len(i) > 0 and '\t\t\t\t\t\t' not in i:
                    if "-" in i or "출연" in i:
                        castResult += i +"\n"
                    else :
                        castResult += i
            data = ""
            data += str(num) + "\n"
            data += "제목:" + KoreaTitle.text.strip() + title + "\n"
            data += "제작 연도: " +  year + "\n"
            data += "장르:" +  generalTitle + "\n"
            data += "시간:" +  playingTime + "\n"
            data += "개봉일:" +  openDay + "\n"
            data += "개요: " +  outline + "\n"
            data += directorName + "\n"
            data += scenario + "\n"
            data += castResult +"\n"

            print(data)
            # f.write(data)
    f.close()