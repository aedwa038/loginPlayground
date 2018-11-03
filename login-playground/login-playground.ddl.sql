create database LOGIN_DB;

set database = LOGIN_DB;	

begin;
	CREATE TABLE users (
  		id SERIAL PRIMARY KEY,
  		email TEXT NOT NULL UNIQUE,
  		username text not null unique,
 		user_pass TEXT NOT NULL
	);

commit;

ALTER TABLE users OWNER TO login;



INSERT INTO users (email, username, user_pass) VALUES ("test@test.com" "username", "password");


DROP DATABASE postgres;


	CREATE TABLE users (
  	       id SERIAL PRIMARY KEY,
  	       	  email TEXT NOT NULL UNIQUE,
  		  	username text not null unique,
 				 password TEXT NOT NULL
				 );