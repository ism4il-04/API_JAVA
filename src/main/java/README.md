Présentation du projet
Ce projet a pour objectif de concevoir une API Java modulaire permettant d'interagir avec différents systèmes de gestion de bases de données relationnelles (SGBDR) tels que MySQL, PostgreSQL et SQL Server. Il intègre une architecture orientée objet avec des interfaces génériques, la gestion propre des exceptions SQL, ainsi que des tests unitaires rigoureux basés sur des jeux de données au format CSV.

Ce travail s'inscrit dans un cadre académique, dans le but de mettre en pratique les principes avancés du développement Java et de l'ingénierie logicielle.

Objectifs pédagogiques
Implémenter une architecture modulaire et réutilisable en Java.

Utiliser les concepts d’interfaces, de polymorphisme et de généricité.

Assurer la gestion d’erreurs de manière propre avec des messages explicites.

Manipuler des bases de données avec JDBC.

Automatiser les tests avec JUnit 5.

Valider les données via des fichiers CSV.

Fonctionnalités principales
Connexion et déconnexion à un SGBD via une interface DatabaseManager

Exécution de requêtes DQL (SELECT) et DML (INSERT, UPDATE, DELETE)

Chargement de fichiers .csv pour tests comparatifs

Gestion automatique des ressources (try-with-resources)

Tests unitaires des opérations de base sur la table utilisateurs

Structure du projet
TP9/
├── src/
│   ├── main/java/TP9/database/
│   │   ├── DatabaseManager.java
│   │   ├── MySQLManager.java
│   │   ├── PostgreSQLManager.java
│   │   ├── SQLServerManager.java
│   │   ├── DBConfigLoader.java
│   │   ├── DQLException.java
│   │   └── DMLException.java
│   └── test/java/
│       ├── MySQLTest.java
│       └── PostgreSQLTest.java
│       └── SQLServerTest.java
│       └── CSVLoader.java
│   └── test/resources/
│       ├── test_data_postgreSql.csv
│       ├── test_data_sqlserver.csv
│       └── test_data_mysql.csv
