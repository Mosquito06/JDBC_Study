show databases;

create table student(
	id char(7) not null primary key,
	name varchar(10),
	dept varchar(20)
);
drop table student;

show tables;

desc student;

insert into student values
('1091011', '김철수', '컴퓨터시스템'),
('0792012', '최고봉', '멀티미디어'),
('0494013', '이기자', '컴퓨터공학');

delete from student where id ='1234567';

select * from student where dept = '컴퓨터공학';

update student set dept = '컴퓨터공학' where name ='최고봉';

select * from student;

delete from student where id = '0792012';