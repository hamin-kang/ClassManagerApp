-- admin 출석
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'PRESENT'
FROM user
WHERE user_name = 'admin';

INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'LATE'
FROM user
WHERE user_name = 'admin';

-- user1 출석
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'ABSENT'
FROM user
WHERE user_name = 'user1';

INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'PRESENT'
FROM user
WHERE user_name = 'user1';

-- user2 출석
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'LEAVE_EARLY'
FROM user
WHERE user_name = 'user2';

INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'PRESENT'
FROM user
WHERE user_name = 'user2';

-- user3 출석
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'PRESENT'
FROM user
WHERE user_name = 'user3';

INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'LATE'
FROM user
WHERE user_name = 'user3';

-- user4 출석
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'ABSENT'
FROM user
WHERE user_name = 'user4';

INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'LEAVE_EARLY'
FROM user
WHERE user_name = 'user4';

-- hamin1234 출석
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'PRESENT'
FROM user
WHERE user_name = 'hamin1234';

INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'LATE'
FROM user
WHERE user_name = 'hamin1234';
