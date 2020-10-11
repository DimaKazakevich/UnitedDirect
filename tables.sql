create database UnitedDirect;
use UnitedDirect;
create table Products
(Id int primary key auto_increment,
Name char(200),
Description char(255),
Price decimal(16,2));
use UnitedDirect;
insert into Products(Name, Description, Price) values ('Manchester United Home Shorts 2019 - 20', 'Designed for United fans, the shorts offer a looser fit than those of your favourite players, while the lightweight and sweat-wicking Climalite technology helps to keep you dry throughout the 90 minutes. The shorts’ shield shaped badge also pays homage to Sir Alex Ferguson’s all conquering treble winning team of the 1998-99 season.', 45 );
insert into Products(Name, Description, Price) values ('Manchester United Home Socks 2019 - 20', 'Designed to give you on-the-go comfort, the socks hug your foot at the arch, while also providing additional support for your ankle and cushioning high stress areas. The socks’ mesh inserts also give you increased ventilation.', 21 );
insert into Products(Name, Description, Price) values ('Manchester United Home Shirt 2019 - 20 - Long Sleeve', 'Manchester Uniteds treble-winning team earned the club a place in the record books and hearts of football fans worldwide. This home jersey honours 20 years since that fateful night in Barcelona. Made for fans, this version has a looser cut than the one players wear on match days. Its built from sweat-wicking fabric, with commemorative details and a special crest honouring their incredible 1998/99 season.', 45 );

use UnitedDirect;
create table Categories
(
Id int primary key auto_increment,
Category varchar(50)
);
use UnitedDirect;
create table CategoryDetails
(
Id int primary key auto_increment,
ProductId int,
CategoryId int,
constraint categorydetails_products_fk 
foreign key (ProductId) references Products(Id),
constraint categorydetails_categories_fk 
foreign key (CategoryId) references Categories(Id)
);

insert into Categories(Category) value('Kits');
insert into Categories(Category) value('Training');

insert into CategoryDetails(ProductId, CategoryId) values(1, 2);
insert into CategoryDetails(ProductId, CategoryId) values(2, 2);
insert into CategoryDetails(ProductId, CategoryId) values(3, 2);
insert into CategoryDetails(ProductId, CategoryId) values(4, 1);