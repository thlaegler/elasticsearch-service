INSERT INTO category (id, description, meta_description, meta_keywords, meta_title, name) VALUES (NULL, NULL, NULL, NULL, NULL, "T-Shirts");
INSERT INTO category (id, description, meta_description, meta_keywords, meta_title, name) VALUES (NULL, NULL, NULL, NULL, NULL, "Shoes");
INSERT INTO category (id, description, meta_description, meta_keywords, meta_title, name) VALUES (NULL, NULL, NULL, NULL, NULL, "Jeans");

INSERT INTO price (id, amount, currency, discount_rate, tax_rate) VALUES (NULL, "49.99", "US Dollar", 0.0, 0.0);
INSERT INTO price (id, amount, currency, discount_rate, tax_rate) VALUES (NULL, "29.99", "US Dollar", 0.0, 0.0);
INSERT INTO price (id, amount, currency, discount_rate, tax_rate) VALUES (NULL, "9.99", "US Dollar", 0.0, 0.0);

INSERT INTO product (id, description, item_code, name, price_id) VALUES (NULL, "Blue", "123", "Blue Shirt", 1);
INSERT INTO product (id, description, item_code, name, price_id) VALUES (NULL, "Red", "321", "Red Shirt", 2);
INSERT INTO product (id, description, item_code, name, price_id) VALUES (NULL, "Green", "231", "Green Shirt", 3);

INSERT INTO product_categories (product_id, categories_id) VALUES (1, 1);
INSERT INTO product_categories (product_id, categories_id) VALUES (1, 2);
INSERT INTO product_categories (product_id, categories_id) VALUES (2, 2);
INSERT INTO product_categories (product_id, categories_id) VALUES (2, 3);
INSERT INTO product_categories (product_id, categories_id) VALUES (3, 3);
INSERT INTO product_categories (product_id, categories_id) VALUES (3, 1);