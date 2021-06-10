授权root 用户可以远程登录

```sql
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'youpassword' WITH GRANT OPTION;
```

修改mysql 密码：

```sql
 alter user 'root'@'localhost' identified by '123456';
```



