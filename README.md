# QuestionsSystem-Windows
试题库系统的windows平台版本，用于admin登录管理数据。

该项目分成三种用户，管理员使用widows端，即本项目，[老师和学生使用web端](https://github.com/black-six/QuestionsSystem-Web)
登陆使用，两个项目数据库互通。

本项目主要使用swing创建Windows窗体，利用swing表格对数据进行增删改查。

# Database
本项目使用MySQL数据库，数据库名为questions_manag，在MySQL中创建同名数据库并将数据(questions_manag.sql)导入，web端和windows端数据互通，只需导入一次即可。

项目中数据库配置在src->sql->MySQLLink.java文件中，默认URL为localhost，默认端口为3306，默认账号密码都是root，请根据自身环境进行更改！

# Run
直接运行src/main/Main.java的main方法即可。

需要使用到的库都在lib中。

# Other
该项目是java swing项目，使用eclipse编写。

# License
License by [MIT License](https://github.com/black-six/QuestionsSystem-Windows/blob/main/LICENSE) 