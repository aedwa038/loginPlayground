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

INSERT INTO users (email, username, password) VALUES ('test@test.com', 'username', 'password');

SELECT email, username from users where users.id = 1

DROP DATABASE postgres;


UPDATE users
SET last_login = DEFAULT
WHERE
 users.id = 1;