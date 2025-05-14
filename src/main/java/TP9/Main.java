package TP9;

import TP9.db.DatabaseManager;
import TP9.db.MySQLManager;
import TP9.db.PostgreSQLManager;
import TP9.db.SQLServerManager;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager db = null;

        try {
            // Choix du SGBD
            System.out.println("=== Sélection du SGBD ===");
            System.out.println("1. MySQL");
            System.out.println("2. PostgreSQL");
            System.out.println("3. SQL Server");
            System.out.print("Votre choix : ");
            int choixSGBD = scanner.nextInt();
            scanner.nextLine(); // nettoyer le saut de ligne

            switch (choixSGBD) {
                case 1 -> db = new MySQLManager("src/main/resources/db.properties");
                case 2 -> db = new PostgreSQLManager("src/main/resources/db.properties");
                case 3 -> db = new SQLServerManager("src/main/resources/db.properties");
                default -> {
                    System.out.println("Choix invalide.");
                    return;
                }
            }

            db.connect();
            System.out.println("Connexion réussie.");

            // Choix du type de requête
            System.out.println("\n=== Type de requête ===");
            System.out.println("1. Requête SELECT");
            System.out.println("2. Requête INSERT / UPDATE / DELETE");
            System.out.print("Votre choix : ");
            int choixReq = scanner.nextInt();
            scanner.nextLine(); // nettoyer le saut de ligne

            System.out.print("Saisissez votre requête SQL : ");
            String sql = scanner.nextLine();

            if (choixReq == 1) {
                List<Map<String, Object>> results = db.executeQuery(sql);
                if (results.isEmpty()) {
                    System.out.println("Aucun résultat.");
                } else {
                    System.out.println("Résultats :");
                    for (Map<String, Object> row : results) {
                        for (Map.Entry<String, Object> entry : row.entrySet()) {
                            System.out.print(entry.getKey() + ": " + entry.getValue() + "  ");
                        }
                        System.out.println();
                    }
                }
            } else if (choixReq == 2) {
                int lignesAffectees = db.executeDML(sql);
                System.out.println("Requête exécutée. Lignes affectées : " + lignesAffectees);
            } else {
                System.out.println("Type de requête invalide.");
            }

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (db != null) db.disconnect();
                System.out.println("Déconnexion.");
            } catch (Exception e) {
                System.err.println("Erreur lors de la déconnexion.");
            }
        }
    }
}
