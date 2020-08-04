sudo -u postgres psql


CREATE DATABASE converter;
CREATE USER test WITH password 'test';
GRANT ALL ON DATABASE converter TO test;
psql -h localhost converter test