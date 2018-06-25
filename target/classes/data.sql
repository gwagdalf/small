insert into member(id, name, email, passwd, reg_date)
values( null, '1234', '1234', '{bcrypt}$2a$10$0IKjNgE8fn.5oTSc4V0Cj.9NArYsSZYEZl7NVwV/cPP27dKDOGy76', now());

insert into member(id, name, email, passwd, reg_date)
values( null, 'test4plan', 'test4plan@ebay.com', '{bcrypt}$2a$10$0IKjNgE8fn.5oTSc4V0Cj.9NArYsSZYEZl7NVwV/cPP27dKDOGy76', now());


insert into member(id, name, email, passwd, reg_date)
values( null, 'kim', 'urstory@gmail.com', '{bcrypt}$2a$10$0IKjNgE8fn.5oTSc4V0Cj.9NArYsSZYEZl7NVwV/cPP27dKDOGy76', now());

insert into member(id, name, email, passwd, reg_date)
values( null, 'kang', 'carami@gmail.com', '{bcrypt}$2a$10$0IKjNgE8fn.5oTSc4V0Cj.9NArYsSZYEZl7NVwV/cPP27dKDOGy76', now());

insert into member_role(id, name, member_id)
values( null, 'USER', 1);

insert into member_role(id, name, member_id)
values( null, 'ADMIN', 1);

insert into member_role(id, name, member_id)
values( null, 'USER', 2);