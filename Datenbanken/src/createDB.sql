USE CAP_DB;

DROP TABLE IF EXISTS customers;
CREATE TABLE customers(
	cid int NOT NULL AUTO_INCREMENT,
	cname varchar(15) NOT NULL,
	city varchar(15) NOT NULL,
	discnt decimal(3,2) NOT NULL,
	PRIMARY KEY (cid)
);

DROP TABLE IF EXISTS products;
CREATE TABLE products(
	pid int NOT NULL AUTO_INCREMENT,
	pname varchar(15) NOT NULL,
	city varchar(15) NOT NULL,
	quantity smallint NOT NULL,
	price decimal(10,2) NOT NULL,
	PRIMARY KEY (pid)
);

DROP TABLE IF EXISTS agents;
CREATE TABLE agents(
	aid int NOT NULL AUTO_INCREMENT,
	aname varchar(15) NOT NULL,
	city varchar(15) NOT NULL,
	percent smallint NOT NULL,
	PRIMARY KEY (aid)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders(
	oid int NOT NULL AUTO_INCREMENT,
	month varchar(10) NOT NULL,
	cid int NOT NULL REFERENCES customers(cid),
	pid int NOT NULL REFERENCES products(pid),
	aid int NOT NULL REFERENCES agents(aid),
	qty smallint NOT NULL,
	dollars decimal(10,2) NOT NULL,
	PRIMARY KEY (oid)
);