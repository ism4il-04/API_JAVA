# API Java pour la Gestion Unifiée des SGBDR

## Description

Ce projet consiste à développer une API Java packagée en fichier `.jar`. Cette API offre une interface unifiée pour :
- La connexion à plusieurs types de systèmes de gestion de base de données relationnelles (SGBDR), tels que MySQL, PostgreSQL, et SQL Server.
- L'exécution de requêtes SQL.
- La gestion des résultats des requêtes.

L'objectif est de simplifier l'interaction avec différents SGBDR en fournissant une abstraction commune.

## Fonctionnalités

- **Connexion unifiée** : Connectez-vous facilement à différents SGBDR en utilisant une interface unique.
- **Exécution de requêtes** : Exécutez des requêtes SQL (SELECT, INSERT, UPDATE, DELETE) de manière simplifiée.
- **Gestion des résultats** : Récupérez et manipulez les résultats des requêtes avec des outils intégrés.
- **Support multi-SGBDR** : Compatible avec MySQL, PostgreSQL, et SQL Server.

## Structure du Projet

- **API_JAVA** : Contient le code source de l'API.
- **Tests** : Inclut des tests unitaires pour valider les fonctionnalités.
- **Documentation** : Fournit des exemples d'utilisation et des guides.

## Prérequis

- **Java** : Version 8 ou supérieure.

## Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/ism4il-04/API_JAVA
   ```
2. Compilez et construisez le fichier `.jar` :
   ```bash
   mvn clean package
   ```
3. Ajoutez le fichier `.jar` généré à votre projet Java.

## Utilisation

### Exemple de Connexion

```java
import TP9.db.ConnectException;
import TP9.db.MySQLManager;
import TP9.db.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new MySQLManager("src/main/resources/db.properties");
        try {
            dbManager.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        // Exécutez des requêtes et gérez les résultats
    }
}
```

### Exécution de Requêtes

```java
import TP9.db.DQLException;
import TP9.db.MySQLManager;

public static void main(String[] args) {
    String query = "SELECT * FROM users";
    MySQLManager db = new MySQLManager("src/main/resources/db.properties");
    try {
        db.executeQuery("select * from utilisateurs");
    } catch (DQLException e) {
        e.printStackTrace();
    }
}

```
