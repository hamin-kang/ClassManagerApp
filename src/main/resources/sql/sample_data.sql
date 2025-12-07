SET FOREIGN_KEY_CHECKS = 0;

-- 기존 데이터 초기화
TRUNCATE TABLE presentation_order;
TRUNCATE TABLE submission;
TRUNCATE TABLE attendance;
TRUNCATE TABLE assignment;
TRUNCATE TABLE user;
TRUNCATE TABLE team;
TRUNCATE TABLE project;

-- Project 생성
INSERT INTO project (project_name) VALUES
    ('자바 미니 프로젝트'),
    ('자바 파이널 프로젝트');

-- User 생성 (총 21명: 관리자 1 + 학생 20)
INSERT INTO user (user_name, password_hash, full_name, birthday, authorization)
VALUES ('admin', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '관리자', '1990-01-01', 'ADMIN');

-- 학생 20명 (student1 ~ student20)
INSERT INTO user (user_name, password_hash, full_name, birthday, authorization) VALUES
    ('student1', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '김철수', '1998-01-01', 'USER'),
    ('student2', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '이영희', '1999-02-02', 'USER'),
    ('student3', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '박민수', '1998-03-03', 'USER'),
    ('student4', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '최지우', '2000-04-04', 'USER'),
    ('student5', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '정우성', '1997-05-05', 'USER'),
    ('student6', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '강동원', '1999-06-06', 'USER'),
    ('student7', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '조인성', '1998-07-07', 'USER'),
    ('student8', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '한효주', '2000-08-08', 'USER'),
    ('student9', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '송혜교', '1996-09-09', 'USER'),
    ('student10', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '전지현', '1999-10-10', 'USER'),
    ('student11', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '이정재', '1998-11-11', 'USER'),
    ('student12', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '공유', '2001-12-12', 'USER'),
    ('student13', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '아이유', '1997-01-13', 'USER'),
    ('student14', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '수지', '1999-02-14', 'USER'),
    ('student15', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '박서준', '1998-03-15', 'USER'),
    ('student16', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '김다미', '2000-04-16', 'USER'),
    ('student17', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '최우식', '1999-05-17', 'USER'),
    ('student18', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '남주혁', '2000-06-18', 'USER'),
    ('student19', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '김태리', '1995-07-19', 'USER'),
    ('student20', '$2a$10$E4m98o7SEQA7oJOuNUms2O3sIdZQiyKbThwBKFJJAN1jd67zpWXiW', '현빈', '1997-08-20', 'USER');

-- Team 데이터 생성 (5개 팀)
INSERT INTO team (team_name, project_id) VALUES
    ('1조 - 자바킹', 1),
    ('2조 - 코딩마스터', 1),
    ('3조 - 버그헌터', 1),
    ('4조 - 풀스택', 1),
    ('5조 - 배포성공', 1);

-- User 에 팀 배정 (4명씩 5개 조)
UPDATE user SET team_id = 1 WHERE id BETWEEN 2 AND 5;   -- 1조
UPDATE user SET team_id = 2 WHERE id BETWEEN 6 AND 9;   -- 2조
UPDATE user SET team_id = 3 WHERE id BETWEEN 10 AND 13; -- 3조
UPDATE user SET team_id = 4 WHERE id BETWEEN 14 AND 17; -- 4조
UPDATE user SET team_id = 5 WHERE id BETWEEN 18 AND 21; -- 5조

-- Assignment (과제) 데이터 생성 (개인 5개, 팀 2개)
INSERT INTO assignment (title, content, creator_id, assignment_type, is_close, due_date) VALUES
-- 개인 과제
('Java 기초 문법 정리', '변수, 자료형, 연산자에 대해 정리하여 제출하세요.', 1, 'INDIVIDUAL', TRUE, '2025-10-05 23:59:59'),
('객체지향의 이해', '클래스와 객체, 상속의 개념을 서술하세요.', 1, 'INDIVIDUAL', TRUE, '2025-10-12 23:59:59'),
('컬렉션 프레임워크 활용', 'ArrayList와 HashMap을 활용한 예제 코드를 작성하세요.', 1, 'INDIVIDUAL', TRUE, '2025-10-19 23:59:59'),
('IO 및 네트워크 프로그래밍', '파일 입출력과 소켓 통신을 이용한 간단한 채팅 프로그램을 만드세요.', 1, 'INDIVIDUAL', FALSE, '2025-12-31 23:59:59'),
('SQL 기초 문제 풀이', '첨부된 SQL 연습문제 20개를 풀어서 제출하세요.', 1, 'INDIVIDUAL', FALSE, '2025-01-10 23:59:59'),

-- 팀 과제
('중간 프로젝트 기획서', '팀별 주제 선정 및 기획 의도를 담은 문서를 제출하세요.', 1, 'TEAM', TRUE, '2025-10-25 23:59:59'),
('최종 프로젝트 결과물', '소스코드, PPT, 시연 영상을 포함하여 제출하세요.', 1, 'TEAM', FALSE, '2025-01-20 23:59:59');

-- 7. Submission (제출) 데이터 대량 생성

-- [과제 1: Java 기초 문법] - 전원 제출
INSERT INTO submission (assignment_id, submitter_user_id, team_id, is_submitted)
SELECT 1, id, null, TRUE
FROM user
WHERE id BETWEEN 2 AND 21;

-- -- 생성된 submission.id 에 대해 내용 입력
-- INSERT INTO submission_content (submission_id, content)
-- SELECT s.id, CONCAT(u.full_name, ' - 기초 문법 정리 과제 제출 내용입니다.')
-- FROM submission s
--          JOIN user u ON s.submitter_user_id = u.id
-- WHERE s.assignment_id = 1;

-- 과제 2: 제출자만 등록
INSERT INTO submission (assignment_id, submitter_user_id, team_id, is_submitted)
SELECT 2, id, null, TRUE
FROM user
WHERE id BETWEEN 2 AND 21;

-- -- 내용 입력
-- INSERT INTO submission_content (submission_id, content)
-- SELECT s.id, CONCAT(u.full_name, ' - 객체지향 과제 제출본입니다.')
-- FROM submission s
--          JOIN user u ON s.submitter_user_id = u.id
-- WHERE s.assignment_id = 2;

-- 과제 3
INSERT INTO submission (assignment_id, submitter_user_id, team_id, is_submitted)
SELECT 3, id, null, false
FROM user
WHERE id BETWEEN 2 AND 21;

-- -- 내용 입력
-- INSERT INTO submission_content (submission_id, content)
-- SELECT s.id, '컬렉션 프레임워크 과제 제출합니다.'
-- FROM submission s
-- WHERE s.assignment_id = 3;

-- 과제 4
INSERT INTO submission (assignment_id, submitter_user_id, team_id, is_submitted)
SELECT 4, id, null, false
FROM user
WHERE id BETWEEN 2 AND 21;

-- -- content
-- INSERT INTO submission_content (submission_id, content)
-- SELECT id, CONCAT('IO 및 네트워크 과제 제출 - 제출자 ID: ', submitter_user_id)
-- FROM submission
-- WHERE assignment_id = 4;

-- 과제 5
INSERT INTO submission (assignment_id, submitter_user_id, team_id, is_submitted)
SELECT 5, id, null, false
FROM user
WHERE id BETWEEN 2 AND 21;

-- 팀 과제 6
INSERT INTO submission (assignment_id, submitter_user_id, team_id, is_submitted) VALUES
                                                                                     (6, 2, 1, TRUE),
                                                                                     (6, 6, 2, TRUE),
                                                                                     (6, 10, 3, TRUE),
                                                                                     (6, 14, 4, TRUE),
                                                                                     (6, 18, 5, TRUE);

-- -- content
-- INSERT INTO submission_content (submission_id, content)
-- SELECT id, CONCAT(team_id, '조 기획서 제출본입니다.')
-- FROM submission
-- WHERE assignment_id = 6;

-- 팀 과제 7
INSERT INTO submission (assignment_id, submitter_user_id, team_id, is_submitted) VALUES
                                                                                     (7, null, 1, FALSE),
                                                                                     (7, 6, 2, TRUE),
                                                                                     (7, null, 3, FALSE),
                                                                                     (7, 14, 4, TRUE),
                                                                                     (7, null, 5, FALSE);



-- Attendance (출석) 데이터 생성 (최근 25일치)
-- 날짜별로 상황 다르게 설정
-- Day 6
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-06',
       CASE
           WHEN id IN (7) THEN 'ABSENT'
           WHEN id IN (3) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 7
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-07',
       CASE
           WHEN id IN (10, 17) THEN 'ABSENT'
           WHEN id IN (4) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 8
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-08',
       CASE
           WHEN id IN (6) THEN 'ABSENT'
           WHEN id IN (12, 15) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 9
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-09',
       CASE
           WHEN id IN (21) THEN 'ABSENT'
           WHEN id IN (9) THEN 'LATE'
           WHEN id IN (5) THEN 'LEAVE_EARLY'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 10
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-10',
       CASE
           WHEN id IN (4, 13) THEN 'ABSENT'
           WHEN id IN (19) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 11
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-11',
       CASE
           WHEN id IN (3) THEN 'ABSENT'
           WHEN id IN (8, 16) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 12
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-12',
       CASE
           WHEN id IN (11, 20) THEN 'ABSENT'
           WHEN id IN (6) THEN 'LATE'
           WHEN id IN (17) THEN 'LEAVE_EARLY'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 13
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-13',
       CASE
           WHEN id IN (5) THEN 'ABSENT'
           WHEN id IN (2, 14) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 14
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-14',
       CASE
           WHEN id IN (19) THEN 'ABSENT'
           WHEN id IN (9) THEN 'LATE'
           WHEN id IN (21) THEN 'LEAVE_EARLY'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 15
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-15',
       CASE
           WHEN id IN (8, 18) THEN 'ABSENT'
           WHEN id IN (3) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 16
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-16',
       CASE
           WHEN id IN (12) THEN 'ABSENT'
           WHEN id IN (7) THEN 'LATE'
           WHEN id IN (4) THEN 'LEAVE_EARLY'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 17
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-17',
       CASE
           WHEN id IN (6, 13) THEN 'ABSENT'
           WHEN id IN (10) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 18
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-18',
       CASE
           WHEN id IN (2) THEN 'ABSENT'
           WHEN id IN (11, 17) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 19
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-19',
       CASE
           WHEN id IN (14, 20) THEN 'ABSENT'
           WHEN id IN (5) THEN 'LATE'
           WHEN id IN (16) THEN 'LEAVE_EARLY'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 20
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-20',
       CASE
           WHEN id IN (9) THEN 'ABSENT'
           WHEN id IN (18) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 21
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-21',
       CASE
           WHEN id IN (7, 15) THEN 'ABSENT'
           WHEN id IN (3) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 22
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-22',
       CASE
           WHEN id IN (21) THEN 'ABSENT'
           WHEN id IN (6, 12) THEN 'LATE'
           WHEN id IN (10) THEN 'LEAVE_EARLY'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 23
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-23',
       CASE
           WHEN id IN (8) THEN 'ABSENT'
           WHEN id IN (17) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 24
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-24',
       CASE
           WHEN id IN (4, 19) THEN 'ABSENT'
           WHEN id IN (2) THEN 'LATE'
           WHEN id IN (13) THEN 'LEAVE_EARLY'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

-- Day 25
INSERT INTO attendance (user_id, session_date, status)
SELECT id, '2025-11-25',
       CASE
           WHEN id IN (6) THEN 'ABSENT'
           WHEN id IN (15, 20) THEN 'LATE'
           ELSE 'PRESENT'
       END
FROM user WHERE id BETWEEN 2 AND 21;

SET FOREIGN_KEY_CHECKS = 1;





-- 9. Notice
INSERT INTO notice (content) VALUES (

'**수업 정보**
이영희(도연) 강사님 : ai.edu.kingsmile@gmail.com
전화번호 : 010-9872-0202
**네트워크 공유폴더 위치** : 192.168.5.4 / DESKTOP-K2CFNHS
id : java
password : java
**네이버 공유 폴더** 링크: https://mybox.naver.com/share/list?shareKey=UqveG0e0HulOZeIWmhrXOJMOHRrQhvpPmx-xftIxgcQD
pwd : kosa
**오픈채팅**
https://invite.kakao.com/tc/C6aawhRARR
**카페** 링크: https://cafe.naver.com/javajoblink
**일자별 정리** 링크: https://docs.google.com/document/d/1kSutXoDQBuDjVuWRES2y8mYX9llnKIJE3WJJ2wUjCp8/edit
**Discord**
https://discord.gg/smGATKfm'
);
SET FOREIGN_KEY_CHECKS = 1;