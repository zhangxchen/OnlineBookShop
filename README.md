# OnlineBookShop
## instrunctions
1. environments
'''
- IntelliJ IDEA Ultimate
- apache-tomcat-8.0.50
- MySQL 8.0
- java version "1.8.0_152" java(TM) SE Runtime Environment
'''
2. system configuration
'''
- Databese information and administrator account password settings：/src/jdbc_default.properties
- username：database username
- password：database password
'''
3. method of application
'''
1. Create table(book, orders, cart, user) in mysql, set the code to UTF-8 and the port to 3306.
2. Import the sql file into the database and execute.
3. Import the project into local Idea and change coding method in settings to UTF-8.
4. Add the local database user information to the profile information(jdbc_default.properties).
5. Deploy Tomcat server to run the project.
'''
