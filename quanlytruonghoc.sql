-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:4306
-- Thời gian đã tạo: Th9 07, 2024 lúc 06:19 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlytruonghoc`
--
CREATE DATABASE IF NOT EXISTS `quanlytruonghoc` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `quanlytruonghoc`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `academictranscript`
--

CREATE TABLE `academictranscript` (
  `academictranscipt_id` int(11) NOT NULL,
  `student_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `academictranscript`
--

INSERT INTO `academictranscript` (`academictranscipt_id`, `student_id`) VALUES
(1, '0000000001'),
(3, '0000000002'),
(4, '0000000003'),
(5, '0000000009'),
(9, '0000000010'),
(8, '0000000011'),
(15, '0000000012'),
(16, '0000000013'),
(17, '0000000014'),
(18, '0000000015'),
(19, '0000000016'),
(20, '0000000017'),
(21, '0000000018'),
(22, '0000000019'),
(23, '0000000020'),
(24, '0000000021'),
(12, '0000001456'),
(13, '1231231231'),
(14, '1231231232');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `academictranscriptdetail`
--

CREATE TABLE `academictranscriptdetail` (
  `academictransciptdetail_id` int(11) NOT NULL,
  `academic_ability` varchar(255) DEFAULT NULL,
  `review_of_teachers_and_parents` varchar(255) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `semester_year` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `academictranscipt_id` int(11) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `academictranscriptdetail`
--

INSERT INTO `academictranscriptdetail` (`academictransciptdetail_id`, `academic_ability`, `review_of_teachers_and_parents`, `score`, `semester_year`, `time`, `academictranscipt_id`, `student_id`, `teacher_id`) VALUES
(14, 'Giỏi', 'Em rất ngoan và có tiến bộ', 8.86, 'Năm 2024 học kỳ 2', '2024-06-12', 1, '0000000001', '0000000023'),
(15, 'Giỏi', 'Em học rất tốt\r\n\r\n', 9, 'Năm 2024 học kỳ 2', '2024-05-12', 4, '0000000003', '0000000023'),
(16, 'Giỏi', 'Em học rất tốt', 8.03, 'Năm 2024 học kỳ 2', '2024-05-12', 5, '0000000009', '0000000023'),
(17, 'Khá', 'Em học rất tốt', 7.66, 'Năm 2024 học kỳ 2', '2024-05-12', 9, '0000000010', '0000000023'),
(18, 'Khá', 'Em học rất tốt', 7.87, 'Năm 2024 học kỳ 2', '2024-05-12', 8, '0000000011', '0000000023'),
(19, 'Giỏi', 'Em học rất tốt', 8.09, 'Năm 2024 học kỳ 2', '2024-05-12', 13, '1231231231', '0000000023'),
(20, 'Khá', 'Em học rất tốt', 7.13, 'Năm 2024 học kỳ 2', '2024-05-12', 14, '1231231232', '0000000023');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `active`, `email`, `password`) VALUES
(1, b'1', 'dang@gmail.com', '$2a$12$0yI7DMgYUMBzXmzrqKEkIOy5D.pg3OXR5ytxtYKDNHYpxyTawdc2G'),
(2, b'1', 'giang@gmail.com', '$2a$10$L5PNLdwhefwJGRj8SJChIO4r56Vli2elOOtyRQly3zrBS4FibFyHu'),
(3, b'1', 'dung@gmail.com', '$2a$10$0OL2JCmvo5SKcqyZn0Kxpe61iYGEjvwpleubuEtW7bcKlrDQiNQcO'),
(4, b'1', 'diep@gmail.com', '$2a$10$aP5Tkps7wvfjzhOzG/e2Nukaxz4xVyWDVr8P81qkH0aDUUETzIDxW'),
(5, b'1', 'thanh@gmail.com', '$2a$10$xgLzLI2vmUjYpIYUEZFlA.UpAVUuavGvcgCXCNII53xyg2hAjgpUG'),
(6, b'1', 'an@gmail.com', '$2a$10$WRxUxRTmuj5dwISk.Ei0dunFOOO8feRLSOWv90AO7uqyjFtJtGITC'),
(8, b'1', 'ha@gmail.com', '$2a$10$tc2ntT1cN46b9OwRnR/qcO29goAjnVv6QvWUzCZpzbQYwy1zZJMrm'),
(12, b'1', 'tho@gmail.com', '$2a$10$WAsSGQSjLo/K6MKLHvbeyuU26G.EzUvy8X9RlSODitvXLDh2TFvHC'),
(13, b'1', 'nhi@gmail.com', '$2a$10$q9NF09oDxsnp1vEb9l8UteqJVs5hVM5ltNlYNkNV36TdgTkCDkfwG'),
(14, b'1', 'hai@gmail.com', '$2a$10$HqqSF1vlVUFNzXeHFuZdLubwG1/FqTn7sfjK/aEnmokDy7wwKA0Q6'),
(15, b'1', 'tung@gmail.com', '$2a$10$eQxuYag.eSnQ3PIErjyXvOf9EeE/S/E/TMx672NkZyFAGm7/4mvUC'),
(17, b'1', 'ngoc@gmail.com', '$2a$10$XXTEyKkbjg5iUWlX/lt1PeTNt7p2eYPgAosM6ieM5RROgs61JmDv.'),
(18, b'1', 'dung1@gmail.com', '$2a$10$nXI/EF6/WocWW4TSHWBJHu7HzS8eZ1OwCSuzEQRD8AfzWZKA8AiiG'),
(21, b'1', 'dung2@gmail.com', '$2a$10$4X/3GsgAVdZeLZTM77LUTu4o1N9VYKBKkFKU7ZVSo4y8PVea/gA0m'),
(23, b'1', 'cuong@gmail.com', '$2a$10$C1.PqAwkaK84CuZB4H7q3.KSee4EZY0d9odeFi7IBl9JXK2Nj6DCu'),
(25, b'1', 'tho1@gmail.com', '$2a$10$pf.7rih2gGMmQ8R3.qiQzuSWxySu/6Wr9BFXDpxy9GKi5fM.BAV0q'),
(26, b'1', 'linh@gmail.com', '$2a$10$XDsPzgcQz74C2QoVJ050uOyxckuWTG2UWJxCWo4.ow9nY5lLdjHbq'),
(27, b'1', 'duan@gmail.com', '$2a$10$QZYyKdqDqKa.UhdyPfZzBe.LUWTnNRokZMmttsmhO6LaCkZqD7jGi'),
(28, b'1', 'nguyet@gmail.com', '$2a$10$S9fqBXfAB.K9AXI8zzXRj.a8RL.BurhKNXsIhgH3QfR63R7vuz3Rm'),
(29, b'1', 'dieulinh@gmail.com', '$2a$10$9ddzcatTlqZ.bEVbqBZOpeO4qCBKcChgLyHW4CoQB6Dj/3Au7QXfW'),
(30, b'1', 'vanlinh@gmail.com', '$2a$10$/PKQKHdGTcBipAxHJ8/2DuD3Wlhv1Gk9LBZDehYDeCdNvP3iJWLtu'),
(31, b'1', 'ducthe@gmail.com', '$2a$10$0y/PQjXehM8NkPDI9QD2OeQciaOq6NSjWkb30L61zUsB/Dej7vpbm'),
(32, b'1', 'manhtuan@gmail.com', '$2a$10$P5eAy4UtV4vWf9P0PooUUO6fGJ3cEFbXUgFKLBE3F7TNOT.N/Mlta'),
(33, b'1', 'thiha@gmail.com', '$2a$10$V3cuqfTdzRTv2MQDcTXd/Osza9a0PEhgQJq90s1UKxDp6.t4vpPfa'),
(34, b'1', 'vanloc@gmail.com', '$2a$10$H8CbYkchC63shjWZEwp23OQbUfqknq/VDc5SmkQgBWFncSwAs/OUK'),
(35, b'1', 'thean@gmail.com', '$2a$10$t4Lk0r.6KpTFtSjwZmUSj.WWYFJncre4WGVsr24PKLYr11qlX53iq'),
(36, b'1', 'ngan@gmail.com', '$2a$10$GCfY3qZud9Hu/TFgIa6kEeD2ip7I7mHHbPe9GFgfpezaG4ZM8CEUK'),
(37, b'1', 'huong@gmail.com', '$2a$10$wmmQa4FdqqeifKGkdcdI4uIV6aGvYTDiUQI5qUVG535H8v5ierf6a');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `classroom`
--

CREATE TABLE `classroom` (
  `classroom_id` int(11) NOT NULL,
  `classroom_block` varchar(255) DEFAULT NULL,
  `classroom_name` varchar(255) DEFAULT NULL,
  `school_year` int(11) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `classroom`
--

INSERT INTO `classroom` (`classroom_id`, `classroom_block`, `classroom_name`, `school_year`, `teacher_id`) VALUES
(1, 'A', '11a1', 2022, '0000000023'),
(2, 'A', '11a2', 2022, '0000000023'),
(3, 'B', '12a1', 2021, '0000000004'),
(4, 'B', '12a2', 2021, '0000000004'),
(6, 'D', '10a1', 2023, '0000000007');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `classroom_subject`
--

CREATE TABLE `classroom_subject` (
  `subject_id` int(11) NOT NULL,
  `classroom_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `classroom_subject`
--

INSERT INTO `classroom_subject` (`subject_id`, `classroom_id`) VALUES
(1, 2),
(3, 2),
(4, 2),
(5, 2),
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(2, 3),
(3, 3),
(1, 4),
(3, 4),
(4, 4),
(11, 6),
(12, 6),
(13, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `parent`
--

CREATE TABLE `parent` (
  `parent_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `parent_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `parent`
--

INSERT INTO `parent` (`parent_id`, `address`, `date_of_birth`, `parent_name`, `phone`, `student_id`) VALUES
(1, 'Hà Nội', '1982-02-12', 'Pham Van An', '1231233341', '0000000001'),
(3, 'Hà Nội', '1978-02-12', 'Phạm Việt Linh', '1231233341', '0000000002'),
(4, 'Hà Nội', '1982-02-12', 'Hoàng Thế Công', '1231233341', '0000000003'),
(5, 'Hà Nội', '1982-03-23', 'Bùi Đức Thịnh', '1231231231', '0000000009'),
(10, 'Hà Nội', '1990-02-12', 'Bùi Đức Tâm', '1231231231', '0000000011'),
(11, 'Hà Nội', '1981-02-12', 'Phạm Thị Hường', '1231231233', '0000000010'),
(14, 'Hà Nội', '1982-02-23', 'Pham Van A', '1231231231', '0000001456'),
(15, 'hà Nội', '1982-02-12', 'Dương Hoàng Hải', '1231231231', '1231231231'),
(16, 'Hà Nội', '1987-02-12', 'Bùi Văn Hùng', '1231231231', '1231231232'),
(17, 'Hà Nội', '1982-02-12', 'Phạm Văn Thanh', '1231231231', '0000000012'),
(18, 'Hà Nội', '1971-02-12', 'Nguyễn Đình Dũng', '1231312311', '0000000013'),
(19, 'Sơn La', '1971-02-12', 'Hoàng Văn Viết', '1231231231', '0000000014'),
(20, 'Bắc Giang', '1988-02-12', 'Bùi Thế Linh', '1231231231', '0000000015'),
(21, 'Hà Nam', '1988-02-12', 'Phạm Văn Tuấn', '1231231231', '0000000016'),
(22, 'Hà Nội', '1989-11-12', 'Ma Đức An', '1231231231', '0000000017'),
(23, 'Hà Nội', '1981-02-12', 'Vũ Mạnh Cường', '1231231231', '0000000018'),
(24, 'Hà Nội', '1981-01-12', 'Bùi Thế An', '1231231231', '0000000019'),
(25, 'Hà Nội', '1971-02-12', 'Bùi Văn Đức', '1231231231', '0000000020'),
(26, 'Thái Bình', '1982-12-12', 'Từ Thế Huy', '1231231231', '0000000021');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `registernotebook`
--

CREATE TABLE `registernotebook` (
  `registernotebook_id` int(11) NOT NULL,
  `classroom_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `registernotebook`
--

INSERT INTO `registernotebook` (`registernotebook_id`, `classroom_id`) VALUES
(1, 1),
(2, 2),
(4, 3),
(5, 4),
(15, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `registernotebookdetail`
--

CREATE TABLE `registernotebookdetail` (
  `registernotebookdetail_id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `lesson` varchar(255) DEFAULT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `timeteaching` date DEFAULT NULL,
  `classroom_id` int(11) DEFAULT NULL,
  `registernotebook_id` int(11) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `registernotebookdetail`
--

INSERT INTO `registernotebookdetail` (`registernotebookdetail_id`, `content`, `lesson`, `semester`, `timeteaching`, `classroom_id`, `registernotebook_id`, `teacher_id`) VALUES
(22, 'Môn Lý: Lớp rất ngoan và học rất tốt', 'Tiết 5', 'Học Kỳ 2', '2024-12-02', 1, 1, '0000000004'),
(23, 'Môn Lý: Lớp rất ngoan và học rất tốt', 'Tiết 4', 'Học Kỳ 2', '2024-12-02', 1, 1, '0000000004'),
(24, 'Môn Sử: Lớp rất ngoan và học rất tốt', 'Tiết 1', 'Học Kỳ 2', '2024-02-13', 1, 1, '0000000023'),
(25, 'Môn Toán: Các em rất ngoan ', 'Tiết 5', 'Học Kỳ 2', '2024-03-01', 1, 1, '0000000007'),
(26, 'Môn Toán: Các em rất ngoan ', 'Tiết 4', 'Học Kỳ 2', '2024-03-04', 1, 1, '0000000007');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reportcard`
--

CREATE TABLE `reportcard` (
  `reportcard_id` int(11) NOT NULL,
  `score_medium` float DEFAULT NULL,
  `semester_year` varchar(255) DEFAULT NULL,
  `classroom_id` int(11) DEFAULT NULL,
  `scoresheet_id` int(11) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reportcard`
--

INSERT INTO `reportcard` (`reportcard_id`, `score_medium`, `semester_year`, `classroom_id`, `scoresheet_id`, `student_id`, `subject_id`) VALUES
(1, 8.57, 'Năm 2024 học kỳ 2', 1, 1, '0000000001', 2),
(2, 8.71, 'Năm 2024 học kỳ 2', 1, 1, '0000000003', 2),
(3, 7.86, 'Năm 2024 học kỳ 2', 1, 1, '0000000009', 2),
(4, 7.96, 'Năm 2024 học kỳ 2', 1, 1, '0000000010', 2),
(5, 8, 'Năm 2024 học kỳ 2', 1, 1, '0000000011', 2),
(6, 8.57, 'Năm 2024 học kỳ 2', 1, 1, '1231231231', 2),
(7, 5.71, 'Năm 2024 học kỳ 2', 1, 1, '1231231232', 2),
(8, 9.38, 'Năm 2024 học kỳ 2', 1, 1, '0000000001', 1),
(9, 9.46, 'Năm 2024 học kỳ 2', 1, 1, '0000000003', 1),
(10, 9.08, 'Năm 2024 học kỳ 2', 1, 1, '0000000009', 1),
(11, 8.83, 'Năm 2024 học kỳ 2', 1, 1, '0000000010', 1),
(12, 7.83, 'Năm 2024 học kỳ 2', 1, 1, '0000000011', 1),
(13, 8.5, 'Năm 2024 học kỳ 2', 1, 1, '1231231231', 1),
(14, 8.83, 'Năm 2024 học kỳ 2', 1, 1, '1231231232', 1),
(15, 9, 'Năm 2024 học kỳ 2', 1, 1, '0000000001', 3),
(16, 9, 'Năm 2024 học kỳ 2', 1, 1, '0000000003', 3),
(17, 6.5, 'Năm 2024 học kỳ 2', 1, 1, '0000000009', 3),
(18, 6.67, 'Năm 2024 học kỳ 2', 1, 1, '0000000010', 3),
(19, 7.83, 'Năm 2024 học kỳ 2', 1, 1, '0000000011', 3),
(20, 8.12, 'Năm 2024 học kỳ 2', 1, 1, '1231231231', 3),
(21, 8.33, 'Năm 2024 học kỳ 2', 1, 1, '1231231232', 3),
(22, 8.5, 'Năm 2024 học kỳ 2', 1, 1, '0000000001', 4),
(23, 8.83, 'Năm 2024 học kỳ 2', 1, 1, '0000000003', 4),
(24, 8.67, 'Năm 2024 học kỳ 2', 1, 1, '0000000009', 4),
(25, 7.17, 'Năm 2024 học kỳ 2', 1, 1, '0000000010', 4),
(26, 7.83, 'Năm 2024 học kỳ 2', 1, 1, '0000000011', 4),
(27, 7.17, 'Năm 2024 học kỳ 2', 1, 1, '1231231231', 4),
(28, 5.67, 'Năm 2024 học kỳ 2', 1, 1, '1231231232', 4),
(29, 7.83, 'Năm 2024 học kỳ 2', 2, 2, '0000000002', 3),
(30, 7.83, 'Năm 2024 học kỳ 2', 2, 2, '0000000012', 3),
(31, 8.33, 'Năm 2024 học kỳ 2', 2, 2, '0000000013', 3),
(32, 8.5, 'Năm 2024 học kỳ 2', 2, 2, '0000000002', 1),
(33, 6, 'Năm 2024 học kỳ 2', 2, 2, '0000000012', 1),
(34, 7.33, 'Năm 2024 học kỳ 2', 2, 2, '0000000013', 1),
(35, 8.83, 'Năm 2024 học kỳ 1', 1, 3, '0000000001', 2),
(36, 8.5, 'Năm 2024 học kỳ 1', 1, 3, '0000000003', 2),
(37, 7.5, 'Năm 2024 học kỳ 1', 1, 3, '0000000009', 2),
(38, 7.67, 'Năm 2024 học kỳ 1', 1, 3, '0000000010', 2),
(39, 7.92, 'Năm 2024 học kỳ 1', 1, 3, '0000000011', 2),
(40, 7, 'Năm 2024 học kỳ 1', 1, 3, '1231231231', 2),
(41, 8.67, 'Năm 2024 học kỳ 1', 1, 3, '1231231232', 2),
(42, 8.5, 'Năm 2024 học kỳ 1', 1, 3, '0000000001', 1),
(43, 8, 'Năm 2024 học kỳ 1', 1, 3, '0000000003', 1),
(44, 8.83, 'Năm 2024 học kỳ 1', 1, 3, '0000000009', 1),
(45, 8.33, 'Năm 2024 học kỳ 1', 1, 3, '0000000010', 1),
(46, 8.17, 'Năm 2024 học kỳ 1', 1, 3, '0000000011', 1),
(47, 8.33, 'Năm 2024 học kỳ 1', 1, 3, '1231231231', 1),
(48, 6.17, 'Năm 2024 học kỳ 1', 1, 3, '1231231232', 1),
(49, 9, 'Năm 2024 học kỳ 1', 1, 3, '0000000001', 3),
(50, 9, 'Năm 2024 học kỳ 1', 1, 3, '0000000003', 3),
(51, 8, 'Năm 2024 học kỳ 1', 1, 3, '0000000009', 3),
(52, 8.12, 'Năm 2024 học kỳ 1', 1, 3, '0000000010', 3),
(53, 7.5, 'Năm 2024 học kỳ 1', 1, 3, '0000000011', 3),
(54, 5.33, 'Năm 2024 học kỳ 1', 1, 3, '1231231231', 3),
(55, 8.17, 'Năm 2024 học kỳ 1', 1, 3, '1231231232', 3),
(56, 9, 'Năm 2024 học kỳ 1', 1, 3, '0000000001', 4),
(57, 8, 'Năm 2024 học kỳ 1', 1, 3, '0000000003', 4),
(58, 8.33, 'Năm 2024 học kỳ 1', 1, 3, '0000000009', 4),
(59, 5.5, 'Năm 2024 học kỳ 1', 1, 3, '0000000010', 4),
(60, 8, 'Năm 2024 học kỳ 1', 1, 3, '0000000011', 4),
(61, 7, 'Năm 2024 học kỳ 1', 1, 3, '1231231231', 4),
(62, 6.5, 'Năm 2024 học kỳ 1', 1, 3, '1231231232', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reportcarddetail`
--

CREATE TABLE `reportcarddetail` (
  `reportcarddetail_id` int(11) NOT NULL,
  `score` float DEFAULT NULL,
  `semester_year` varchar(255) DEFAULT NULL,
  `test_day` datetime(6) DEFAULT NULL,
  `test_name` varchar(255) DEFAULT NULL,
  `reportcard_id` int(11) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reportcarddetail`
--

INSERT INTO `reportcarddetail` (`reportcarddetail_id`, `score`, `semester_year`, `test_day`, `test_name`, `reportcard_id`, `student_id`, `subject_id`) VALUES
(1, 7, 'Năm 2024 học kỳ 2', '2024-05-20 14:30:43.000000', 'Điểm ĐGTX', 1, '0000000001', 2),
(2, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:30:43.000000', 'Điểm ĐGTX', 2, '0000000003', 2),
(3, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:30:43.000000', 'Điểm ĐGTX', 3, '0000000009', 2),
(4, 6, 'Năm 2024 học kỳ 2', '2024-05-20 14:30:44.000000', 'Điểm ĐGTX', 4, '0000000010', 2),
(5, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:30:44.000000', 'Điểm ĐGTX', 5, '0000000011', 2),
(6, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:30:44.000000', 'Điểm ĐGTX', 6, '1231231231', 2),
(7, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:30:44.000000', 'Điểm ĐGTX', 7, '1231231232', 2),
(8, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:00.000000', 'Điểm ĐGTX', 1, '0000000001', 2),
(9, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:00.000000', 'Điểm ĐGTX', 2, '0000000003', 2),
(10, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:00.000000', 'Điểm ĐGTX', 3, '0000000009', 2),
(11, 6, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:00.000000', 'Điểm ĐGTX', 4, '0000000010', 2),
(12, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:00.000000', 'Điểm ĐGTX', 5, '0000000011', 2),
(13, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:00.000000', 'Điểm ĐGTX', 6, '1231231231', 2),
(14, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:00.000000', 'Điểm ĐGTX', 7, '1231231232', 2),
(15, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:14.000000', 'Điểm giữa kỳ', 1, '0000000001', 2),
(16, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:14.000000', 'Điểm giữa kỳ', 2, '0000000003', 2),
(17, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:14.000000', 'Điểm giữa kỳ', 3, '0000000009', 2),
(18, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:14.000000', 'Điểm giữa kỳ', 4, '0000000010', 2),
(19, 6, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:14.000000', 'Điểm giữa kỳ', 5, '0000000011', 2),
(20, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:14.000000', 'Điểm giữa kỳ', 6, '1231231231', 2),
(21, 7.5, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:14.000000', 'Điểm giữa kỳ', 7, '1231231232', 2),
(22, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:33.000000', 'Điểm cuối kỳ', 1, '0000000001', 2),
(23, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:33.000000', 'Điểm cuối kỳ', 2, '0000000003', 2),
(24, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:33.000000', 'Điểm cuối kỳ', 3, '0000000009', 2),
(25, 9.25, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:33.000000', 'Điểm cuối kỳ', 4, '0000000010', 2),
(26, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:33.000000', 'Điểm cuối kỳ', 5, '0000000011', 2),
(27, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:33.000000', 'Điểm cuối kỳ', 6, '1231231231', 2),
(28, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:31:33.000000', 'Điểm cuối kỳ', 7, '1231231232', 2),
(29, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:14.000000', 'Điểm ĐGTX', 8, '0000000001', 1),
(30, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:14.000000', 'Điểm ĐGTX', 9, '0000000003', 1),
(31, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:14.000000', 'Điểm ĐGTX', 10, '0000000009', 1),
(32, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:14.000000', 'Điểm ĐGTX', 11, '0000000010', 1),
(33, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:14.000000', 'Điểm ĐGTX', 12, '0000000011', 1),
(34, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:14.000000', 'Điểm ĐGTX', 13, '1231231231', 1),
(35, 7.5, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:14.000000', 'Điểm ĐGTX', 14, '1231231232', 1),
(36, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:35.000000', 'Điểm giữa kỳ', 8, '0000000001', 1),
(37, 10, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:36.000000', 'Điểm giữa kỳ', 9, '0000000003', 1),
(38, 9.25, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:36.000000', 'Điểm giữa kỳ', 10, '0000000009', 1),
(39, 10, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:36.000000', 'Điểm giữa kỳ', 11, '0000000010', 1),
(40, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:36.000000', 'Điểm giữa kỳ', 12, '0000000011', 1),
(41, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:36.000000', 'Điểm giữa kỳ', 13, '1231231231', 1),
(42, 9.25, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:36.000000', 'Điểm giữa kỳ', 14, '1231231232', 1),
(43, 9.75, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:53.000000', 'Điểm cuối kỳ', 8, '0000000001', 1),
(44, 9.25, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:53.000000', 'Điểm cuối kỳ', 9, '0000000003', 1),
(45, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:53.000000', 'Điểm cuối kỳ', 10, '0000000009', 1),
(46, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:53.000000', 'Điểm cuối kỳ', 11, '0000000010', 1),
(47, 7, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:53.000000', 'Điểm cuối kỳ', 12, '0000000011', 1),
(48, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:53.000000', 'Điểm cuối kỳ', 13, '1231231231', 1),
(49, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:32:53.000000', 'Điểm cuối kỳ', 14, '1231231232', 1),
(50, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:29.000000', 'Điểm ĐGTX', 15, '0000000001', 3),
(51, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:29.000000', 'Điểm ĐGTX', 16, '0000000003', 3),
(52, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:29.000000', 'Điểm ĐGTX', 17, '0000000009', 3),
(53, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:29.000000', 'Điểm ĐGTX', 18, '0000000010', 3),
(54, 7, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:29.000000', 'Điểm ĐGTX', 19, '0000000011', 3),
(55, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:29.000000', 'Điểm ĐGTX', 20, '1231231231', 3),
(56, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:29.000000', 'Điểm ĐGTX', 21, '1231231232', 3),
(57, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:37.000000', 'Điểm giữa kỳ', 15, '0000000001', 3),
(58, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:37.000000', 'Điểm giữa kỳ', 16, '0000000003', 3),
(59, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:37.000000', 'Điểm giữa kỳ', 17, '0000000009', 3),
(60, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:37.000000', 'Điểm giữa kỳ', 18, '0000000010', 3),
(61, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:37.000000', 'Điểm giữa kỳ', 19, '0000000011', 3),
(62, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:37.000000', 'Điểm giữa kỳ', 20, '1231231231', 3),
(63, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:37.000000', 'Điểm giữa kỳ', 21, '1231231232', 3),
(64, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:51.000000', 'Điểm cuối kỳ', 15, '0000000001', 3),
(65, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:51.000000', 'Điểm cuối kỳ', 16, '0000000003', 3),
(66, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:51.000000', 'Điểm cuối kỳ', 17, '0000000009', 3),
(67, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:51.000000', 'Điểm cuối kỳ', 18, '0000000010', 3),
(68, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:51.000000', 'Điểm cuối kỳ', 19, '0000000011', 3),
(69, 7.25, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:51.000000', 'Điểm cuối kỳ', 20, '1231231231', 3),
(70, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:34:51.000000', 'Điểm cuối kỳ', 21, '1231231232', 3),
(71, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:12.000000', 'Điểm ĐGTX', 22, '0000000001', 4),
(72, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:12.000000', 'Điểm ĐGTX', 23, '0000000003', 4),
(73, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:12.000000', 'Điểm ĐGTX', 24, '0000000009', 4),
(74, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:12.000000', 'Điểm ĐGTX', 25, '0000000010', 4),
(75, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:12.000000', 'Điểm ĐGTX', 26, '0000000011', 4),
(76, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:12.000000', 'Điểm ĐGTX', 27, '1231231231', 4),
(77, 6, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:12.000000', 'Điểm ĐGTX', 28, '1231231232', 4),
(78, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:21.000000', 'Điểm giữa kỳ', 22, '0000000001', 4),
(79, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:21.000000', 'Điểm giữa kỳ', 23, '0000000003', 4),
(80, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:21.000000', 'Điểm giữa kỳ', 24, '0000000009', 4),
(81, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:21.000000', 'Điểm giữa kỳ', 25, '0000000010', 4),
(82, 6, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:21.000000', 'Điểm giữa kỳ', 26, '0000000011', 4),
(83, 10, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:21.000000', 'Điểm giữa kỳ', 27, '1231231231', 4),
(84, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:21.000000', 'Điểm giữa kỳ', 28, '1231231232', 4),
(85, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:35.000000', 'Điểm cuối kỳ', 22, '0000000001', 4),
(86, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:35.000000', 'Điểm cuối kỳ', 23, '0000000003', 4),
(87, 9, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:35.000000', 'Điểm cuối kỳ', 24, '0000000009', 4),
(88, 8, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:35.000000', 'Điểm cuối kỳ', 25, '0000000010', 4),
(89, 10, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:35.000000', 'Điểm cuối kỳ', 26, '0000000011', 4),
(90, 5, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:35.000000', 'Điểm cuối kỳ', 27, '1231231231', 4),
(91, 4, 'Năm 2024 học kỳ 2', '2024-05-20 14:36:35.000000', 'Điểm cuối kỳ', 28, '1231231232', 4),
(92, 8, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:32.000000', 'Điểm ĐGTX', 29, '0000000002', 3),
(93, 7, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:32.000000', 'Điểm ĐGTX', 30, '0000000012', 3),
(94, 7, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:32.000000', 'Điểm ĐGTX', 31, '0000000013', 3),
(95, 9, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:48.000000', 'Điểm giữa kỳ', 29, '0000000002', 3),
(96, 8, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:48.000000', 'Điểm giữa kỳ', 30, '0000000012', 3),
(97, 8, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:48.000000', 'Điểm giữa kỳ', 31, '0000000013', 3),
(98, 7, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:57.000000', 'Điểm cuối kỳ', 29, '0000000002', 3),
(99, 8, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:57.000000', 'Điểm cuối kỳ', 30, '0000000012', 3),
(100, 9, 'Năm 2024 học kỳ 2', '2024-05-20 20:49:57.000000', 'Điểm cuối kỳ', 31, '0000000013', 3),
(101, 8, 'Năm 2024 học kỳ 2', '2024-05-20 20:51:49.000000', 'Điểm ĐGTX', 32, '0000000002', 1),
(102, 7, 'Năm 2024 học kỳ 2', '2024-05-20 20:51:49.000000', 'Điểm ĐGTX', 33, '0000000012', 1),
(103, 8, 'Năm 2024 học kỳ 2', '2024-05-20 20:51:49.000000', 'Điểm ĐGTX', 34, '0000000013', 1),
(104, 8, 'Năm 2024 học kỳ 2', '2024-05-20 20:52:04.000000', 'Điểm giữa kỳ', 32, '0000000002', 1),
(105, 7, 'Năm 2024 học kỳ 2', '2024-05-20 20:52:04.000000', 'Điểm giữa kỳ', 33, '0000000012', 1),
(106, 9, 'Năm 2024 học kỳ 2', '2024-05-20 20:52:04.000000', 'Điểm giữa kỳ', 34, '0000000013', 1),
(107, 9, 'Năm 2024 học kỳ 2', '2024-05-20 20:52:20.000000', 'Điểm cuối kỳ', 32, '0000000002', 1),
(108, 5, 'Năm 2024 học kỳ 2', '2024-05-20 20:52:20.000000', 'Điểm cuối kỳ', 33, '0000000012', 1),
(109, 6, 'Năm 2024 học kỳ 2', '2024-05-20 20:52:20.000000', 'Điểm cuối kỳ', 34, '0000000013', 1),
(110, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:28:42.000000', 'Điểm ĐGTX', 35, '0000000001', 2),
(111, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:28:42.000000', 'Điểm ĐGTX', 36, '0000000003', 2),
(112, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:28:42.000000', 'Điểm ĐGTX', 37, '0000000009', 2),
(113, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:28:42.000000', 'Điểm ĐGTX', 38, '0000000010', 2),
(114, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:28:42.000000', 'Điểm ĐGTX', 39, '0000000011', 2),
(115, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:28:42.000000', 'Điểm ĐGTX', 40, '1231231231', 2),
(116, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:28:42.000000', 'Điểm ĐGTX', 41, '1231231232', 2),
(117, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:12.000000', 'Điểm giữa kỳ', 35, '0000000001', 2),
(118, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:12.000000', 'Điểm giữa kỳ', 36, '0000000003', 2),
(119, 6, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:12.000000', 'Điểm giữa kỳ', 37, '0000000009', 2),
(120, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:12.000000', 'Điểm giữa kỳ', 38, '0000000010', 2),
(121, 7, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:12.000000', 'Điểm giữa kỳ', 39, '0000000011', 2),
(122, 3, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:12.000000', 'Điểm giữa kỳ', 40, '1231231231', 2),
(123, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:12.000000', 'Điểm giữa kỳ', 41, '1231231232', 2),
(124, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:44.000000', 'Điểm cuối kỳ', 35, '0000000001', 2),
(125, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:44.000000', 'Điểm cuối kỳ', 36, '0000000003', 2),
(126, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:44.000000', 'Điểm cuối kỳ', 37, '0000000009', 2),
(127, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:44.000000', 'Điểm cuối kỳ', 38, '0000000010', 2),
(128, 8.5, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:44.000000', 'Điểm cuối kỳ', 39, '0000000011', 2),
(129, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:44.000000', 'Điểm cuối kỳ', 40, '1231231231', 2),
(130, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:29:44.000000', 'Điểm cuối kỳ', 41, '1231231232', 2),
(131, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:40.000000', 'Điểm ĐGTX', 42, '0000000001', 1),
(132, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:40.000000', 'Điểm ĐGTX', 43, '0000000003', 1),
(133, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:40.000000', 'Điểm ĐGTX', 44, '0000000009', 1),
(134, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:40.000000', 'Điểm ĐGTX', 45, '0000000010', 1),
(135, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:40.000000', 'Điểm ĐGTX', 46, '0000000011', 1),
(136, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:40.000000', 'Điểm ĐGTX', 47, '1231231231', 1),
(137, 6, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:40.000000', 'Điểm ĐGTX', 48, '1231231232', 1),
(138, 6, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:50.000000', 'Điểm giữa kỳ', 42, '0000000001', 1),
(139, 6, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:50.000000', 'Điểm giữa kỳ', 43, '0000000003', 1),
(140, 7, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:50.000000', 'Điểm giữa kỳ', 44, '0000000009', 1),
(141, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:50.000000', 'Điểm giữa kỳ', 45, '0000000010', 1),
(142, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:50.000000', 'Điểm giữa kỳ', 46, '0000000011', 1),
(143, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:50.000000', 'Điểm giữa kỳ', 47, '1231231231', 1),
(144, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:32:50.000000', 'Điểm giữa kỳ', 48, '1231231232', 1),
(145, 10, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:06.000000', 'Điểm cuối kỳ', 42, '0000000001', 1),
(146, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:06.000000', 'Điểm cuối kỳ', 43, '0000000003', 1),
(147, 10, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:06.000000', 'Điểm cuối kỳ', 44, '0000000009', 1),
(148, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:06.000000', 'Điểm cuối kỳ', 45, '0000000010', 1),
(149, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:06.000000', 'Điểm cuối kỳ', 46, '0000000011', 1),
(150, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:06.000000', 'Điểm cuối kỳ', 47, '1231231231', 1),
(151, 7, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:06.000000', 'Điểm cuối kỳ', 48, '1231231232', 1),
(152, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:37.000000', 'Điểm ĐGTX', 49, '0000000001', 3),
(153, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:37.000000', 'Điểm ĐGTX', 50, '0000000003', 3),
(154, 6, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:37.000000', 'Điểm ĐGTX', 51, '0000000009', 3),
(155, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:37.000000', 'Điểm ĐGTX', 52, '0000000010', 3),
(156, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:38.000000', 'Điểm ĐGTX', 53, '0000000011', 3),
(157, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:38.000000', 'Điểm ĐGTX', 54, '1231231231', 3),
(158, 6, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:38.000000', 'Điểm ĐGTX', 55, '1231231232', 3),
(159, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:47.000000', 'Điểm giữa kỳ', 49, '0000000001', 3),
(160, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:47.000000', 'Điểm giữa kỳ', 50, '0000000003', 3),
(161, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:47.000000', 'Điểm giữa kỳ', 51, '0000000009', 3),
(162, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:47.000000', 'Điểm giữa kỳ', 52, '0000000010', 3),
(163, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:47.000000', 'Điểm giữa kỳ', 53, '0000000011', 3),
(164, 6, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:47.000000', 'Điểm giữa kỳ', 54, '1231231231', 3),
(165, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:33:47.000000', 'Điểm giữa kỳ', 55, '1231231232', 3),
(166, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:00.000000', 'Điểm cuối kỳ', 49, '0000000001', 3),
(167, 10, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:00.000000', 'Điểm cuối kỳ', 50, '0000000003', 3),
(168, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:00.000000', 'Điểm cuối kỳ', 51, '0000000009', 3),
(169, 7.25, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:00.000000', 'Điểm cuối kỳ', 52, '0000000010', 3),
(170, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:00.000000', 'Điểm cuối kỳ', 53, '0000000011', 3),
(171, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:00.000000', 'Điểm cuối kỳ', 54, '1231231231', 3),
(172, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:00.000000', 'Điểm cuối kỳ', 55, '1231231232', 3),
(173, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:51.000000', 'Điểm giữa kỳ', 56, '0000000001', 4),
(174, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:51.000000', 'Điểm giữa kỳ', 57, '0000000003', 4),
(175, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:51.000000', 'Điểm giữa kỳ', 58, '0000000009', 4),
(176, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:51.000000', 'Điểm giữa kỳ', 59, '0000000010', 4),
(177, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:51.000000', 'Điểm giữa kỳ', 60, '0000000011', 4),
(178, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:51.000000', 'Điểm giữa kỳ', 61, '1231231231', 4),
(179, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:34:51.000000', 'Điểm giữa kỳ', 62, '1231231232', 4),
(180, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:38.000000', 'Điểm ĐGTX', 56, '0000000001', 4),
(181, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:38.000000', 'Điểm ĐGTX', 57, '0000000003', 4),
(182, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:38.000000', 'Điểm ĐGTX', 58, '0000000009', 4),
(183, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:38.000000', 'Điểm ĐGTX', 59, '0000000010', 4),
(184, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:38.000000', 'Điểm ĐGTX', 60, '0000000011', 4),
(185, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:38.000000', 'Điểm ĐGTX', 61, '1231231231', 4),
(186, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:38.000000', 'Điểm ĐGTX', 62, '1231231232', 4),
(187, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:50.000000', 'Điểm cuối kỳ', 56, '0000000001', 4),
(188, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:50.000000', 'Điểm cuối kỳ', 57, '0000000003', 4),
(189, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:50.000000', 'Điểm cuối kỳ', 58, '0000000009', 4),
(190, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:50.000000', 'Điểm cuối kỳ', 59, '0000000010', 4),
(191, 8, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:50.000000', 'Điểm cuối kỳ', 60, '0000000011', 4),
(192, 9, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:50.000000', 'Điểm cuối kỳ', 61, '1231231231', 4),
(193, 5, 'Năm 2024 học kỳ 1', '2024-05-20 22:35:50.000000', 'Điểm cuối kỳ', 62, '1231231232', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `roles_id` int(11) NOT NULL,
  `namerole` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`roles_id`, `namerole`) VALUES
(1, 'ROLE_PRINCIPAL'),
(2, 'ROLE_STUDENT'),
(3, 'ROLE_HOMEROOMTEACHER'),
(4, 'ROLE_TEACHER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles_teacher`
--

CREATE TABLE `roles_teacher` (
  `teacher_id` varchar(255) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles_teacher`
--

INSERT INTO `roles_teacher` (`teacher_id`, `roles_id`) VALUES
('0000000004', 3),
('0000000004', 4),
('0000000007', 3),
('0000000007', 4),
('0000000005', 4),
('0000000022', 4),
('0000000023', 3),
('0000000023', 4),
('0000000005', 3),
('0000000008', 4),
('0000000000', 1),
('0000000077', 3),
('0000000078', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `scoresheet`
--

CREATE TABLE `scoresheet` (
  `scoresheet_id` int(11) NOT NULL,
  `semester_year` varchar(255) DEFAULT NULL,
  `classroom_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `scoresheet`
--

INSERT INTO `scoresheet` (`scoresheet_id`, `semester_year`, `classroom_id`) VALUES
(1, 'Năm 2024 học kỳ 2', 1),
(2, 'Năm 2024 học kỳ 2', 2),
(3, 'Năm 2024 học kỳ 1', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE `student` (
  `student_id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_phone` varchar(255) DEFAULT NULL,
  `classroom_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `roles_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`student_id`, `address`, `date_of_birth`, `gender`, `student_name`, `student_phone`, `classroom_id`, `account_id`, `roles_id`) VALUES
('0000000001', 'Hà Nội', '2008-02-12', 'Nam', 'Ma Văn Thơ', '1231231231', 1, 12, 2),
('0000000002', 'Hà Nội', '2008-02-23', 'Nữ', 'Lê Yến Nhi', '1231231231', 2, 13, 2),
('0000000003', 'Hà Nội', '2008-01-18', 'Nữ', 'Bùi Thị Hài', '1231231231', 1, 14, 2),
('0000000009', 'Hà Nam', '2008-08-08', 'Nam', 'Phạm Huy Tùng', '1231231231', 1, 15, 2),
('0000000010', 'Hà Nội', '2008-09-23', 'Nam', 'Bùi Hữu Dũng', '1231231231', 1, 18, 2),
('0000000011', 'Hà Nội', '2008-02-01', 'Nữ', 'Bùi Bích Ngọc', '1231231231', 1, 17, 2),
('0000000012', 'Hà Nội', '2008-08-12', 'Nữ', 'Phạm Ngọc Linh', '1231231231', 2, 26, 2),
('0000000013', 'Hà Nội', '1981-02-12', 'Nam', 'Nguyễn Văn Duẩn', '1231231231', 2, 27, 2),
('0000000014', 'Sơn La', '2009-02-12', 'Nữ', 'Hoàng Ánh Nguyệt', '1231231231', 4, 28, 2),
('0000000015', 'Bắc Giang', '2009-03-12', 'Nữ', 'Bùi Thị Diệu Linh', '1231231231', 4, 29, 2),
('0000000016', 'Hà Nam', '2009-03-12', 'Nam', 'Phạm Văn Linh', '1231231231', 4, 30, 2),
('0000000017', 'Hà Nội', '2009-02-12', 'Nam', 'Ma Đức Thế', '1231231231', 3, 31, 2),
('0000000018', 'Hà Nội', '2009-02-12', 'Nam', 'Vũ Mạnh Tuấn', '1231231231', 3, 32, 2),
('0000000019', 'Hà Nội', '0009-02-12', 'Nữ', 'Bùi Thị Hà', '1231231231', 6, 33, 2),
('0000000020', 'Hà Nội', '2010-02-12', 'Nam', 'Bùi Văn Lộc', '1231231231', 6, 34, 2),
('0000000021', 'Thái Bình', '2010-02-12', 'Nam', 'Từ Thế An', '1231231231', 6, 35, 2),
('0000001456', 'Hà Nội', '2006-03-12', 'Nam', 'Lò Văn Dũng', '1231231231', 3, 21, 2),
('1231231231', 'Hà Nam', '2008-12-12', 'Nam', 'Dương Mạnh Cường', '1231231231', 1, 23, 2),
('1231231232', 'hà Nội', '2008-02-12', 'Nam', 'Bùi Đức Thọ', '1231231231', 1, 25, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `subject`
--

CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL,
  `number_of_periods` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `subject`
--

INSERT INTO `subject` (`subject_id`, `number_of_periods`, `description`, `subject_name`, `teacher_id`) VALUES
(1, 65, 'Toán lớp 11 ', 'Toán lớp 11 ', '0000000008'),
(2, 32, 'Sử lớp 11', 'Sử lớp 11', '0000000023'),
(3, 60, 'Lý lớp 11', 'Lý lớp 11 ', '0000000004'),
(4, 57, 'Hóa lớp 11', 'Hóa lớp 11', '0000000022'),
(5, 30, 'Thể dục nâng cao sức khỏe', 'Thể dục', '0000000008'),
(7, 60, 'Toán 12 ', 'Toán 12 ', '0000000008'),
(8, 45, 'Lý 12 ', 'Lý 12 ', '0000000004'),
(9, 45, 'Hóa 12 ', 'Hóa 12 ', '0000000007'),
(10, 45, 'Ngoại Ngữ 12', 'Ngoại Ngữ 12', '0000000005'),
(11, 50, 'Văn lớp 10', 'Văn lớp 10 ', '0000000007'),
(12, 45, 'Tiếng anh', 'Ngoại Ngữ lớp 10 ', '0000000022'),
(13, 60, 'Toán lớp 10', 'Toán lớp 10 ', '0000000007');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `teacher_phone` varchar(255) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `address`, `date_of_birth`, `gender`, `teacher_name`, `teacher_phone`, `account_id`) VALUES
('0000000000', 'Hà nội', '1969-04-15', 'Nam', 'Hoàng Hải Đăng', '1231231231', 1),
('0000000004', 'Hồ Chí Minh', '1989-02-09', 'Nam', 'Bùi Vũ Thành', '1231231231', 5),
('0000000005', 'An Giang', '1992-12-09', 'Nam', 'Lê Hoàng An', '1231231231', 6),
('0000000007', 'Bắc Ninh', '1989-04-11', 'Nữ', 'Hoàng Minh Diệp', '1231231231', 4),
('0000000008', 'Hà Nội', '1992-03-12', 'Nam', 'Từ Việt Hà', '1231231231', 8),
('0000000022', 'Hà Nội', '1982-01-01', 'Nam', 'Lê Trường Giang', '1231231231', 2),
('0000000023', 'Hà nội', '1988-11-12', 'Nam', 'Lò Văn Dũng', '1231231231', 3),
('0000000077', 'Hà Nội', '1979-03-12', 'Nữ', 'Từ Hải Ngân', '0123123123', 36),
('0000000078', 'Hà Nội', '1988-02-12', 'Nữ', 'Phạm Thị Hường', '0123123123', 37);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `academictranscript`
--
ALTER TABLE `academictranscript`
  ADD PRIMARY KEY (`academictranscipt_id`),
  ADD UNIQUE KEY `UK_re9few85xp0ntofx2ot8872v0` (`student_id`);

--
-- Chỉ mục cho bảng `academictranscriptdetail`
--
ALTER TABLE `academictranscriptdetail`
  ADD PRIMARY KEY (`academictransciptdetail_id`),
  ADD KEY `FK97gho516qohy0t8j0tmb4gfn8` (`academictranscipt_id`),
  ADD KEY `FK5xyyhxdtnr3weht74xcq6lb5s` (`student_id`),
  ADD KEY `FK9xcqpny483kp64mjnl7a12t2d` (`teacher_id`);

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`classroom_id`),
  ADD KEY `FKem6hymrr3cxa0ldasm7jxojrc` (`teacher_id`);

--
-- Chỉ mục cho bảng `classroom_subject`
--
ALTER TABLE `classroom_subject`
  ADD KEY `FKp5fc4pktkpjar3jsuf1ts9ulw` (`classroom_id`),
  ADD KEY `FKs4hyp4i08cu4knfl14r7p6cvp` (`subject_id`);

--
-- Chỉ mục cho bảng `parent`
--
ALTER TABLE `parent`
  ADD PRIMARY KEY (`parent_id`),
  ADD UNIQUE KEY `UK_tf1jv6aqj0ixial64hfhp6dl3` (`student_id`);

--
-- Chỉ mục cho bảng `registernotebook`
--
ALTER TABLE `registernotebook`
  ADD PRIMARY KEY (`registernotebook_id`),
  ADD UNIQUE KEY `UK_7myg9roseu7un2is0f7per9ec` (`classroom_id`);

--
-- Chỉ mục cho bảng `registernotebookdetail`
--
ALTER TABLE `registernotebookdetail`
  ADD PRIMARY KEY (`registernotebookdetail_id`),
  ADD KEY `FKhoqc35xj8fdk6s7n5oo430b7` (`classroom_id`),
  ADD KEY `FK1jucvs47rv84l602sklu54orb` (`registernotebook_id`),
  ADD KEY `FK5mvipvb4uyah7s56xsrgu3ku2` (`teacher_id`);

--
-- Chỉ mục cho bảng `reportcard`
--
ALTER TABLE `reportcard`
  ADD PRIMARY KEY (`reportcard_id`),
  ADD KEY `FKltawree8rikkrw9j4ft7ib95r` (`classroom_id`),
  ADD KEY `FKcwnrs2ge1046kcpmwxdinvc0r` (`scoresheet_id`),
  ADD KEY `FK1lry60jp3a0ap4in2nxu67mhf` (`student_id`),
  ADD KEY `FK2g3ik1cx4lltxoknj3v8igtbs` (`subject_id`);

--
-- Chỉ mục cho bảng `reportcarddetail`
--
ALTER TABLE `reportcarddetail`
  ADD PRIMARY KEY (`reportcarddetail_id`),
  ADD KEY `FKlmshk2oqbvpweyabtv613n1mg` (`reportcard_id`),
  ADD KEY `FKardb7a1kslt00d93q4rh8ya4j` (`student_id`),
  ADD KEY `FKedrbi6irvb54paru8e5cdaxqt` (`subject_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`roles_id`);

--
-- Chỉ mục cho bảng `roles_teacher`
--
ALTER TABLE `roles_teacher`
  ADD KEY `FKprr7o2yn4osoqcd7swgr3vjht` (`roles_id`),
  ADD KEY `FK7h8vljrf70euxqqga33m5y0r` (`teacher_id`);

--
-- Chỉ mục cho bảng `scoresheet`
--
ALTER TABLE `scoresheet`
  ADD PRIMARY KEY (`scoresheet_id`),
  ADD KEY `FKn9sr5hkij19vwnda6lfmbb8lw` (`classroom_id`);

--
-- Chỉ mục cho bảng `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `UK_h3517npqxa9l44t6qihyalkdy` (`account_id`),
  ADD KEY `FK1rs4md9whkjqy20v181d18kfy` (`classroom_id`),
  ADD KEY `FK5ne9dhgy86onlawxprnsqw3da` (`roles_id`);

--
-- Chỉ mục cho bảng `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`),
  ADD KEY `FKdvgvxo0oxhxeepkkwug7vg4w4` (`teacher_id`);

--
-- Chỉ mục cho bảng `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`),
  ADD UNIQUE KEY `UK_8eugmpfm40x0vpuaqpds1ru32` (`account_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `academictranscript`
--
ALTER TABLE `academictranscript`
  MODIFY `academictranscipt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `academictranscriptdetail`
--
ALTER TABLE `academictranscriptdetail`
  MODIFY `academictransciptdetail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT cho bảng `classroom`
--
ALTER TABLE `classroom`
  MODIFY `classroom_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `parent`
--
ALTER TABLE `parent`
  MODIFY `parent_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `registernotebook`
--
ALTER TABLE `registernotebook`
  MODIFY `registernotebook_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `registernotebookdetail`
--
ALTER TABLE `registernotebookdetail`
  MODIFY `registernotebookdetail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `reportcard`
--
ALTER TABLE `reportcard`
  MODIFY `reportcard_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT cho bảng `reportcarddetail`
--
ALTER TABLE `reportcarddetail`
  MODIFY `reportcarddetail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=194;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `roles_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `scoresheet`
--
ALTER TABLE `scoresheet`
  MODIFY `scoresheet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `academictranscript`
--
ALTER TABLE `academictranscript`
  ADD CONSTRAINT `FK58c1l0quf6016g5in8lg5sp1e` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`);

--
-- Các ràng buộc cho bảng `academictranscriptdetail`
--
ALTER TABLE `academictranscriptdetail`
  ADD CONSTRAINT `FK5xyyhxdtnr3weht74xcq6lb5s` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  ADD CONSTRAINT `FK97gho516qohy0t8j0tmb4gfn8` FOREIGN KEY (`academictranscipt_id`) REFERENCES `academictranscript` (`academictranscipt_id`),
  ADD CONSTRAINT `FK9xcqpny483kp64mjnl7a12t2d` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`);

--
-- Các ràng buộc cho bảng `classroom`
--
ALTER TABLE `classroom`
  ADD CONSTRAINT `FKem6hymrr3cxa0ldasm7jxojrc` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`);

--
-- Các ràng buộc cho bảng `classroom_subject`
--
ALTER TABLE `classroom_subject`
  ADD CONSTRAINT `FKp5fc4pktkpjar3jsuf1ts9ulw` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`),
  ADD CONSTRAINT `FKs4hyp4i08cu4knfl14r7p6cvp` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`);

--
-- Các ràng buộc cho bảng `parent`
--
ALTER TABLE `parent`
  ADD CONSTRAINT `FKarwf7gfuwotw1cpj7ql4sf9f2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`);

--
-- Các ràng buộc cho bảng `registernotebook`
--
ALTER TABLE `registernotebook`
  ADD CONSTRAINT `FKhdq89v34sebvu8efwornfm2mm` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`);

--
-- Các ràng buộc cho bảng `registernotebookdetail`
--
ALTER TABLE `registernotebookdetail`
  ADD CONSTRAINT `FK1jucvs47rv84l602sklu54orb` FOREIGN KEY (`registernotebook_id`) REFERENCES `registernotebook` (`registernotebook_id`),
  ADD CONSTRAINT `FK5mvipvb4uyah7s56xsrgu3ku2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`),
  ADD CONSTRAINT `FKhoqc35xj8fdk6s7n5oo430b7` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`);

--
-- Các ràng buộc cho bảng `reportcard`
--
ALTER TABLE `reportcard`
  ADD CONSTRAINT `FK1lry60jp3a0ap4in2nxu67mhf` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  ADD CONSTRAINT `FK2g3ik1cx4lltxoknj3v8igtbs` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `FKcwnrs2ge1046kcpmwxdinvc0r` FOREIGN KEY (`scoresheet_id`) REFERENCES `scoresheet` (`scoresheet_id`),
  ADD CONSTRAINT `FKltawree8rikkrw9j4ft7ib95r` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`);

--
-- Các ràng buộc cho bảng `reportcarddetail`
--
ALTER TABLE `reportcarddetail`
  ADD CONSTRAINT `FKardb7a1kslt00d93q4rh8ya4j` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  ADD CONSTRAINT `FKedrbi6irvb54paru8e5cdaxqt` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `FKlmshk2oqbvpweyabtv613n1mg` FOREIGN KEY (`reportcard_id`) REFERENCES `reportcard` (`reportcard_id`);

--
-- Các ràng buộc cho bảng `roles_teacher`
--
ALTER TABLE `roles_teacher`
  ADD CONSTRAINT `FK7h8vljrf70euxqqga33m5y0r` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`),
  ADD CONSTRAINT `FKprr7o2yn4osoqcd7swgr3vjht` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`roles_id`);

--
-- Các ràng buộc cho bảng `scoresheet`
--
ALTER TABLE `scoresheet`
  ADD CONSTRAINT `FKn9sr5hkij19vwnda6lfmbb8lw` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`);

--
-- Các ràng buộc cho bảng `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FK1rs4md9whkjqy20v181d18kfy` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`),
  ADD CONSTRAINT `FK5ne9dhgy86onlawxprnsqw3da` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`roles_id`),
  ADD CONSTRAINT `FKoootcgotavmpat2yv9o52wx1q` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `FKdvgvxo0oxhxeepkkwug7vg4w4` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`);

--
-- Các ràng buộc cho bảng `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `FK5t4vdu18ohx39bj4lef9qf779` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
