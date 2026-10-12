-- Sample Initial Students Data
INSERT INTO students (id, roll_number, name, email, department, semester, created_at)
VALUES (1, '23CS001', 'Rahul Sharma', 'rahul.sharma@example.com', 'Computer Science', 6, NOW())
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO students (id, roll_number, name, email, department, semester, created_at)
VALUES (2, '23CS002', 'Priya Patel', 'priya.patel@example.com', 'Computer Science', 6, NOW())
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO students (id, roll_number, name, email, department, semester, created_at)
VALUES (3, '23EC015', 'Kiran Kumar', 'kiran.kumar@example.com', 'Electronics & Comm', 6, NOW())
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO students (id, roll_number, name, email, department, semester, created_at)
VALUES (4, '23IT021', 'Ananya Reddy', 'ananya.reddy@example.com', 'Information Tech', 6, NOW())
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO students (id, roll_number, name, email, department, semester, created_at)
VALUES (5, '23CS045', 'Sneha Verma', 'sneha.verma@example.com', 'Computer Science', 6, NOW())
ON DUPLICATE KEY UPDATE name=name;

-- Sample Preloaded Users (Faculty & Students)
-- Faculty Account (Username: faculty101, Password: password123)
INSERT INTO users (id, username, password, full_name, role, student_id)
VALUES (1, 'faculty101', 'password123', 'Prof. Rajesh Sharma', 'FACULTY', NULL)
ON DUPLICATE KEY UPDATE username=username;

-- Student 1 Account (Username: 23CS001, Password: password123)
INSERT INTO users (id, username, password, full_name, role, student_id)
VALUES (2, '23CS001', 'password123', 'Rahul Sharma', 'STUDENT', 1)
ON DUPLICATE KEY UPDATE username=username;

-- Student 2 Account (Username: 23CS002, Password: password123)
INSERT INTO users (id, username, password, full_name, role, student_id)
VALUES (3, '23CS002', 'password123', 'Priya Patel', 'STUDENT', 2)
ON DUPLICATE KEY UPDATE username=username;

-- Sample Attendance Records for Testing (Rahul Sharma: 3 classes attended, 1 absent = 75%)
INSERT INTO attendance (id, student_id, attendance_date, status, remarks)
VALUES (1, 1, '2026-10-01', 'PRESENT', 'On time')
ON DUPLICATE KEY UPDATE status=status;

INSERT INTO attendance (id, student_id, attendance_date, status, remarks)
VALUES (2, 1, '2026-10-02', 'PRESENT', 'On time')
ON DUPLICATE KEY UPDATE status=status;

INSERT INTO attendance (id, student_id, attendance_date, status, remarks)
VALUES (3, 1, '2026-10-03', 'ABSENT', 'Sick leave')
ON DUPLICATE KEY UPDATE status=status;

INSERT INTO attendance (id, student_id, attendance_date, status, remarks)
VALUES (4, 1, '2026-10-04', 'PRESENT', 'On time')
ON DUPLICATE KEY UPDATE status=status;
