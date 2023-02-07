--Kellner
INSERT INTO SERVICE(FIRST_NAME, LAST_NAME)
VALUES ('Hans', 'Müller');

INSERT INTO SERVICE(FIRST_NAME, LAST_NAME)
VALUES ('Oliver', 'Daxinger');

INSERT INTO SERVICE(FIRST_NAME, LAST_NAME)
VALUES ('Bertl', 'Bolasch');

--Speisen/Getränke
INSERT INTO DISH(NAME)
VALUES ('Getränke');

INSERT INTO DISH(NAME)
VALUES ('Hauptspeise');

--Produkte
INSERT INTO PRODUCT(DISH_NR, NAME, PRICE)
VALUES (1, 'Coca-Cola', 1.50);

INSERT INTO PRODUCT(DISH_NR, NAME, PRICE)
VALUES (1, 'Fanta', 1.30);

INSERT INTO PRODUCT(DISH_NR, NAME, PRICE)
VALUES (1, 'Sprite', 1.40);

INSERT INTO PRODUCT(DISH_NR, NAME, PRICE)
VALUES (2, 'Kebab Pizza', 12.20);

INSERT INTO PRODUCT(DISH_NR, NAME, PRICE)
VALUES (2, 'Cheese Burger', 4.90);

--Tische
INSERT INTO DESK(WORKING_NR)
VALUES (1);

INSERT INTO DESK(WORKING_NR)
VALUES (2);

INSERT INTO DESK(WORKING_NR)
VALUES (3);

INSERT INTO DESK(WORKING_NR)
VALUES (1);

--Rechnungen
INSERT INTO BILL(WORKING_NR, DESK_NR)
VALUES (1, 1);

INSERT INTO BILL(WORKING_NR, DESK_NR)
VALUES (1, 4);

INSERT INTO BILL(WORKING_NR, DESK_NR)
VALUES (2, 2);

INSERT INTO BILL(WORKING_NR, DESK_NR)
VALUES (3, 3);

--Bestellung
INSERT INTO INVOICE(BILL_NR, ITEM_NR, AMOUNT_OF_ITEM, PRICE)
VALUES (1, 1, 1, 1.50);

INSERT INTO INVOICE(BILL_NR, ITEM_NR, AMOUNT_OF_ITEM, PRICE)
VALUES (1, 4, 1, 12.20);

INSERT INTO INVOICE(BILL_NR, ITEM_NR, AMOUNT_OF_ITEM, PRICE)
VALUES (2, 2, 1, 1.30);

INSERT INTO INVOICE(BILL_NR, ITEM_NR, AMOUNT_OF_ITEM, PRICE)
VALUES (3, 3, 1, 1.40);