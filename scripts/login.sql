begin;
	CREATE TABLE users (
  		id SERIAL,
  		email TEXT NOT NULL UNIQUE,
  		username text not null unique,
 		password TEXT NOT NULL,
		registered_date TIMESTAMP NOT NULL,
		last_login TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		PRIMARY KEY (id)
	);

	CREATE TABLE stuff (
		id SERIAL,
		user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
		data TEXT,
		content_type TEXT,
		updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		PRIMARY KEY(id, user_id)
	);

	INSERT INTO users (id, email, username, password, registered_date) VALUES (0, 'test@test.com', 'username', '$2a$12$6SnB66J6C9L2O9dk3YeFde9z/ReAgdutkzk/Up.d0oUcS6ncPBbPy', CURRENT_TIMESTAMP);
	INSERT INTO stuff (user_id, data, content_type) VALUES (0, 'stuff 1', 'text/plain');
	INSERT INTO stuff (user_id, data,content_type) VALUES (0, 'stuff 2', 'text/plain');
	INSERT INTO stuff (user_id, data,content_type) VALUES (0, 'stuff 3', 'text/plain');
	INSERT INTO stuff (user_id, data,content_type) VALUES (0, 'stuff 4', 'text/plain');
commit;

DROP DATABASE postgres;