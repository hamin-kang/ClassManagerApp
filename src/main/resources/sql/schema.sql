SET FOREIGN_KEY_CHECKS = 0;

-- 1. project
CREATE TABLE `project` (
   `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `project_name` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. user
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_name` VARCHAR(100) NOT NULL UNIQUE, -- 이메일 등을 고려해 길이 확장
    `password_hash` VARCHAR(255) NOT NULL,
    `full_name` VARCHAR(100) NOT NULL,
    `team_id` BIGINT,
    `birthday` DATE,
    `authorization` ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,

    -- 팀이 삭제되면 유저의 team_id는 NULL 로 변경 (팀원은 남음)
    FOREIGN KEY (`team_id`) REFERENCES `team`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. team
CREATE TABLE `team` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `team_name` VARCHAR(50) NOT NULL,
    `project_id` BIGINT NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (`project_id`) REFERENCES `project`(`id`)
    -- 리더가 삭제된다고 팀이 삭제되는 것은 위험할 수 있으므로 ON DELETE 제약조건 신중히 결정
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. assignment
CREATE TABLE `assignment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `creator_id` BIGINT NOT NULL,
    `assignment_type` ENUM('INDIVIDUAL', 'TEAM') NOT NULL,
    `is_close` BOOLEAN DEFAULT FALSE,
    `due_date` DATETIME NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `presentation_order_team_id` TEXT,

    FOREIGN KEY (`creator_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. submission
CREATE TABLE `submission` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `assignment_id` BIGINT NOT NULL,
    `submitter_user_id` BIGINT NOT NULL,
    `team_id` BIGINT, -- 팀 과제일 경우 어느 팀의 제출인지 기록 (팀 변경 이력 문제 해결)
    `content` TEXT NOT NULL,
    `submitted_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (`assignment_id`) REFERENCES `assignment`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`submitter_user_id`) REFERENCES `user`(`id`),
    FOREIGN KEY (`team_id`) REFERENCES `team`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. attendance
CREATE TABLE `attendance` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `session_date` DATE NOT NULL,
    `status` ENUM('PRESENT', 'ABSENT', 'LATE', 'LEAVE_EARLY') NOT NULL, -- 조퇴 추가 고려
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    -- 한 유저가 같은 날짜에 중복 출석 데이터가 생기지 않도록 유니크 제약 조건 추가 권장
    UNIQUE KEY `unique_attendance` (`user_id`, `session_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7. notice
CREATE TABLE `notice` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `content` TEXT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;