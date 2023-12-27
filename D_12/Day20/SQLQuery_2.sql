DROP DATABASE IF EXISTS Module03
GO

CREATE DATABASE Module03
GO

USE Module03
GO

CREATE TABLE Category (
    CategoryNo int,
    CategoryName varchar(50) NOT NULL,
    CONSTRAINT pk_Category PRIMARY KEY(CategoryNo)
)
GO

CREATE TABLE Product (
    ProductNo int PRIMARY KEY,
    ProductName varchar(100) NOT NULL,
    UnitPrice int DEFAULT 0 NOT NULL,
    Description text,
    CategoryNo int,
    
    CONSTRAINT fk_Product_CategoryID FOREIGN KEY(CategoryNo) REFERENCES Category(CategoryNo)
)
GO

CREATE TABLE Customer (
    CustomerNo int,
    CustomerName nvarchar(10),
    Email varchar(40),
    Password varchar(16),
    
    CONSTRAINT pk_Customer PRIMARY KEY(CustomerNo)
)
GO

CREATE TABLE Orders (
	OrderNo int,
    OrderDate Date,
    CustomerNo int FOREIGN KEY REFERENCES Customer(CustomerNo),

    CONSTRAINT pk_Order PRIMARY KEY(OrderNo),
    CONSTRAINT fk_Order_Customer FOREIGN KEY(CustomerNo) REFERENCES Customer(CustomerNo)
)
GO

CREATE TABLE OrderDetail (
	ProductNo	int,
    OrderNo	int,
    Quantity int,

    CONSTRAINT pk_OrderDetail PRIMARY KEY(ProductNo, OrderNo),
    CONSTRAINT fk_OrderDetail_Order FOREIGN KEY(OrderNo) REFERENCES Orders(OrderNo)
)
GO

-- ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ

insert into Category VALUES(1, 'Novel'), (2, 'Science');

select * from Category;

insert into Product VALUES(1, 'Fellowship of the Rings', 25000, 'Book of Legend', 1);
insert into Product VALUES (2, 'The Two Towers', 25000, 'Book of Legend', 1);

select * from Product;

insert into Product VALUES (4, 'Science', 25000, 'Book of Legend', 2);
insert into Product VALUES (5, 'Newton', 25000, 'Science Magazine', 2);
insert into Product VALUES (3, 'Reurn of the King', 25000, 'Book of Legend', 1);

CREATE INDEX idx_product on Product(CategoryNo);

insert into Product values (7, 'World War Z', 20000, 'Most interesting book', 1);
insert into Product VALUES (6, 'Bourne Identity', 18000, 'Spy Novel', 1);

select * from Product with(index(idx_product))where CategoryNo > 0;

select * from Product WHERE CategoryNo > 0;

-- desc Category;
SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Category';


-- 저거 주석 처리 치워버리면 index 적용시키고, 주석하면 컴퓨터가 알아서 적정한 걸로 골라서 정렬한다
select * from Product --with (index(idx_product))
WHERE CategoryNo > 0;

select CategoryNo, ProductName from Product --with (index(idx_product))
WHERE CategoryNo > 0;

-- 복합 인덱스
CREATE INDEX idx_product_no_name ON Product(CategoryNo, ProductName);
-- CategoryNo로 정렬하고 ProductName으로 2차정렬한다

SELECT ProdUctName
FROM Product
WHERE UnitPrice >=20000;

SELECT ProductNo, ProductName FROM Product WHERE CategoryNo = 1
UNION
SELECT ProductNo, ProductName FROM Product WHERE CategoryNo = 2

select ProducctName, CategoryName 

FROM Product, Category 

select ProductName, CategoryName 
FROM Product, Category 
WHERE Product.CategoryNo = Category.CategoryNo;

SELECT ProductName, CategoryName
--                                                    = 대신 >나 <도 사용 가능
FROM Product INNER JOIN Category ON Product.CategoryNo = Category.CategoryNo;

SELECT 1 + 1 AS p;

SELECT *
FROM Product AS p INNER JOIN Category AS c oN p.CategoryNo = c.CategoryNo;

