INSERT INTO user (user_name, password_hash, full_name, team_id, birthday, authorization)
VALUES ( -- 관리자용 insert 문
           'admin',
           '$2a$10$O03r9L30N1yXgA5wH2o0f.7Yk/PqD3uB3X6Y8cT5W7M4V.zHk2', -- 1234
           '관리자',
           NULL,
           '2000-01-01',
           'ADMIN'
       );