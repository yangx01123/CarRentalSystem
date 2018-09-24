create table IF NOT EXISTS car
(
	primary key(cartype),
	cartype varchar(255) not null,
	available integer not null
);

create table IF NOT EXISTS carrent
(
	primary key(id),
	id integer not null,
	cartype varchar(255) not null,
	startdt TIMESTAMP not null,
	days integer not null, 
	enddt TIMESTAMP not null
);
