insert into users(id, join_date, name, password, ssn) values(90001, now(), 'User1', 'test111', '701010-1111111');
insert into users(id, join_date, name, password, ssn) values(90002, now(), 'User2', 'test222', '801010-1111111');
insert into users(id, join_date, name, password, ssn) values(90003, now(), 'User3', 'test333', '901010-1111111');

insert into post(description,user_id) values('first data', 90001);
insert into post(description,user_id) values('second data', 90001);