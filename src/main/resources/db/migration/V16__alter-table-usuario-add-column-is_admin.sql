ALTER TABLE usuarios ADD is_admin tinyint;
UPDATE usuarios SET is_admin = 1;
