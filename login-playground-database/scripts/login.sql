begin;
	CREATE TABLE users (
  		id SERIAL PRIMARY KEY,
  		email TEXT NOT NULL UNIQUE,
  		username text not null unique,
 		password TEXT NOT NULL
	);

	INSERT INTO users (email, username, password) VALUES ('test@test.com', 'username', 'password');

commit;

DROP DATABASE postgres;