BEGIN TRANSACTION;
CREATE TABLE access (id INTEGER PRIMARY KEY);
CREATE TABLE data (_id INTEGER PRIMARY KEY, type TEXT, city TEXT, region TEXT, street TEXT, home TEXT, room TEXT, floor TEXT, storey TEXT, material TEXT, gross_area TEXT, living_space TEXT, kitchen_area TEXT, cost TEXT, comment TEXT);
CREATE TABLE system (version TEXT);
COMMIT;
