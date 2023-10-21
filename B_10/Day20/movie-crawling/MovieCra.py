import requests
from bs4 import BeautifulSoup


class MovieCrowling :
    
    for i in range(1, 2):
        webpage = requests.get("https://movie.datamotion.co.kr/Movie/?id=10")
        soup = BeautifulSoup(webpage.content, "html.parser")
        if webpage.status_code ==200:
            f = open("movie.txt",'w')
            print("--------------------------------------------")
            # <span class="Director">각본 </span>

            # <td style="height:20px;vertical-align:bottom">
		# 	<span class="Director">각본 </span><br>
		# 		<a href="/Person/?id=13665">로린 스카파리아 (Lorene Scafaria)</a>
		# </td>
            # scenarios = soup.select('span Director')
            scenarios = soup.select('span[class="Director"]')
            tag = soup.select('td[style="height:20px;vertical-align:bottom"]')

            # tag = soup.select('td')[0]['btn-title']
            Director = ""
            Scenario = ""
            for i in tag:
                if "감독" in i.text.strip() :
                    Directors = i.text.replace(",",", ").split()
                    # print(Directors)
                    for j in Directors:
                        if "감독" in j:
                            Director += j + ": "
                        elif ")" in j :
                            Director += j
                        else:
                            Director += j + " "
                if "각본" in i.text.strip() :
                    Scenarios = i.contents
                    # print(Scenarios)
                    for k in Scenarios:
                        k = k.text.strip()
                        # print(k)
                        if "각본" in k:
                            Scenario += k + ": "
                        elif ")" in k :
                            Scenario += k + ", "
                        else:
                            Scenario += k
            Director = Director[:-2]
            Scenario = Scenario[:-2]

            name = soup.select_one('body > div > main > table:nth-child(7) > tr:nth-child(1) > td > table')
            # print(name)
            df = soup.select_one('body > div > main > table:nth-child(7) > tr:nth-child(1) > td > table')
            # replaceWith가 span을 시작 기준으로 뭘 해주는 거 같은데 잘 모르겟다
            df = df.replaceWith("span","")
            df = df.text.split()
            # print(df)


            f.close()