drop table if exists sales;
create table sales(id bigint identity primary key, title varchar(255), sale_date date, quantity int, cost decimal);
