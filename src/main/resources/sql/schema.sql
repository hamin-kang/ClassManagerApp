-- 테이블 생성 순서나 순환 참조 문제 방지를 위해 외래 키 체크를 잠시 끔
SET FOREIGN_KEY_CHECKS = 0;

-- project ---------------------------------------------------------
CREATE TABLE `project` (
   `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   `project_name` VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- user ------------------------------------------------------------
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_name` VARCHAR(50) NOT NULL UNIQUE,
    `password_hash` VARCHAR(255) NOT NULL,
    `full_name` VARCHAR(100) NOT NULL,
    `team_id` BIGINT,
    `birthday` DATE,
    `authorization` ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER', -- 오타 방지를 위해 ENUM
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`team_id`) REFERENCES `team`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- team ------------------------------------------------------------
CREATE TABLE `team` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `team_name` VARCHAR(50) NOT NULL,
    `project_id` BIGINT NOT NULL,
    `leader_id` BIGINT NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (`project_id`) REFERENCES `project`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`leader_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- assignment -------------------------------------------------------
CREATE TABLE `assignment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    `creator_id` BIGINT NOT NULL,
    `assignment_type` ENUM('INDIVIDUAL', 'TEAM') NOT NULL,
    `team_id` BIGINT,
    `is_close` BOOLEAN DEFAULT FALSE,
    `presentation_order_team_id` JSON, -- JSON 타입이 데이터 관리에 유리함
    `due_date` DATETIME NOT NULL,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (`creator_id`) REFERENCES `user`(`id`),
    FOREIGN KEY (`team_id`) REFERENCES `team`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- submission -------------------------------------------------------
CREATE TABLE `submission` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `assignment_id` BIGINT NOT NULL,
    `submitter_user_id` BIGINT NOT NULL,
    `content` TEXT NOT NULL,
    `submitted_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (`assignment_id`) REFERENCES `assignment`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`submitter_user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Attendance -------------------------------------------------------
CREATE TABLE `attendance` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `session_date` DATE NOT NULL,
    `status` ENUM('PRESENT', 'ABSENT', 'LATE') NOT NULL,

    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 외래 키 체크를 다시 켬
SET FOREIGN_KEY_CHECKS = 1;