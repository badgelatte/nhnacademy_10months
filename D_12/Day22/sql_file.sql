select RoleName  -- , count(RoleName)
from person 
inner join appear on person.PersonID = appear.PersonID
inner join movie on movie.MovieID = appear.MovieID
inner join role on role.RoleID = appear.RoleID
where movie.Title = 'The Martian'
group by role.RoleName; -- 

select KindOfAircraft
from Flight inner join Aircraft ON Flight.AircraftNo = Aircraft.AircraftNo
where Flight.Deparetures = '인천';

--  서브 쿼리
-- 도착지가 '인천'인 비행기의 종류는?
select KindOfAircraft
from Aircraft
where AircraftNo in (
select AircraftNo
from Flight
where Flight.Deparetures = '인천');


-- 다중 서브쿼리
-- 
select PassengerName
from Passenger
where PassengerNo in(	-- 여기랑 
	select PassengerNo	-- 여기가 PassengerNo를 서로 참조한다
    from Reservation
    where FlightNo in (	-- 여기랑
		select FlightNo	-- 여기가 FlightNo를 서로 참조한다 -> inner join과 비슷
        from Flight
        where Arrival = '샌프란시스코'));
        
-- 대한 항공에서 운항하는 항공편의 항고편 번호, 비행기 번호, 출발지, 도착지, 가격을 구하라
-- 서브쿼리와 inner join으로 둘 다 만들어 보시오

select Aircraft.AircraftNo, FlightNo, Deparetures, Arrival, Price from Aircraft
inner join Flight on Flight.AircraftNo = Aircraft.AircraftNo
where Airline = '대한항공';

select AircraftNo, FlightNo, Deparetures, Arrival, Price from Flight
where AircraftNo in (
	select AircraftNo
    from Aircraft
    where Airline = '대한항공');

select distinct KindOfAircraft
from Aircraft as a,
where ( )
;


select Flightno,
(select Airline from Aircraft as a where a.AircraftNo = f.AircraftNo)
From Flight as f;

select flightNo, depareture, arrival, airline
from flight as f inner join Aircraft as a on f.aircraftNo = a.AircraftNo;

select PassengerName, Age 
from Passenger as p1
where age > (
	select avg(age)
	from Passenger as p2
	where p1.grade = p2.grade);
    
    select * from Passenger;
    
    --  뷰 ----------------------------------------
-- 3개의 컬럼을 가진 뷰 만들기 완료
CREATE view ReserveStatus
as
select Passenger.PassengerNo, Passenger.PassengerName,KindOfAircraft
from Passenger inner join Reservation on Passenger.PassengerNo = Reservation.PassengerNo
    inner join Flight  on Reservation.FlightNo = Flight.FlightNo
    inner join Aircraft on Flight.AircraftNo = Aircraft.AircraftNo;

drop view ReserveStatus;

-- 만든 뷰 보기
select * from ReserveStatus;

select KindOfAircraft FROM ReserveStatus
where PassengerName = '이순신';

insert into ReserveStatus(PassengerNo, PassengerName) values(100, '아무개');
select * from Passenger;

-- 뷰가 참조하는 테이블의 제약 조건에 따라 들어갈 수 있고 안 들어 갈 수 있다
insert into ReserveStatus(KindOfAircraft) values('F-14');

-- ---------------------------------------------------------------------------
CREATE VIEW ReservationInfo (No, Name, Grade, ReservedDate, FlightNo)
AS
SELECT p.PassengerNo, PassengerName, Grade, ReservedDate, f.FlightNo
FROM
	Passenger AS p 
    INNER JOIN Reservation AS r ON p.PassengerNo = r.PassengerNo
    INNER JOIN Flight AS f ON f.FlightNo = r.FlightNo;

CREATE VIEW FlightInfo (No, Departures, Arrivals, Price, Date, AircraftNo, AirCraftType, Airline)
AS
SELECT f.FlightNo, Deparetures, Arrival, Price, FlightDate, a.AircraftNo, KindOfAircraft, Airline
FROM Flight AS f INNER JOIN Aircraft AS a ON f.AircraftNo = a.AircraftNo;

select * from ReservationInfo;
select * from FlightInfo;

select name, reserveddate, Deparetures, Arrival, date
from ReservationInfo AS r natural join FlightInfo
where name = '이순신';



select Departure, Arrivals price, Date, Type
from FlightInfo as f inner join KoreanAir as k
ON f.AircraftNo = k.no;

-- -------------------------------------------------------------------
GRANT select on Module06.AsianaAir TO Michael;
GRANT select on Module06.KoreanAir TO Michael;
GRANT select on Module06.JejuAir TO Michael;
GRANT select on Module06.FlightInfo TO Michael;
GRANT select on Module06.ReservationInfo TO Michael;




