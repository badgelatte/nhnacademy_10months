show tables;
desc movie;
select * from movie;

-- 1 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
select ReleaseYear, Title, RunningTime, Plot from movie where KoreanTitle = '퍼스트 맨';
-- releaseYear 제작 연도 / Title 영문 제목 / Running Time 러닝 타임 / => movie

 -- 2 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
 select KoreanTitle, Title from movie where ReleaseYear = 2003;


-- 3 영화 '글래디에이터'의 작곡가를 고르세요
select Name, KoreanName from movie natural join appear natural join role natural join person where KoreanTitle = '글래디에이터' and RoleKorName = '작곡';

-- 4. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
-- select * from movie join appear ON movie.MovieID = appear.MovieID where KoreanTitle = '매트릭스'; 
select count(*) AS how_many_Director from movie natural join appear natural join role where KoreanTitle = '매트릭스' and RoleKorName = '감독';

-- 5. 감독이 2명 이상인 영화를 출력하세요
select MovieID, Title, KoreanTitle, count(*) as '감독 수' 
from movie natural join appear natural join role where RoleKorName = '감독'
-- group by MovieID having count(MovieID) >= 2 order by count(*) desc; v -- 내가 생각하기엔 PersonID가 맞는 거 같은데?
group by MovieID
having count(PersonID) >= 2 order by count(*) desc;

-- 부학장님이랑 같이
-- select *
-- from person inner join appear on person.PersonID = appear.PersonID
-- 	inner join movie on appear.MovieID = movie.MovieID
-- 	inner join role on role.RoleID = appear.RoleID
-- where role.RoleName = 'Director' and
-- person.KoreanName = '제임스 카메론'
-- INTERSECT -- intersect가 mysql에선 안 먹힌다 -> 대신으로 inner join 써야한다
-- select *
-- from person inner join appear on person.PersonID = appear.PersonID
-- 	inner join movie on appear.MovieID = movie.MovieID
-- 	inner join role on role.RoleID = appear.RoleID
-- where role.RoleName = 'Actor' and
-- person.KoreanName = '아놀드 슈워제네거';


select * from role;
-- select * from movie natural join appear natural join role where RoleKorName = '감독' ;

-- 6.'한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
select movieID, Title, KoreanName, WinOrNot
from movie natural join appear natural join role natural join person natural join awardinvolve natural join winning 
where KoreanName = '한스 짐머' and WinningID = 2;

-- 7.감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요

Select m.MovieID , m.Title as '영화이름', aap.Name as '배우 이름', ar.RoleKorName, dap.Name as '감독 이름',dr.RoleKorName 
	from movie as m
	inner join appear as aa on m.MovieID = aa.MovieID
    inner join appear as da on m.MovieID = da.MovieID
    inner join role as ar on ar.RoleID = aa.RoleID
    inner join role as dr on dr.RoleID = da.RoleID
    inner join person as aap on aap.PersonID = aa.PersonID
    inner join person as dap on dap.PersonID = da.PersonID
    where (dap.KoreanName = '제임스 카메론' And dr.RoleKorName = '감독') AND (aap.KoreanName = '아놀드 슈워제네거' And ar.RoleKorName = '배우'); 
;

select movieID, Title from movie natural join appear natural join role natural join person 
where (KoreanName = '제임스 카메론' and RoleKorName = '감독') or (KoreanName = '아놀드 슈워제네거' and RoleKorName = '배우')
group by movieID having count(*) >= 2;

-- 08. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
select distinct Title, RunningTime, KoreanName from movie natural join appear natural join person
where RunningTime >= 100 and KoreanName = '레오나르도 디카프리오' and RoleID = 6 -- Name = Leonardo DiCaprio 
;

-- 09. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
select distinct Title, KoreanTitle, BoxOfficeWWGross, Budget
from movie natural join grade natural join gradeinkorea
where GradeInKoreaName = '청소년 관람불가'
order by BoxOfficeWWGross desc;

-- 10.1999년 이전에 제작된 영화의 수익 평균을 고르시오
select avg(BoxOfficeWWGross) AS '수익 평균' from movie
where ReleaseYear < 1999;

-- 11. 가장 많은 제작비가 투입된 영화를 고르시오.
select Title, Budget from movie
order by Budget desc limit 1;

-- 12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
select KoreanName, sum(Budget) AS '제작한 영화 제작비 총합' from movie as m
inner join appear as a ON m.MovieID = a.MovieID
inner join role as r ON r.RoleID = a.RoleID
inner join person as p ON p.PersonID = a.PersonID
where RoleKorName = '감독' and KoreanName > ''
group by KoreanName
order by sum(Budget) desc, KoreanName limit 1;

-- 13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.
select Name, sum(BoxOfficeWWGross) AS '세계흥행',sum(BoxOfficeWWGross) AS '미국 흥행'
from movie as m
inner join appear as a on a.MovieID = m.MovieID
inner join person as p on p.PersonID = a.PersonID
inner join role as r on r.RoleID = a.RoleID
where RoleName = 'Actor'
group by Name limit 1; 

-- 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
select Title, Budget from movie
where Budget > 0
order by Budget limit 1;

-- 15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
select avg(BoxOfficeWWGross) AS '평균 수익' from movie
where Budget > 50000000;

-- 16. 액션 장르 영화의 평균 수익을 고르세요
select avg(BoxOfficeWWGross) from movie
natural join moviegenre natural join genre 
where GenreName = 'Action';

-- 17. 드라마, 전쟁 장르의 영화를 고르세요.
select Title, KoreanTitle from movie
natural join moviegenre natural join genre
where GenreKorName = '드라마' or GenreKorName = '전쟁' 
group by MovieID having count(*) >= 2;

select movie.MovieID, Title, KoreanTitle from movie
inner join moviegenre AS dramaG ON dramaG.MovieID = movie.MovieID
inner join moviegenre AS warG ON warG.MovieID = movie.MovieID
inner join genre AS drama ON drama.GenreID = dramaG.GenreID
inner join genre AS war ON war.GenreID = warG.GenreID
where drama.GenreKorName = '드라마' and war.GenreKorName = '전쟁';
-- union은 낼 물어보자 몰겠다 이거 쓰는 방법은

-- 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
select Title, KoreanTitle, ReleaseYear, RunningTime from movie
natural join appear natural join role natural join person
where KoreanName = '톰 행크스'
order by RunningTime desc limit 1;

-- 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
select KoreanName, count(*) AS '수상 갯수' from movie
natural join appear natural join role natural join person
natural join awardinvolve natural join sector natural join winning
where SectorKorName = '남우주연상' and WinOrNot = 'Winner'
group by KoreanName
order by count(*) desc limit 1;
-- WinOrNot = 'Winner' 대신 WinningID = 2 하면 natural join winning 떼도 된다

-- 20. 아카데미상을 가장 많이 수상한 배우를 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
select KoreanName, count(*) AS '수상 갯수' from movie
natural join appear natural join role natural join person
natural join awardinvolve natural join winning
where WinOrNot = 'Winner' and RoleKorName = '배우'
group by KoreanName
order by count(*) desc limit 1;

-- 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
select KoreanName, count(*) AS '수상 갯수' from movie
natural join appear natural join role natural join person natural join awardinvolve natural join sector natural join winning
where SectorKorName = '남우주연상' and WinOrNot = 'Winner'
group by KoreanName having count(*) >= 2
order by count(*) desc;

-- 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
select KoreanName, count(*) AS '수상 갯수' from movie
natural join appear natural join role natural join person
natural join awardinvolve natural join winning
where WinOrNot = 'Winner' and KoreanName !='수상자 없음'
group by KoreanName
order by count(*) desc;

-- 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
select KoreanName, count(*) AS '수상 갯수' from movie
natural join appear natural join role natural join person
natural join awardinvolve natural join winning
where WinOrNot = 'Winner' and KoreanName !='수상자 없음'
group by KoreanName
order by count(*) desc;

select * from genre;
select * from winning;
select * from awardinvolve;

-- 25. 가장 많은 영화에 출연한 여배우를 고르세요.
select Name, count(Name) AS '영화에 출연한 횟수' from movie
inner join appear ON movie.MovieID = appear.MovieID
inner join role ON role.RoleID = appear.RoleID
inner join person ON person.PersonID = appear.PersonID
-- inner join moviegenre ON moviegenre.MovieID = movie.MovieID
-- inner join genre ON genre.GenreID = moviegenre.GenreID
where RoleName = 'Actor'
group by Name
order by count(Name) desc limit 1
;

-- 26. 수익이 가장 높은 영화 TOP 10을 출력하세요. 
select Title, BoxOfficeWWGross, BoxOfficeUSGross from movie
order by BoxOfficeWWGross desc limit 10;

-- 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
select Title, BoxOfficeWWGross, Budget from movie
where BoxOfficeWWGross >= 1000000000 and Budget <= 100000000
order by BoxOfficeWWGross;

-- 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
select Name, count(Name) AS '전쟁 영화 감독한 횟수' from movie 
inner join moviegenre ON movie.MovieID = moviegenre.MovieID
inner join genre ON moviegenre.GenreID = genre.GenreID
inner join appear ON appear.MovieID = movie.MovieID
inner join role ON role.RoleID = appear.RoleID
inner join person ON person.PersonID = appear.PersonID
where GenreKorName = '전쟁' and RoleName = 'Director'
group by Name 
order by count(Name) desc limit 1
;

-- 29. 드라마에 가장 많이 출연한 사람을 고르세요.
select Name, count(Name) AS '드라마 촬영한 횟수' from movie 
inner join moviegenre ON movie.MovieID = moviegenre.MovieID
inner join genre ON moviegenre.GenreID = genre.GenreID
inner join appear ON appear.MovieID = movie.MovieID
inner join role ON role.RoleID = appear.RoleID
inner join person ON person.PersonID = appear.PersonID
where GenreName = 'Drama' and RoleName = 'Actor'
group by Name 
order by count(Name) desc limit 1
;

-- 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
-- 5번 참고
select Name from movie 
inner join moviegenre ON movie.MovieID = moviegenre.MovieID
inner join genre ON moviegenre.GenreID = genre.GenreID
inner join appear ON appear.MovieID = movie.MovieID
inner join role ON role.RoleID = appear.RoleID
inner join person ON person.PersonID = appear.PersonID
where (GenreName = 'Drama' and RoleName = 'Actor') or (GenreName = 'Horror' and RoleName = 'Actor')
group by Name;

select * from genre;

-- 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
select Location, count(Location) from awardyear
group by Location
order by count(Location) desc;

-- 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
select date_format(now(), '%Y') - date_format(Date, '%Y')
-- , date_format(now(), '%Y'), date_format(Date, '%Y') 
from awardyear
order by Date limit 1;
