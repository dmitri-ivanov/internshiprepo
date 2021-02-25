**Internship console application**        
You are going to use internship console application. All the interaction with user happened through command line. All the data stored in MySQL database, connection specified in application.properties. Sample.sql includes some DDL & DML.   
To run the program just run main method in IntershipprojectApplication class.  
There are some simple commands you are able to use:  
- createProduct : allows you to create new Product and save it to database. You just need to specify product name and price in one row using the delimeter ",";  
- showAll : shows all created products;  
- showAllOrdered : shows all the products that have ever been ordered;  
- createOrder : allows to create a new Order with particular product and quantity;  
- updateOrder : helps to update existing order by id and add another product;  
- showAllOrders : shows detail info about every order, products in this order, total product prices, quantity and time of order creation;  
- removeProduct : allows to delete product with specified id;  
- removeAllProducts : remove all the products from database, but first you need to enter the password. You can to specify the password in application.properties file (key remove.password);  
- help : shows available commands;
- quit : break the program.

