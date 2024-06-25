
--restaurantsテーブル--
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (1, 'まるや本店', 'maruya.jpg', 'うなぎの蒲焼のセットメニューや寿司を楽しめるモダンな店。テイクアウトもある。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (2, 'あつた蓬莱店', 'atsuta.jpg', '落ち着いた和風で長年の歴史を持つ老舗の鰻屋で、創業当時より継ぎ足されている秘伝のタレや備長炭で焼いたこだわりの鰻を頂ける。。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (3, '大貫名古屋店', 'onuki1.jpg', '大貫が経営する美味しい店', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (4, '大貫名古屋店2', 'onuki2.jpg', '大貫2号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (5, '大貫名古屋店3', 'onuki3.jpg', '大貫3号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000','名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (6, '大貫名古屋店4', 'onuki4.jpg', '大貫4号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (7, '大貫名古屋店5', 'onuki5.jpg', '大貫5号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (8, '大貫名古屋店6', 'onuki6.jpg', '大貫6号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000','名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (9, '大貫名古屋店7', 'onuki7.jpg', '大貫7号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000','名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (10, '大貫名古屋店8', 'onuki8.jpg', '大貫8号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000','名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (11, '大貫名古屋店9', 'onuki9.jpg', '大貫9号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000','名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (12, '大貫名古屋店10', 'onuki10.jpg', '大貫10号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);
INSERT IGNORE INTO restaurants (restaurant_id, name, image_name, restaurant_description, lowest_price, highest_price, opening_time, closing_time, phone_number, address, regular_holiday, capacity) VALUES (13, '大貫名古屋店11', 'onuki11.jpg', '大貫11号店。', 1000, 6000, '10:00:00', '22:00:00', '090-0000-0000', '名古屋', '毎週金曜日', 50);



--reviewsテーブル--
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (1, 1, 1, '美味しかったです', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (2, 2, 2, 'まずかったです', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (3, 3, 3, '微妙', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (4, 4, 4, '返金レベル', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (5, 5, 5, '最高！', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (6, 6, 6, 'よかったです', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (7, 7, 7, '雰囲気も味もよかった', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (8, 8, 8, '食中毒になった', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (9, 9, 9, 'また行きたいです', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (10, 10, 10, 'うーん', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (11, 11, 11, 'メニュー写真と実際に見た料理のボリューム感が全然違った', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (12, 12, 12, '予約したのに待たされた', 1);
INSERT IGNORE INTO reviews (id, user_id, restaurant_id, sentense, score) VALUES (13, 13, 13, '来週も行きます', 1);

--rolesテーブル--
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_SUBSCRIBER');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_ADMIN');

--usersテーブル--
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (1, 1, '太郎', 'タロウ', 'sample@gmail.com', 'aoyama1093', '厚木市飯山', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (2, 1, '次郎', 'ジロウ', 'jiro@gmail.com', 'aoyama1093', '厚木市飯山', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (3, 1, '三郎', 'サブロウ', 'saburo@gmail.com', 'aoyama1093', '厚木市恩名', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (4, 2, '四郎', 'シロウ', 'siro@gmail.com', 'aoyama1093', '厚木市東', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (5, 3, '五郎', 'ゴロウ', 'goro@gmail.com', 'aoyama1093', '厚木市西', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (6, 1, '六郎', 'ゴロウ', 'rokuro@gmail.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '厚木市西', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (7, 3, '七郎', 'ナナロウ', 'nana@gmail.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '厚木市西', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (10, 2, '八郎', 'ハチロウ', 'hati@gmail.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '厚木市西', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (18, 1, '九郎', 'クロウ', 'kyu@gmail.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '厚木市西', '090-0000-0000',true);
INSERT IGNORE INTO users (id, role_id, name, kana, email, password, address, phone_number,enabled) VALUES (19, 1, 'アップグレードテスト', 'アップグレードテスト', 'upgrade@test.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', '厚木市西', '090-0000-0000',true);

--reservationテーブル--
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (1, 1, 1, '2023-04-01', '11:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (2, 2, 1, '2023-04-01', '12:00', 3);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (3, 3, 1, '2023-04-01', '13:00', 4);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (4, 4, 1, '2023-04-01', '12:00', 5);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (5, 5, 1, '2023-04-01', '11:00', 6);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (6, 6, 1, '2023-04-01', '11:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (7, 7, 1, '2023-04-01', '12:00', 3);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (8, 8, 1, '2023-04-01', '14:00', 4);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (9, 9, 1, '2023-04-01', '19:00', 5);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (10, 10, 1, '2023-04-01', '18:00', 6);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (11, 11, 1, '2023-04-01', '11:00', 2);
INSERT IGNORE INTO reservations (id, restaurant_id, user_id, reservation_datetime, reservation_time, number_of_people) VALUES (12, 12, 6, '2023-04-01', '11:00', 2);

--method_of_paymentテーブル--
INSERT IGNORE INTO method_of_payment (id, user_id,nominee,card_number,sec_number,card_type,period_year,period_month) VALUES (1, 10,'hatiro','3333-3333-3333','234','VISA','2024','09');

