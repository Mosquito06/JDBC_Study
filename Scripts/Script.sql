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
('1091011', '��ö��', '��ǻ�ͽý���'),
('0792012', '�ְ��', '��Ƽ�̵��'),
('0494013', '�̱���', '��ǻ�Ͱ���');

delete from student where id ='1234567';

select * from student where dept = '��ǻ�Ͱ���';

update student set dept = '��ǻ�Ͱ���' where name ='�ְ��';

select * from student;

delete from student where id = '0792012';