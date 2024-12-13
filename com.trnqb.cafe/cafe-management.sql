-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 13, 2024 at 06:26 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cafe-management`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `product_details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`product_details`)),
  `total` int(11) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  `shift_time` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`id`, `created_by`, `email`, `name`, `payment_method`, `phone_number`, `product_details`, `total`, `uuid`, `date`, `time`, `shift_time`) VALUES
(19, 'admin@gmail.com', 'tqbao2363@gmail.com', 'fdvd', NULL, '0948980058', '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":29000,\"total\":29000}]', 29000, 'Bill-1728962164316', '2024-11-29', '13:58:00.000000', 'Afternoon'),
(23, 'admin@gmail.com', 'testEmail@gmail.com', 'aaa', 'Cash', 'xyzxzyzxyz', '[{\"name\": \"Milk Coffee\", \"category\": \"Coffee\", \"quantity\": 1, \"price\": 100, \"total\": 100}, {\"name\": \"Tea Coffee\", \"category\": \"Coffee\", \"quantity\": 2, \"price\": 260, \"total\": 260} ]', 360, 'Bill-1731696854305', '2024-11-30', '13:58:00.000000', 'Afternoon'),
(24, 'admin@gmail.com', 'tqbao2363@gmail.com', 'Bao', 'Credit Card', '0948980058', '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":29000,\"total\":29000},{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":32000,\"total\":64000}]', 93000, 'Bill-1731696931388', '2024-12-01', '13:58:00.000000', 'Afternoon'),
(26, 'tqbao@gmail.com', 'asdas@gmail.com', 'khasch', 'Cash', '1234567890', '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":32000,\"total\":64000}]', 64000, 'Bill-1731832558618', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(27, 'tqbao@gmail.com', 'tqbao2363@gmail.com', 'fdvd', 'Debit Card', '0948980058', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 60000, 'Bill-1731832687590', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(28, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":2,\"price\":32000,\"total\":64000}]', 64000, 'Bill-1731834068675', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(29, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000},{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":70000,\"total\":70000}]', 166000, 'Bill-1731834514578', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(30, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"4\",\"price\":29000,\"total\":116000},{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":75000,\"total\":75000}]', 191000, 'Bill-1731834575047', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(31, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":75000,\"total\":150000},{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000}]', 246000, 'Bill-1731834622266', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(32, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":75000,\"total\":75000},{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":29000,\"total\":58000}]', 133000, 'Bill-1731834652531', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(33, 'tqbao@gmail.com', 'email@example.vn', 'Hello', 'Credit Card', '1234567890', '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"3\",\"price\":75000,\"total\":225000},{\"id\":25,\"name\":\"Cafe Đen Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":25000,\"total\":25000}]', 250000, 'Bill-1731858305246', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(34, 'tqbao@gmail.com', 'email@example.vn', 'Hello12121212', 'Cash', '1234567890', '[{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":70000,\"total\":140000},{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":75000,\"total\":150000}]', 290000, 'Bill-1731858865110', '2024-12-04', '11:58:00.000000', 'Morning'),
(35, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":32000,\"total\":32000}]', 32000, 'Bill-1731858959538', '2024-12-04', '11:58:00.000000', 'Morning'),
(37, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000}]', 96000, 'Bill-1731859018433', '2024-12-04', '11:58:00.000000', 'Morning'),
(38, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":32000,\"total\":64000}]', 64000, 'Bill-1731870389319', '2024-12-04', '11:58:00.000000', 'Morning'),
(39, 'admin@gmail.com', 'testEmail@gmail.com', 'aaa', 'Cash', 'xyzxzyzxyz', '[{\"name\": \"Americano\", \"category\": \"Espresso-Based\", \"quantity\": 1, \"price\": 45000, \"total\": 45000}, {\"name\": \"Latte\", \"category\": \"Espresso-Based\", \"quantity\": 2, \"price\": 65000, \"total\": 130000} ]', 175000, 'Bill-1732198292115', '2024-12-04', '11:58:00.000000', 'Morning'),
(40, 'tqbao@gmail.com', 'f@gmail.com', 'Khach 1', 'Cash', '1234567895', '[{\"id\":28,\"name\":\"Trà đào\",\"category\":\"Trà hoa quả\",\"quantity\":\"4\",\"price\":36000,\"total\":144000},{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 204000, 'Bill-1732296465134', '2024-12-04', '11:58:00.000000', 'Morning'),
(41, 'tqbao@gmail.com', 'email@example.vn', 'Khach 2', 'Debit Card', '1231231231', '[{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":65000,\"total\":65000},{\"id\":28,\"name\":\"Trà đào\",\"category\":\"Trà hoa quả\",\"quantity\":\"1\",\"price\":36000,\"total\":36000}]', 101000, 'Bill-1732296520975', '2024-11-30', '11:58:00.000000', 'Morning'),
(42, 'admin@gmail.com', 'hiepgale0817@gmail.com', 'Hiep Ga', 'Cash', '1234567891', '[{\"id\":21,\"name\":\"Iced Americano\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":50000,\"total\":100000}]', 100000, 'Bill-1732447809876', '2024-12-04', '11:58:00.000000', 'Morning'),
(43, 'admin@gmail.com', '123456@gmail.com', 'Khach A', 'Credit Card', '1234567800', '[{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":70000,\"total\":140000},{\"id\":25,\"name\":\"Cafe Đen Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":25000,\"total\":25000}]', 165000, 'Bill-1732601224525', '2024-12-04', '11:58:00.000000', 'Morning'),
(44, 'admin@gmail.com', 'email@example.vn', 'Hello', 'Cash', '0987654321', '[{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":70000,\"total\":140000},{\"id\":25,\"name\":\"Cafe Đen Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":25000,\"total\":50000}]', 190000, 'Bill-1732601467744', '2024-12-04', '11:58:00.000000', 'Morning'),
(45, 'admin@gmail.com', 'hello@gmail.com', 'Hello A', 'Cash', '2121212121', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":65000,\"total\":130000},{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 180000, 'Bill-1732602516844', '2024-12-05', '11:58:00.000000', 'Morning'),
(46, 'admin@gmail.com', 'email@example.vn', 'Hello', 'Cash', '0987654321', '[{\"id\":25,\"name\":\"Cafe Đen Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":25000,\"total\":75000},{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 135000, 'Bill-1732602600420', '2024-12-05', '11:58:00.000000', 'Morning'),
(47, 'admin@gmail.com', 'email@example.vn', 'Hello', 'Cash', '0987654321', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":65000,\"total\":130000},{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"5\",\"price\":70000,\"total\":350000}]', 480000, 'Bill-1732602699011', '2024-12-05', '11:58:00.000000', 'Morning'),
(48, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Cash', '1234567891', '[{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":55000,\"total\":110000},{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":75000,\"total\":75000}]', 185000, 'Bill-1732604705753', '2024-12-05', '11:58:00.000000', 'Morning'),
(49, 'admin@gmail.com', 'bao@example.com', 'Bao', 'Debit Card', '1234567891', '[{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":70000,\"total\":70000}]', 70000, 'Bill-1732646670520', '2024-12-05', '11:58:00.000000', 'Morning'),
(50, 'admin@gmail.com', 'bao@gmail.com', 'Tran Bao', 'Cash', '1234567891', '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":32000,\"total\":64000}]', 64000, 'Bill-1732646885149', '2024-12-06', '11:58:00.000000', 'Morning'),
(51, 'admin@gmail.com', 'duy@gmail.com', 'Duy', 'Credit Card', '1234567821', '[{\"id\":21,\"name\":\"Iced Americano\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 50000, 'Bill-1732727359687', '2024-12-06', '11:58:00.000000', 'Morning'),
(52, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Cash', '1234567891', '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"4\",\"price\":29000,\"total\":116000}]', 116000, 'Bill-1732819176583', '2024-12-06', '11:58:00.000000', 'Morning'),
(53, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Credit Card', '1234567891', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 60000, 'Bill-1732819214483', '2024-12-06', '11:58:00.000000', 'Morning'),
(54, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Cash', '1234567891', '[{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"9\",\"price\":75000,\"total\":675000}]', 675000, 'Bill-1732998141326', '2024-12-06', '11:58:00.000000', 'Morning'),
(55, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Cash', '1234567891', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"3\",\"price\":65000,\"total\":195000},{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"6\",\"price\":50000,\"total\":300000},{\"id\":15,\"name\":\"Cappuccino\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"4\",\"price\":60000,\"total\":240000}]', 735000, 'Bill-1733081632144', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(56, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Debit Card', '1234567891', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"11\",\"price\":60000,\"total\":660000}]', 660000, 'Bill-1733081646750', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(57, 'admin@gmail.com', 'email@example.vn', 'Hello', 'Debit Card', '1234567890', '[{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"7\",\"price\":75000,\"total\":525000}]', 525000, 'Bill-1733081670662', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(58, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Cash', '1234567891', '[{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 50000, 'Bill-1733124887497', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(59, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Credit Card', '1234567891', '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":29000,\"total\":29000}]', 29000, 'Bill-1733124996275', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(60, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Credit Card', '1234567891', '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":29000,\"total\":29000}]', 29000, 'Bill-1733125790972', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(61, 'admin@gmail.com', '123@gmail.com', 'Khach', 'Cash', '1231231231', '[{\"id\":21,\"name\":\"Iced Americano\",\"category\":\"Iced Coffee\",\"quantity\":\"10\",\"price\":50000,\"total\":500000}]', 500000, 'Bill-1733126351568', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(62, 'admin@gmail.com', '123@gmail.com', 'Khach', 'Cash', '1231231231', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":65000,\"total\":65000}]', 65000, 'Bill-1733126440802', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(63, 'admin@gmail.com', '123@gmail.com', 'Khach', 'Cash', '1231231231', '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"10\",\"price\":75000,\"total\":750000}]', 750000, 'Bill-1733126590054', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(64, 'admin@gmail.com', '123@gmail.com', 'Khach', 'Cash', '1231231231', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":65000,\"total\":65000}]', 65000, 'Bill-1733126663897', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(65, 'admin@gmail.com', '123@gmail.com', 'Khach', 'Credit Card', '1231231231', '[{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"7\",\"price\":70000,\"total\":490000},{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"4\",\"price\":75000,\"total\":300000}]', 790000, 'Bill-1733126681187', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(66, 'admin@gmail.com', '123@gmail.com', 'Khach', 'Cash', '1231231231', '[{\"id\":28,\"name\":\"Trà đào\",\"category\":\"Trà hoa quả\",\"quantity\":\"1\",\"price\":36000,\"total\":36000}]', 36000, 'Bill-1733126713923', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(67, 'admin@gmail.com', '123@gmail.com', 'Khach', 'Cash', '1231231231', '[{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":55000,\"total\":55000}]', 55000, 'Bill-1733126746280', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(68, 'admin@gmail.com', 'email@example.vn', 'Hello', 'Cash', '1234567890', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"3\",\"price\":60000,\"total\":180000}]', 180000, 'Bill-1733157190564', '2024-12-07', '16:58:00.000000', 'Afternoon'),
(69, 'admin@gmail.com', 'email@example.vn', 'Hello', 'Credit Card', '1234567890', '[{\"id\":15,\"name\":\"Cappuccino\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 60000, 'Bill-1733157528621', '2024-11-30', '16:58:00.000000', 'Afternoon'),
(70, 'admin@gmail.com', '', '', 'Cash', '', '[{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":55000,\"total\":55000}]', 55000, 'Bill-1733158436661', '2024-12-08', '06:58:00.000000', 'Morning'),
(71, 'admin@gmail.com', '', '', 'Cash', '', '[{\"id\":15,\"name\":\"Cappuccino\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 60000, 'Bill-1733158555700', '2024-12-08', '06:58:00.000000', 'Morning'),
(72, 'tqbao@gmail.com', '', '', 'Cash', '', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":65000,\"total\":65000}]', 65000, 'Bill-1733158731208', '2024-12-08', '06:58:00.000000', 'Morning'),
(73, 'tqbao@gmail.com', '', '', 'Credit Card', '', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":65000,\"total\":65000}]', 65000, 'Bill-1733158908848', '2024-12-08', '06:58:00.000000', 'Morning'),
(74, 'tqbao@gmail.com', 'tranb@gmail.com', 'Bao Tran', 'Cash', '2362362361', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"4\",\"price\":65000,\"total\":260000}]', 260000, 'Bill-1733158939206', '2024-12-08', '06:58:00.000000', 'Morning'),
(75, 'tqbao@gmail.com', '', '', 'Cash', '', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 60000, 'Bill-1733159022413', '2024-12-08', '06:58:00.000000', 'Morning'),
(76, 'tqbao@gmail.com', NULL, NULL, 'Credit Card', NULL, '[{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":65000,\"total\":65000}]', 65000, 'Bill-1733159043952', '2024-12-08', '06:58:00.000000', 'Morning'),
(77, 'tqbao@gmail.com', NULL, NULL, 'Debit Card', NULL, '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":60000,\"total\":120000}]', 120000, 'Bill-1733159065012', '2024-12-08', '06:58:00.000000', 'Morning'),
(78, 'admin@gmail.com', '', '', 'Credit Card', '', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"7\",\"price\":60000,\"total\":420000}]', 420000, 'Bill-1733159234127', '2024-12-08', '06:58:00.000000', 'Morning'),
(79, 'admin@gmail.com', NULL, NULL, 'Credit Card', NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":32000,\"total\":32000}]', 32000, 'Bill-1733159336389', '2024-12-08', '06:58:00.000000', 'Morning'),
(80, 'admin@gmail.com', NULL, NULL, 'Debit Card', NULL, '[{\"id\":15,\"name\":\"Cappuccino\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"3\",\"price\":60000,\"total\":180000}]', 180000, 'Bill-1733159432970', '2024-12-08', '06:58:00.000000', 'Morning'),
(81, 'admin@gmail.com', '', '', 'Debit Card', '', '[{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":50000,\"total\":100000}]', 100000, 'Bill-1733159472793', '2024-12-08', '06:58:00.000000', 'Morning'),
(82, 'admin@gmail.com', '', '', 'Cash', '', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":65000,\"total\":65000}]', 65000, 'Bill-1733159593434', '2024-12-08', '06:58:00.000000', 'Morning'),
(83, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"3\",\"price\":75000,\"total\":225000}]', 225000, 'Bill-1733159609816', '2024-12-08', '06:58:00.000000', 'Morning'),
(84, 'admin@gmail.com', '', '', 'Cash', '', '[{\"id\":15,\"name\":\"Cappuccino\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 60000, 'Bill-1733159920815', '2024-12-08', '06:58:00.000000', 'Morning'),
(85, 'admin@gmail.com', NULL, NULL, 'Debit Card', NULL, '[{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"6\",\"price\":65000,\"total\":390000}]', 390000, 'Bill-1733159936190', '2024-12-08', '06:58:00.000000', 'Morning'),
(86, 'admin@gmail.com', '', '', 'Credit Card', '', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"4\",\"price\":60000,\"total\":240000}]', 240000, 'Bill-1733160515907', '2024-12-08', '06:58:00.000000', 'Morning'),
(87, 'admin@gmail.com', '', '', 'Cash', '', '[{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":75000,\"total\":150000}]', 150000, 'Bill-1733163491256', '2024-12-08', '06:58:00.000000', 'Morning'),
(88, 'admin@gmail.com', NULL, NULL, 'Credit Card', NULL, '[{\"id\":28,\"name\":\"Trà đào\",\"category\":\"Trà hoa quả\",\"quantity\":\"1\",\"price\":36000,\"total\":36000}]', 36000, 'Bill-1733163977604', '2024-12-08', '06:58:00.000000', 'Morning'),
(89, 'admin@gmail.com', NULL, NULL, 'Cash', NULL, '[{\"id\":28,\"name\":\"Trà đào\",\"category\":\"Trà hoa quả\",\"quantity\":\"2\",\"price\":36000,\"total\":72000}]', 72000, 'Bill-1733164295978', '2024-12-08', '06:58:00.000000', 'Morning'),
(90, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Cash', '1234567891', '[{\"id\":21,\"name\":\"Iced Americano\",\"category\":\"Iced Coffee\",\"quantity\":\"3\",\"price\":50000,\"total\":150000}]', 150000, 'Bill-1733194279319', '2024-12-11', '17:58:00.000000', 'Evening'),
(91, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Credit Card', '1234567891', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":65000,\"total\":130000},{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":50000,\"total\":100000}]', 230000, 'Bill-1733194367132', '2024-12-11', '17:58:00.000000', 'Evening'),
(92, 'admin@gmail.com', 'bao@gmail.com', 'Bao', 'Credit Card', '1234567891', '[{\"id\":15,\"name\":\"Cappuccino\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":60000,\"total\":120000},{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 170000, 'Bill-1733194449761', '2024-12-11', '17:58:00.000000', 'Evening'),
(93, 'admin@gmail.com', 'bao@gmail.com', 'Bao', NULL, '1234567891', '[{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"5\",\"price\":55000,\"total\":275000}]', 275000, 'Bill-1733194493553', '2024-12-11', '17:58:00.000000', 'Evening'),
(94, 'admin@gmail.com', 'james@gmail.com', 'James Smith', 'Credit Card', '0987654329', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":65000,\"total\":130000},{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"3\",\"price\":50000,\"total\":150000}]', 280000, 'Bill-1733194697049', '2024-12-11', '17:58:00.000000', 'Evening'),
(95, 'admin@gmail.com', 'james@gmail.com', 'James Smith', 'Cash', '0987654329', '[{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"5\",\"price\":55000,\"total\":275000}]', 275000, 'Bill-1733196586503', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(96, 'admin@gmail.com', 'f@gmail.com', 'Quoc', 'Cash', '0948980058', '[{\"id\":29,\"name\":\"Cookie n Cream\",\"category\":\"Specials\",\"quantity\":\"5\",\"price\":45000,\"total\":225000},{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":75000,\"total\":150000},{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 425000, 'Bill-1733837380739', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(97, 'tqbao@gmail.com', 'f@gmail.com', 'Quoc', 'Credit Card', '0948980058', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":60000,\"total\":120000},{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"4\",\"price\":65000,\"total\":260000},{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":55000,\"total\":110000},{\"id\":21,\"name\":\"Iced Americano\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":50000,\"total\":100000},{\"id\":29,\"name\":\"Cookie n Cream\",\"category\":\"Specials\",\"quantity\":\"9\",\"price\":45000,\"total\":405000}]', 995000, 'Bill-1733853062577', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(98, 'tqbao@gmail.com', 'f@gmail.com', 'Quoc', 'Cash', '0948980058', '[{\"id\":21,\"name\":\"Iced Americano\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 50000, 'Bill-1733853726188', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(99, 'tqbao@gmail.com', 'f@gmail.com', 'Quoc', 'Credit Card', '0948980058', '[{\"id\":24,\"name\":\"Macchiato\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"5\",\"price\":50000,\"total\":250000}]', 250000, 'Bill-1733853740274', '2024-12-12', '13:58:00.000000', 'Afternoon'),
(100, 'admin@gmail.com', 'hung@gmail.com', 'Hung', 'Cash', '0912123234', '[{\"id\":29,\"name\":\"Cookie n Cream\",\"category\":\"Specials\",\"quantity\":\"2\",\"price\":45000,\"total\":90000},{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":70000,\"total\":70000},{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":75000,\"total\":75000}]', 235000, 'Bill-1733981899906', '2024-12-12', '12:38:19.000000', 'Afternoon'),
(101, 'admin@gmail.com', 'dung@gmail.com', 'Dung', 'Cash', '0987666666', '[{\"id\":13,\"name\":\"Espresso\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":40000,\"total\":40000}]', 40000, 'Bill-1733982019451', '2024-12-12', '12:40:19.000000', 'Afternoon'),
(102, 'admin@gmail.com', '', '', NULL, '', '[{\"id\":29,\"name\":\"Cookie n Cream\",\"category\":\"Specials\",\"quantity\":\"1\",\"price\":45000,\"total\":45000}]', 45000, 'Bill-1733994050304', '2024-12-12', '16:00:50.000000', 'Afternoon'),
(103, 'tqbao@gmail.com', '', '', NULL, '', '[{\"id\":29,\"name\":\"Cookie n Cream\",\"category\":\"Specials\",\"quantity\":\"1\",\"price\":45000,\"total\":45000}]', 45000, 'Bill-1734015302603', '2024-12-12', '21:55:02.000000', 'Evening'),
(104, 'tqbao@gmail.com', '', '', NULL, '', '[{\"id\":31,\"name\":\"Ocean Blue\",\"category\":\"Soda\",\"quantity\":\"8\",\"price\":50000,\"total\":400000}]', 400000, 'Bill-1734016244977', '2024-12-12', '22:10:44.000000', 'Evening'),
(105, 'admin@gmail.com', '', '', 'Credit Card', '', '[{\"id\":31,\"name\":\"Ocean Blue\",\"category\":\"Soda\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 50000, 'Bill-1734022560808', '2024-12-12', '23:56:00.000000', 'Evening'),
(106, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":65000,\"total\":65000},{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":60000,\"total\":120000},{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"4\",\"price\":55000,\"total\":220000}]', 405000, 'Bill-1734022573603', '2024-12-12', '23:56:13.000000', 'Evening'),
(107, 'admin@gmail.com', 'f@gmail.com', 'Quoc', 'Cash', '0948980058', '[{\"id\":31,\"name\":\"Ocean Blue\",\"category\":\"Soda\",\"quantity\":\"4\",\"price\":50000,\"total\":200000}]', 200000, 'Bill-1734023589289', '2024-12-13', '00:13:10.000000', 'Morning'),
(108, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":65000,\"total\":130000}]', 130000, 'Bill-1734023598796', '2024-12-13', '00:13:19.000000', 'Morning'),
(109, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":75000,\"total\":75000},{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":75000,\"total\":75000}]', 150000, 'Bill-1734023615886', '2024-12-13', '00:13:36.000000', 'Morning'),
(110, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":14,\"name\":\"Americano\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"3\",\"price\":45000,\"total\":135000}]', 135000, 'Bill-1734023620887', '2024-12-13', '00:13:41.000000', 'Morning'),
(111, 'tqbao@gmail.com', 'email@gmail.com', 'Trần Quốc Bảo', 'Cash', '1234567895', '[{\"id\":29,\"name\":\"Cookie n\' Cream\",\"category\":\"Specials\",\"quantity\":\"2\",\"price\":45000,\"total\":90000},{\"id\":21,\"name\":\"Iced Americano\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":50000,\"total\":50000}]', 140000, 'Bill-1734105902779', '2024-12-13', '23:05:02.000000', 'Evening');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Espresso-Based Drinks'),
(3, 'Coffee Brews'),
(4, 'Iced Coffee'),
(11, 'Soda'),
(16, 'Specials'),
(18, 'Pizza');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_discount` bit(1) DEFAULT NULL,
  `last_order` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `point` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `email`, `is_discount`, `last_order`, `name`, `phone_number`, `point`) VALUES
(1, 'bao@gmail.com', b'0', '2024-12-03', 'Tran Nguyen Thien Bao', '1234567891', 0),
(2, 'duy@gmail.com', b'0', '2024-11-28', 'Phan Dinh Duy', '1234567821', 1),
(12, 'james@gmail.com', b'1', '2024-12-03', 'James Smith', '0987654329', 5),
(13, 'quockhanh@gmail.com', b'1', '2024-12-10', 'Tran Quoc Khanh', '0946550051', 7),
(14, 'hung@gmail.com', b'0', '2024-12-12', 'Nguyen Thach Hung', '0912123234', 4),
(16, 'email@gmail.com', b'0', '2024-12-13', 'Trần Quốc Bảo', '1234567895', 3);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `category_fk` int(11) NOT NULL,
  `img` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `description`, `name`, `price`, `status`, `category_fk`, `img`) VALUES
(13, 'A concentrated shot of coffee', 'Espresso', 40000, 'true', 1, 'assets/img/espresso.jpg'),
(14, 'Espresso diluted with hot water', 'Americano', 45000, 'true', 1, 'assets/img/default-drink.png'),
(15, 'Espresso, steamed milk, and foam', 'Cappuccino', 60000, 'true', 1, 'assets/img/default-drink.png'),
(16, 'Coffee brewed using a combination of pressure and immersion', 'AeroPress', 55000, 'true', 3, 'assets/img/aero_press.jpg'),
(17, 'Coffee brewed with cold water over an extended period', 'Cold Brew', 75000, 'true', 3, 'assets/img/default-drink.png'),
(18, 'Coffee brewed by steeping grounds in hot water and pressing down', 'French Press', 60000, 'true', 3, 'assets/img/french_press.jpg'),
(19, 'Coffee brewed by pouring hot water over ground coffee', 'Pour Over', 65000, 'true', 3, 'assets/img/pour_over.jpg'),
(20, 'Espresso and cold milk with ice', 'Iced Latte', 70000, 'true', 4, 'assets\\img\\iced_latte.jpg'),
(21, 'Espresso diluted with cold water and ice', 'Iced Americano', 50000, 'true', 4, 'assets\\img\\iced_americano.jpg\r\n'),
(22, 'Espresso, cold milk, and foam over ice', 'Iced Cappuccino', 75000, 'true', 4, 'assets\\img\\iced_cappuccino.jpg'),
(23, 'Espresso and steamed milk with microfoam', 'Latte', 65000, 'true', 1, 'assets/img/latte.jpg'),
(24, 'Espresso with a dollop of foam', 'Macchiato', 50000, 'true', 1, 'assets/img/macchiato.jpg'),
(29, 'Best Seller', 'Cookie n\' Cream', 45000, 'true', 16, 'assets/img/cookie_cream.jpg'),
(41, 'Soda blue', 'Ocean Blue', 45000, 'true', 11, 'assets/img/default-drink.png'),
(42, 'Chicken Pizza', 'Chicken Pizza', 200000, 'true', 18, 'assets/img/default-drink.png');

-- --------------------------------------------------------

--
-- Table structure for table `revenue`
--

CREATE TABLE `revenue` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_quantity` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `revenue`
--

INSERT INTO `revenue` (`id`, `date`, `product_name`, `product_quantity`, `total`) VALUES
(1, '2024-12-01', 'Cold Brew', 7, 525000),
(5, '2024-11-17', 'Pour Over', 7, 455000),
(6, '2024-11-17', 'Bạc Xỉu', 4, 128000),
(7, '2024-11-17', 'French Press', 1, 60000),
(8, '2024-11-18', 'Bạc Xỉu', 2, 64000),
(9, '2024-11-20', 'Cold Brew', 1, 75000),
(10, '2024-11-21', 'Cold Brew', 3, 225000),
(13, '2024-11-21', 'Pour Over', 1, 65000),
(15, '2024-11-21', 'Iced Latte', 2, 140000),
(16, '2024-11-21', 'French Press', 2, 120000),
(17, '2024-11-21', 'Cappuccino', 1, 60000),
(18, '2024-11-23', 'Trà đào', 5, 180000),
(19, '2024-11-23', 'French Press', 1, 60000),
(20, '2024-11-23', 'Pour Over', 1, 65000),
(21, '2024-11-24', 'Iced Americano', 2, 100000),
(22, '2024-11-26', 'Cafe Đen Đá/Nóng', 6, 150000),
(23, '2024-11-26', 'Iced Latte', 9, 630000),
(24, '2024-11-26', 'Macchiato', 1, 50000),
(25, '2024-11-26', 'Latte', 4, 260000),
(26, '2024-11-26', 'French Press', 1, 60000),
(27, '2024-11-26', 'Cold Brew', 1, 75000),
(28, '2024-11-26', 'AeroPress', 2, 110000),
(29, '2024-11-27', 'Iced Latte', 1, 70000),
(30, '2024-11-27', 'Bạc Xỉu', 2, 64000),
(31, '2024-11-28', 'Iced Americano', 5, 250000),
(32, '2024-11-29', 'Cafe Sữa Đá/Nóng', 4, 116000),
(33, '2024-11-29', 'French Press', 1, 60000),
(34, '2024-12-01', 'Cold Brew', 9, 675000),
(35, '2024-12-02', 'Macchiato', 7, 350000),
(36, '2024-12-02', 'Latte', 6, 390000),
(37, '2024-12-02', 'Cappuccino', 6, 360000),
(38, '2024-11-30', 'French Press', 14, 840000),
(39, '2024-11-27', 'Cold Brew', 7, 525000),
(40, '2024-12-02', 'Cafe Sữa Đá/Nóng', 2, 58000),
(41, '2024-11-28', 'Iced Americano', 10, 500000),
(42, '2024-12-02', 'Iced Cappuccino', 14, 1050000),
(43, '2024-11-30', 'Iced Latte', 7, 490000),
(44, '2024-12-01', 'Trà đào', 1, 36000),
(45, '2024-11-28', 'AeroPress', 2, 110000),
(46, '2024-12-03', 'Latte', 8, 520000),
(47, '2024-11-30', 'French Press', 14, 840000),
(48, '2024-12-03', 'Pour Over', 7, 455000),
(49, '2024-12-03', 'Bạc Xỉu', 1, 32000),
(50, '2024-12-03', 'Cappuccino', 6, 360000),
(51, '2024-12-03', 'Macchiato', 6, 300000),
(52, '2024-12-03', 'Iced Cappuccino', 3, 225000),
(53, '2024-12-03', 'Cold Brew', 2, 150000),
(54, '2024-11-30', 'Trà đào', 3, 108000),
(55, '2024-11-29', 'AeroPress', 5, 275000),
(56, '2024-12-03', 'AeroPress', 5, 275000),
(57, '2024-12-10', 'Macchiato', 1, 50000),
(58, '2024-12-10', 'Cookie n Cream', 5, 225000),
(59, '2024-12-10', 'Cold Brew', 2, 150000),
(60, '2024-12-11', 'Cookie n Cream', 9, 405000),
(61, '2024-12-11', 'Iced Americano', 3, 150000),
(62, '2024-12-11', 'AeroPress', 2, 110000),
(63, '2024-12-11', 'French Press', 2, 120000),
(64, '2024-12-11', 'Pour Over', 4, 260000),
(65, '2024-12-11', 'Macchiato', 5, 250000),
(66, '2024-12-12', 'Cookie n Cream', 4, 180000),
(67, '2024-12-12', 'Iced Latte', 1, 70000),
(68, '2024-12-12', 'Cold Brew', 1, 75000),
(69, '2024-12-12', 'Espresso', 1, 40000),
(70, '2024-12-12', 'Ocean Blue', 9, 450000),
(71, '2024-12-12', 'Pour Over', 1, 65000),
(72, '2024-12-12', 'AeroPress', 4, 220000),
(73, '2024-12-12', 'French Press', 2, 120000),
(74, '2024-12-13', 'Ocean Blue', 4, 200000),
(75, '2024-12-13', 'Latte', 2, 130000),
(76, '2024-12-13', 'Iced Cappuccino', 1, 75000),
(77, '2024-12-13', 'Cold Brew', 1, 75000),
(78, '2024-12-13', 'Americano', 3, 135000),
(79, '2024-12-13', 'Iced Americano', 1, 50000),
(80, '2024-12-13', 'Cookie n\' Cream', 2, 90000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`, `phone_number`, `role`, `status`) VALUES
(4, 'admin@gmail.com', 'admin', '$2a$10$PASKLNTP1YQpvk3gqTwvJOg0W19WfgHR.Uz8BLEKXe89oG4yDio2.', 'xxxxxxxxxx', 'ADMIN', 'true'),
(16, 'tqbao@gmail.com', 'Tran Bao', '$2a$10$Ct9Gv0f14PrFXnVP.o5t9eaAPybKOzMIGKkV/ARvxOVRtbKbgPWHS', '0987654321', 'USER', 'true');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK275nu1ncohhfur6qhxiwrm3go` (`category_fk`);

--
-- Indexes for table `revenue`
--
ALTER TABLE `revenue`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `revenue`
--
ALTER TABLE `revenue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK275nu1ncohhfur6qhxiwrm3go` FOREIGN KEY (`category_fk`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
