
CREATE TABLE [Account] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[username] NVARCHAR(255),
	[password] NVARCHAR(255),
	[email] NVARCHAR(255),
	[phonenumber] NVARCHAR(255),
	[role] NVARCHAR(255),
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Customer
CREATE TABLE [Customer] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[name] NVARCHAR(255),
	[dob] DATE,
	[gender] BIT,
	[address_id] INT,
	[username] NVARCHAR(255),
	[password] NVARCHAR(255),
	[email] NVARCHAR(255),
	[phonenumber] NVARCHAR(255),
	[create_date] DATE,
	[status] BIT,
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Restaurant
CREATE TABLE [Restaurant] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[name] NVARCHAR(255),
	[address_id] INT,
	[phonenumber] NVARCHAR(255),
	[restaurant_rating] NVARCHAR(255),
	[account_id] INT,
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Order
CREATE TABLE [Order] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[restaurant_id] INT,
	[delivery_id] INT,
	[customer_id] INT,
	[total_amount] FLOAT,
	[status] NVARCHAR(255),
	[order_date] DATE,
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Delivery
CREATE TABLE [Delivery] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[address_id] INT,
	[ship_price] FLOAT,
	[delivery_date] DATE,
	[status] NVARCHAR(255),
	[delivery_person] INT,
	[delivery_rating] NVARCHAR(255),
	[account_id] INT,
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Address
CREATE TABLE [Address] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[details] NVARCHAR(255),
	[state] NVARCHAR(255),
	[street] NVARCHAR(255),
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Feedback
CREATE TABLE [Feedback] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[customer_id] INT,
	[value] INT,
	[comment] NVARCHAR(255),
	[order_id] INT,
	[image] NVARCHAR(255),
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Manager
CREATE TABLE [Manager] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[name] NVARCHAR(255),
	[dob] NVARCHAR(255),
	[gender] BIT,
	[account_id] INT,
	PRIMARY KEY([id])
);
GO

-- Tạo bảng OrderDetails
CREATE TABLE [OrderDetails] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[order_id] INT,
	[product_id] INT,
	[quantity] INT,
	[price] FLOAT,
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Delivery_person
CREATE TABLE [Delivery_person] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[name] NVARCHAR(255),
	[dob] DATE,
	[gender] BIT,
	[email] NVARCHAR(255),
	[phonenumber] NVARCHAR(255),
	PRIMARY KEY([id])
);
GO

-- Tạo bảng Product
CREATE TABLE [Product] (
	[product_id] INT NOT NULL IDENTITY UNIQUE,
	[name] NVARCHAR(255),
	[price] FLOAT,
	[description ] NVARCHAR(255),
	[image] NVARCHAR(255),
	[status] BIT,
	[create_date] DATE,
	PRIMARY KEY([product_id])
);
GO

-- Tạo bảng Cart
CREATE TABLE [Cart] (
	[cart_id] INT NOT NULL IDENTITY UNIQUE,
	[quantity] INT,
	[customer_id] INT,
	PRIMARY KEY([cart_id])
);
GO

-- Tạo bảng Category
CREATE TABLE [Category] (
	[id] INT NOT NULL IDENTITY UNIQUE,
	[name] NVARCHAR(255),
	PRIMARY KEY([id])
);
GO

-- Tạo bảng product_cart
CREATE TABLE [product_cart] (
	[id] INT NOT NULL IDENTITY PRIMARY KEY,
	[product_id] INT NOT NULL,
	[cart_id] INT NOT NULL,
	UNIQUE (product_id, cart_id)
);
GO

-- Thêm khóa ngoại
ALTER TABLE [Restaurant]
ADD FOREIGN KEY([account_id]) REFERENCES [Account]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Manager]
ADD FOREIGN KEY([account_id]) REFERENCES [Account]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [OrderDetails]
ADD FOREIGN KEY([order_id]) REFERENCES [Order]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Delivery]
ADD FOREIGN KEY([delivery_person]) REFERENCES [Delivery_person]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Delivery]
ADD FOREIGN KEY([account_id]) REFERENCES [Account]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Restaurant]
ADD FOREIGN KEY([address_id]) REFERENCES [Address]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Customer]
ADD FOREIGN KEY([address_id]) REFERENCES [Address]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Delivery]
ADD FOREIGN KEY([address_id]) REFERENCES [Address]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Feedback]
ADD FOREIGN KEY([customer_id]) REFERENCES [Customer]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Order]
ADD FOREIGN KEY([restaurant_id]) REFERENCES [Restaurant]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Order]
ADD FOREIGN KEY([delivery_id]) REFERENCES [Delivery]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Cart]
ADD FOREIGN KEY([customer_id]) REFERENCES [Customer]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [OrderDetails]
ADD FOREIGN KEY([product_id]) REFERENCES [Product]([product_id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Order]
ADD FOREIGN KEY([customer_id]) REFERENCES [Customer]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [product_cart]
ADD FOREIGN KEY([product_id]) REFERENCES [Product]([product_id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [product_cart]
ADD FOREIGN KEY([cart_id]) REFERENCES [Cart]([cart_id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Feedback]
ADD FOREIGN KEY([order_id]) REFERENCES [Order]([id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO

ALTER TABLE [Category]
ADD FOREIGN KEY([id]) REFERENCES [Product]([product_id])
ON UPDATE NO ACTION ON DELETE NO ACTION;
GO
