-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2024 at 06:12 PM
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
  `date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`id`, `created_by`, `email`, `name`, `payment_method`, `phone_number`, `product_details`, `total`, `uuid`, `date`) VALUES
(13, 'tqbao@gmail.com', 'minhnhat@gmail.com', 'Minh Nhat Nguyen', 'Cash', '1234567890', '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":75000,\"total\":150000}]', 150000, 'Bill-1724149130870', NULL),
(14, 'tqbao@gmail.com', 'alexSandro11@gmail.com', 'Alex Sandro', 'Cash', '0379294258', '[{\"id\":23,\"name\":\"Latte\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"1\",\"price\":65000,\"total\":65000},{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":65000,\"total\":130000}]', 195000, 'Bill-1724149193795', NULL),
(15, 'tqbao@gmail.com', 'dialopez@gmail.com', 'Diago Lopez', 'Credit Card', '3453535345', '[{\"id\":16,\"name\":\"AeroPress\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":55000,\"total\":55000},{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000},{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":65000,\"total\":65000},{\"id\":17,\"name\":\"Cold Brew\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":75000,\"total\":75000}]', 255000, 'Bill-1724149253123', NULL),
(16, 'admin@gmail.com', 'm10@gmail.com', 'Lionel Messi', 'Debit Card', '1234567891', '[{\"id\":14,\"name\":\"Americano\",\"category\":\"Espresso-Based Drinks\",\"quantity\":\"2\",\"price\":45000,\"total\":90000}]', 90000, 'Bill-1724149369921', NULL),
(17, 'admin@gmail.com', 'tqbao2363@gmail.com', 'Bao', NULL, '0948980058', '[]', 0, 'Bill-1728962112062', NULL),
(19, 'admin@gmail.com', 'tqbao2363@gmail.com', 'fdvd', NULL, '0948980058', '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":29000,\"total\":29000}]', 29000, 'Bill-1728962164316', NULL),
(20, 'admin@gmail.com', 'tqbao2363@gmail.com', 'fdvd', 'Cash', '0948980058', '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000}]', 96000, 'Bill-1730776045763', NULL),
(21, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[]', 0, 'Bill-1731696209182', NULL),
(22, 'admin@gmail.com', 'tqbao2363@gmail.com', 'fdvd', 'Credit Card', '0948980058', '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000}]', 96000, 'Bill-1731696262537', NULL),
(23, 'admin@gmail.com', 'testEmail@gmail.com', 'aaa', 'Cash', 'xyzxzyzxyz', '[{\"name\": \"Milk Coffee\", \"category\": \"Coffee\", \"quantity\": 1, \"price\": 100, \"total\": 100}, {\"name\": \"Tea Coffee\", \"category\": \"Coffee\", \"quantity\": 2, \"price\": 260, \"total\": 260} ]', 360, 'Bill-1731696854305', '2024-11-16 01:54:14.000000'),
(24, 'admin@gmail.com', 'tqbao2363@gmail.com', 'Bao', 'Credit Card', '0948980058', '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":29000,\"total\":29000},{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":32000,\"total\":64000}]', 93000, 'Bill-1731696931388', '2024-11-16 01:55:31.000000'),
(25, 'admin@gmail.com', 'tqbao2363@gmail.com', 'fdvd', NULL, '0948980058', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000},{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":3,\"price\":32000,\"total\":96000}]', 156000, 'Bill-1731791667699', '2024-11-17 04:14:27.000000'),
(26, 'tqbao@gmail.com', 'asdas@gmail.com', 'khasch', 'Cash', '1234567890', '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":32000,\"total\":64000}]', 64000, 'Bill-1731832558618', '2024-11-17 15:35:58.000000'),
(27, 'tqbao@gmail.com', 'tqbao2363@gmail.com', 'fdvd', 'Debit Card', '0948980058', '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000}]', 60000, 'Bill-1731832687590', '2024-11-17 15:38:07.000000'),
(28, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":2,\"price\":32000,\"total\":64000}]', 64000, 'Bill-1731834068675', '2024-11-17 16:01:08.000000'),
(29, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000},{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":70000,\"total\":70000}]', 166000, 'Bill-1731834514578', '2024-11-17 16:08:34.000000'),
(30, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"4\",\"price\":29000,\"total\":116000},{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":75000,\"total\":75000}]', 191000, 'Bill-1731834575047', '2024-11-17 16:09:35.000000'),
(31, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":75000,\"total\":150000},{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000}]', 246000, 'Bill-1731834622266', '2024-11-17 16:10:22.000000'),
(32, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"1\",\"price\":75000,\"total\":75000},{\"id\":26,\"name\":\"Cafe Sữa Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":29000,\"total\":58000}]', 133000, 'Bill-1731834652531', '2024-11-17 16:10:52.000000'),
(33, 'tqbao@gmail.com', 'email@example.vn', 'Hello', 'Credit Card', '1234567890', '[{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"3\",\"price\":75000,\"total\":225000},{\"id\":25,\"name\":\"Cafe Đen Đá/Nóng\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":25000,\"total\":25000}]', 250000, 'Bill-1731858305246', '2024-11-17 22:45:05.000000'),
(34, 'tqbao@gmail.com', 'email@example.vn', 'Hello12121212', 'Cash', '1234567890', '[{\"id\":20,\"name\":\"Iced Latte\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":70000,\"total\":140000},{\"id\":22,\"name\":\"Iced Cappuccino\",\"category\":\"Iced Coffee\",\"quantity\":\"2\",\"price\":75000,\"total\":150000}]', 290000, 'Bill-1731858865110', '2024-11-17 22:54:25.000000'),
(35, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"1\",\"price\":32000,\"total\":32000}]', 32000, 'Bill-1731858959538', '2024-11-17 22:55:59.000000'),
(36, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":18,\"name\":\"French Press\",\"category\":\"Coffee Brews\",\"quantity\":\"1\",\"price\":60000,\"total\":60000},{\"id\":19,\"name\":\"Pour Over\",\"category\":\"Coffee Brews\",\"quantity\":\"2\",\"price\":65000,\"total\":130000}]', 190000, 'Bill-1731858995381', '2024-11-17 22:56:35.000000'),
(37, 'tqbao@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"3\",\"price\":32000,\"total\":96000}]', 96000, 'Bill-1731859018433', '2024-11-17 22:56:58.000000'),
(38, 'admin@gmail.com', NULL, NULL, NULL, NULL, '[{\"id\":27,\"name\":\"Bạc Xỉu\",\"category\":\"Cafe\",\"quantity\":\"2\",\"price\":32000,\"total\":64000}]', 64000, 'Bill-1731870389319', '2024-11-18 02:06:29.000000'),
(39, 'admin@gmail.com', 'testEmail@gmail.com', 'aaa', 'Cash', 'xyzxzyzxyz', '[{\"name\": \"Americano\", \"category\": \"Espresso-Based\", \"quantity\": 1, \"price\": 45000, \"total\": 45000}, {\"name\": \"Latte\", \"category\": \"Espresso-Based\", \"quantity\": 2, \"price\": 65000, \"total\": 130000} ]', 175000, 'Bill-1732198292115', '2024-11-21 21:11:32.000000');

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
(8, 'Pizza'),
(9, 'Cafe'),
(10, 'Hồng trà'),
(11, 'Soda'),
(12, 'Sinh tố'),
(13, 'Nước ép'),
(14, 'Trà sữa'),
(15, 'Trà hoa quả'),
(16, 'Specials'),
(17, 'Trà hoa quả');

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
  `category_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `description`, `name`, `price`, `status`, `category_fk`) VALUES
(13, 'A concentrated shot of coffee', 'Espresso', 40000, 'false', 1),
(14, 'Espresso diluted with hot water', 'Americano', 45000, 'false', 1),
(15, 'Espresso, steamed milk, and foam', 'Cappuccino', 60000, 'true', 1),
(16, 'Coffee brewed using a combination of pressure and immersion', 'AeroPress', 55000, 'true', 3),
(17, 'Coffee brewed with cold water over an extended period', 'Cold Brew', 75000, 'true', 3),
(18, 'Coffee brewed by steeping grounds in hot water and pressing down', 'French Press', 60000, 'true', 3),
(19, 'Coffee brewed by pouring hot water over ground coffee', 'Pour Over', 65000, 'true', 3),
(20, 'Espresso and cold milk with ice', 'Iced Latte', 70000, 'true', 4),
(21, 'Espresso diluted with cold water and ice', 'Iced Americano', 50000, 'true', 4),
(22, 'Espresso, cold milk, and foam over ice', 'Iced Cappuccino', 75000, 'true', 4),
(23, 'Espresso and steamed milk with microfoam', 'Latte', 65000, 'true', 1),
(24, 'Espresso with a dollop of foam', 'Macchiato', 50000, 'true', 1),
(25, 'Cafe', 'Cafe Đen Đá/Nóng', 25000, 'true', 9),
(26, 'Cafe', 'Cafe Sữa Đá/Nóng', 29000, 'true', 9),
(27, 'Cafe', 'Bạc Xỉu', 32000, 'true', 9),
(28, '', 'Trà đào', 36000, 'true', 17);

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
(1, '2024-11-17', 'Cold Brew', 2, 150000),
(5, '2024-11-17', 'Pour Over', 7, 455000),
(6, '2024-11-17', 'Bạc Xỉu', 4, 128000),
(7, '2024-11-17', 'French Press', 1, 60000),
(8, '2024-11-18', 'Bạc Xỉu', 2, 64000),
(9, '2024-11-20', 'Cold Brew', 1, 75000),
(10, '2024-11-21', 'Cold Brew', 3, 225000),
(13, '2024-11-21', 'Pour Over', 1, 65000),
(15, '2024-11-21', 'Iced Latte', 2, 140000),
(16, '2024-11-21', 'French Press', 2, 120000),
(17, '2024-11-21', 'Cappuccino', 1, 60000);

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
(1, 'ntt@gmail.com', 'Nguyen Thi Thap', '$2a$10$h1oxHqimF9KybcPQMmzHIuF.zv2Rm7Oo2apdjYk8TZyTg8fuDnv3C', '0987654321', 'USER', 'false'),
(4, 'admin@gmail.com', 'admin', '$2a$10$PASKLNTP1YQpvk3gqTwvJOg0W19WfgHR.Uz8BLEKXe89oG4yDio2.', 'xxxxxxxxxx', 'ADMIN', 'true'),
(8, 'hiepgale0817@gmail.com', 'hiepga', '$2a$10$yXt0Ya0Pjeg1wN1Kfm2O/eDCOQhYDS/Dua6WzUm6Dlk1rnwM8PB6y', '0987654321', 'USER', 'true'),
(9, 'hungreomtp@gmail.com', 'Nguyen Hung', '$2a$10$h0WGRT6o0AoMWHT6TatSa.jpiS.M1BCrGS26si02YoQ0L6WQcyEDe', '0987654321', 'USER', 'false'),
(10, 'xuanltt2233@gmail.com', 'Xuan Vu', '$2a$10$tmywM6wdnNK54Y1AJZ.8QOBtwgefgyTt9H3tJHpLDYwOjbbAqsbpu', '0987654321', 'USER', 'false'),
(11, 'kelvin120803@gmail.com', 'Quoc Huy', '$2a$10$EYdx5AwaaHvs94XOvPJnr.LllGFRwBEAMco7v3dzeCH8yHfm5I14K', '0987654321', 'USER', 'false'),
(12, 'huynhbale777@gmail.com', 'Huynh Ba', '$2a$10$rzFo.JF1PfDtoNueyr8MY.K5htSA6PQp7TnOS7rdX7GjDH68f/Fm6', '0987654321', 'USER', 'false'),
(13, 'minhlatui1365@gmail.com', 'Quang Minh', '$2a$10$aetYEirVeGL4MHGzb8fFfOnbafL2lIqx4WWgYmga9WfbWDj9DEWle', '0987654321', 'USER', 'false'),
(14, 'minhnhut@gmail.com', 'Le Nguyen Minh Nhut', '$2a$10$GqBZlXmn3rQPqAmQcqhRJelaQ9rwipf6OxXq62zf05JU5kLaUtykm', '0987654321', 'USER', 'false'),
(16, 'tqbao@gmail.com', 'Tran Bao', '$2a$10$Ct9Gv0f14PrFXnVP.o5t9eaAPybKOzMIGKkV/ARvxOVRtbKbgPWHS', '0987654321', 'USER', 'true'),
(21, 'duy@gmail.com', 'Nhat Duy', '$2a$10$0DjDPbkvNQLahbcnAEB2ruuRUjV9IGNTcaDMb1kD0dxAfdysk0lb6', '1234567890', 'USER', 'false'),
(23, 'hoang2004xyz@gmail.com', 'Momo', '$2a$10$/ZU98vtcRh2PMPFn06cUNOUkCoY5yzzcNYMABmhqLAFtWKlESgARi', '1234567891', 'USER', 'true'),
(24, 'tranvan@gmail.com', 'Tran Van A', '$2a$10$jmL5ORME08lFRBEgefLRcuTxyD9O7vHfjk83YyNa/nvPO0BdVEe2y', '1234567892', 'USER', 'false');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `revenue`
--
ALTER TABLE `revenue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

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
