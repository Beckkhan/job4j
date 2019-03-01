--1. Написать запрос получение всех продуктов с типом "СЫР".
select * from product as p
inner join type as t on p.type_id = t.id
where t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное".
select * from product as p
where p.name like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product as p
where (extract(month from expired_date) - extract(month from current_date)) = 1;

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product as p
where p.price = (select max(price) from p);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(p.id) from product as p
inner join type as t on p.type_id = t.id
group by t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
select * from product as p
inner join type as t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name from product as p
inner join type as t on p.type_id = t.id
group by t.name having count(product.id) < 10;

--8. Вывести все продукты и их тип.
select p.name, t.name from product as p
inner join type as t on p.type_id = t.id;