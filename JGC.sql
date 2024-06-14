Select * from sakila.actor;
select count(actor_id) from actor;
show tables;

show columns from actor;
Select * from sakila.film_actor;

#group by
select last_update from actor group by last_update;
#inner join
select * from actor inner join film_actor on actor.actor_id=film_actor.actor_id;