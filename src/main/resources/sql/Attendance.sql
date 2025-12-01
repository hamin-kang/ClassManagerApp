INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-12-01', 'PRESENT'
FROM user
WHERE user_name = 'hamin1234';
