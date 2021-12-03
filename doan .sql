create database doan;
use doan;


create table register(
id int NOT NULL AUTO_INCREMENT primary key,
username nvarchar(50) unique,
email nvarchar(30) unique,
password nvarchar(60) ,
firstname nvarchar(30),
lastname nvarchar(30),
phone varchar(13),
description nvarchar(200),
createdate timestamp default now(), 
updatetime timestamp default now() on update now()
);

create table content(
id int NOT NULL AUTO_INCREMENT primary key,
title nvarchar(200),
brief nvarchar(150),
content nvarchar(1000),
createdate datetime default now(),
updatetime datetime default now() on update now(),
authorId int,
constraint a_content_register foreign key (authorId) references register (id)
);

insert into register(username, email, password) values ('lan','lan@gmail.com','87654321');
update register set username='ngoclan2' where email='lan@gmail.com' ;
insert into register(username, email, password) values ('minh','minh@gmail.com','12345678');
insert into register(username, email, password) values ('tue','tue@gmail.com','tue12345');
insert into register(username, email, password) values ('hoa','hoa@gmail.com','hoa12345');
insert into register(username, email, password) values ('tri','tri@gmail.com','tri12345');
insert into register(username, email, password) values ('khue','khue@gmail.com','khue12345');

insert into content(title,brief,content,authorId) 
values('Thời sự','ngày hôm nay','Thích làm gì thì làm, thích nghe gì thì nghe, thích nói gì thì nói',1);
update content set title='thời sự ngày hôm nay' where id=1; 
insert into content(title,brief,content,authorId) 
values('thời sự','cáp quang biển bị đứt ','cáp quang biển tiếp tục bị đứt',1);
insert into content(title,brief,content,authorId) 
values('thời sự','cách mạng 4.0','Việt Nam đổi mới',1);
insert into content(title,brief,content,authorId) 
values('thời sự','dịch covid','Sống chung với dịch',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hà Nội hôm nay','khí hậu Hà Nội',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','TP HCM hôm nay','khí hậuTP HCM',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hải Phòng hôm nay','khí hậu Hải Phòng',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','Đồng Nai hôm nay','khí hậu Đồng Nai',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','SaPa hôm nay','khí hậu SaPa',1);


--
insert into content(title,brief,content,authorId) 
values('Thời sự','ngày hôm nay','Thích làm gì thì làm, thích nghe gì thì nghe, thích nói gì thì nói',1);
update content set title='thời sự ngày hôm nay' where id=1; 
insert into content(title,brief,content,authorId) 
values('thời sự','cáp quang biển bị đứt ','cáp quang biển tiếp tục bị đứt',1);
insert into content(title,brief,content,authorId) 
values('thời sự','cách mạng 4.0','Việt Nam đổi mới',1);
insert into content(title,brief,content,authorId) 
values('thời sự','dịch covid','Sống chung với dịch',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hà Nội hôm nay','khí hậu Hà Nội',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','TP HCM hôm nay','khí hậuTP HCM',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hải Phòng hôm nay','khí hậu Hải Phòng',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','Đồng Nai hôm nay','khí hậu Đồng Nai',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','SaPa hôm nay','khí hậu SaPa',1);
--  

insert into content(title,brief,content,authorId) 
values('thời tiết','Việt Trì hôm nay','khí hậu Việt Trì',1);
insert into content(title,brief,content,authorId) 
values('thời tiết','Huế hôm nay','khí hậu Huế',1);
insert into content(title,brief,content,authorId) 
values('thiên tai','lũ miền Trung','Khắc phục sự cố lũ',1);
insert into content(title,brief,content,authorId) 
values('thiên tai','sương muối','thiệt hại của sương muối cho nông sản Lâm Đồng',1);

insert into content(title,brief,content,authorId) 
values('du lịch','du lịch SaPa ','SaPa và những cảnh đẹp',1);
insert into content(title,brief,content,authorId) 
values('du lịch','du lịch Đàn Nẵng ','cầu vàng Đà Nẵng',1);
insert into content(title,brief,content,authorId) 
values('du lịch','du lịch Nha Trang ','Nha Trang và địa điểm giải trí',1);

insert into content(title,brief,content) 
values('thị trường','chứng khoáng ','chứng khoáng tăng cao');
insert into content(title,brief,content) 
values('thị trường','xăng dầu hôm nay ','giá xăng dầu bắt đầu giảm');

insert into content(title,brief,content,authorId) 
values('Thời sự','ngày hôm nay','Thích làm gì thì làm, thích nghe gì thì nghe, thích nói gì thì nói',2);
update content set title='thời sự hôm nay' where id=2; 


insert into content(title,brief,content,authorId) 
values('thời sự','cáp quang biển bị đứt ','cáp quang biển tiếp tục bị đứt',2);
insert into content(title,brief,content,authorId) 
values('thời sự','cách mạng 4.0','Việt Nam đổi mới',2);
insert into content(title,brief,content,authorId) 
values('thời sự','dịch covid','Sống chung với dịch',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hà Nội hôm nay','khí hậu Hà Nội',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','TP HCM hôm nay','khí hậuTP HCM',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hải Phòng hôm nay','khí hậu Hải Phòng',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','Đồng Nai hôm nay','khí hậu Đồng Nai',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','SaPa hôm nay','khí hậu SaPa',1);
-- 
insert into content(title,brief,content,authorId) 
values('thời sự','cáp quang biển bị đứt ','cáp quang biển tiếp tục bị đứt',2);
insert into content(title,brief,content,authorId) 
values('thời sự','cách mạng 4.0','Việt Nam đổi mới',2);
insert into content(title,brief,content,authorId) 
values('thời sự','dịch covid','Sống chung với dịch',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hà Nội hôm nay','khí hậu Hà Nội',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','TP HCM hôm nay','khí hậuTP HCM',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','Hải Phòng hôm nay','khí hậu Hải Phòng',2);
insert into content(title,brief,content,authorId) 
values('thời tiết','Đồng Nai hôm nay','khí hậu Đồng Nai',2);






