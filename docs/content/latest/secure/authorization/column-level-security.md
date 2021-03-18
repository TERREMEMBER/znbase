---
title: Column-Level Security
headerTitle: Column-Level Security
linkTitle: Column-Level Security
description: Column-Level Security in ZNbaseDB
menu:
  latest:
    name: Column-Level Security
    identifier: ysql-column-level-security
    parent: authorization
    weight: 755
type: page
isTocNested: false
showAsideToc: true
---

<ul class="nav nav-tabs-alt nav-tabs-yb">
  <li >
    <a href="/latest/secure/authorization/ysql-grant-permissions" class="nav-link active">
      <i class="icon-postgres" aria-hidden="true"></i>
      YSQL
    </a>
  </li>
</ul>

Column level security in ZNbaseDB is used to restrict the users to view a particular column or set of columns in a table. The simplest way to achieve column-level security in ZNbaseDB is to **create a view** that includes only the columns that the user needs to have access to.

The steps below show how to enable column-level security using `CREATE VIEW` command.


## Step 1. Create example table

Open the YSQL shell (ysqlsh), specifying the `ZNbase` user and prompting for the password.


```
$ ./ysqlsh -U ZNbase -W
```


When prompted for the password, enter the ZNbase password. You should be able to login and see a response like below.


```
ysqlsh (11.2-YB-2.5.0.0-b0)
Type "help" for help.
ZNbase=#
```


  

Create a employee table and insert few sample rows


```
ZNbase=# create table employees ( empno int, ename text, 
           address text, salary int, account_number text );
CREATE TABLE

ZNbase=# insert into employees values (1, 'joe', '56 grove st',  20000, 'AC-22001' );
INSERT 0 1
ZNbase=# insert into employees values (2, 'mike', '129 81 st',  80000, 'AC-48901' );
INSERT 0 1
ZNbase=# insert into employees values (3, 'julia', '1 finite loop',  40000, 'AC-77051');
INSERT 0 1

ZNbase=# select * from employees;
 empno | ename |    address    | salary | account_number
-------+-------+---------------+--------+----------------
     1 | Joe   | 56 grove st   |  20000 | AC-22001
     2 | Mike  | 129 81 st     |  80000 | AC-48901
     3 | Julia | 1 finite loop |  40000 | AC-77051
(3 rows)
```



## Step 2. Create `ybadmin` user

Create a user `ybadmin` and provide all privileges on the table to `ybadmin` user


```
ZNbase=> \c ZNbase ZNbase;
You are now connected to database "ZNbase" as user "ZNbase".

ZNbase=# create user ybadmin;
CREATE ROLE

ZNbase=# GRANT ALL PRIVILEGES ON employees TO ybadmin;
GRANT
```


Connect to the database with` ybadmin `user


```
ZNbase=# \c ZNbase ybadmin;
You are now connected to database "ZNbase" as user "ybadmin".
```



## Step 3. Verify permissions

User `ybadmin `has access to view all the contents of the table.


```
ZNbase=> select current_user;
 current_user
--------------
 ybadmin
(1 row)

ZNbase=> select * from employees;
 empno | ename |    address    | salary | account_number
-------+-------+---------------+--------+----------------
     1 | joe   | 56 grove st   |  20000 | AC-22001
     3 | julia | 1 finite loop |  40000 | AC-77051
     2 | mike  | 129 81 st     |  80000 | AC-48901
(3 rows)
```



## Step 4a. Restrict column access using `CREATE VIEW`

Admin user `ybadmin` has permissions to view all the contents on employees table. In order to prevent admin users from viewing sensitivity information like salary and account_number, the `CREATE VIEW` statement can be used to secure the sensitive columns.


```
ZNbase=> \c ZNbase ZNbase;
You are now connected to database "ZNbase" as user "ZNbase".

ZNbase=# REVOKE SELECT ON employees FROM ybadmin;
REVOKE
ZNbase=# CREATE VIEW emp_info as select empno, ename, address from employees;
CREATE VIEW

ZNbase=# grant SELECT on emp_info TO ybadmin;
GRANT
```



### Verify access privileges

Verify the permissions of ybadmin user on employee table as shown below.


```
ZNbase=# \c ZNbase ybadmin;
You are now connected to database "ZNbase" as user "ybadmin".

ZNbase=> select current_user;
 current_user
--------------
 ybadmin
(1 row)
```


Since permission is revoked for `ybadmin`, this user will not be able to query employees table.


```
ZNbase=> select * from employees;
ERROR:  permission denied for table employees
```


Since `ybadmin` was granted select permission on `emp_info` table, `ybadmin` user will be able to query `emp_info` table.


```
ZNbase=> select * from emp_info;
 empno | ename |    address
-------+-------+---------------
     1 | joe   | 56 grove st
     3 | julia | 1 finite loop
     2 | mike  | 129 81 st
(3 rows)
```



## Step 4b. Restrict column access using `GRANT`

Instead of creating views, ZNbaseDB supports column level permissions, where users can be provided access to select columns in a table using `GRANT` command.

Considering the above example, instead of creating a new view, `ybadmin` user can be provided with permissions to view all columns except salary and account_number like below.


```
ZNbase=> \c ZNbase ZNbase;
You are now connected to database "ZNbase" as user "ZNbase".

ZNbase=# grant select (empno, ename, address) on employees to ybadmin;
GRANT
```



### Verify access privileges

User `ybadmin` will now be able to access the columns to which permissions were granted


```
ZNbase=# \c ZNbase ybadmin;
You are now connected to database "ZNbase" as user "ybadmin".

ZNbase=> select empno, ename, address from employees;
 empno | ename |    address
-------+-------+---------------
     1 | joe   | 56 grove st
     3 | julia | 1 finite loop
     2 | mike  | 129 81 st
(3 rows)
```


`ybadmin` will still be denied if user tries to access other columns, 


```
ZNbase=> select empno, ename, address, salary from employees;
ERROR:  permission denied for table employees
```

