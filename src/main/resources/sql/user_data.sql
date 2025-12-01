INSERT INTO user (user_name, password_hash, full_name, team_id, birthday, authorization)
VALUES ( -- 관리자용 insert 문
           'admin',
           '$2a$10$H1.xHwCVXknsbx2LccFDsuNyZ4hw/G9t29yzlA0o9E/Dfw489ZWlC', -- 비번: 1234
           '관리자',
           NULL,
           '2000-01-01',
           'ADMINa'
       );
INSERT INTO user (user_name, password_hash, full_name, team_id, birthday, authorization)
VALUES ( -- 유저용 insert 문
           'user1',
           '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', -- 1234
           'user1',
           NULL,
           '2000-02-08',
           'USER'
       );
INSERT INTO user (user_name, password_hash, full_name, team_id, birthday, authorization)
VALUES ( -- 유저용 insert 문
           'user2',
           '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', -- 1234
           'user2',
           NULL,
           '2000-02-08',
           'USER'
       );
INSERT INTO user (user_name, password_hash, full_name, team_id, birthday, authorization)
VALUES ( -- 유저용 insert 문
           'user3',
           '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', -- 1234
           'user3',
           NULL,
           '2000-02-08',
           'USER'
       );
INSERT INTO user (user_name, password_hash, full_name, team_id, birthday, authorization)
VALUES ( -- 유저용 insert 문
           'user4',
           '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', -- 1234
           'user4',
           NULL,
           '2000-02-08',
           'USER'
       );
INSERT INTO user (user_name, password_hash, full_name, team_id, birthday, authorization)
VALUES ( -- 유저용 insert 문
           'hamin1234',
           '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', -- 비번: 1234
           '강화민',
           NULL,
           '2000-02-08',
           'USER'
       );