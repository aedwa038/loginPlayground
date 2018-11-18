begin;
	CREATE TABLE users (
  		id SERIAL PRIMARY KEY,
  		email TEXT NOT NULL UNIQUE,
  		username text not null unique,
 		password TEXT NOT NULL,
		registered_date TIMESTAMP NOT NULL,
		last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
	);

	INSERT INTO users (email, username, password, registered_date) VALUES ('test@test.com', 'username', '$2a$12$6SnB66J6C9L2O9dk3YeFde9z/ReAgdutkzk/Up.d0oUcS6ncPBbPy', CURRENT_TIMESTAMP);

commit;

DROP DATABASE postgres;