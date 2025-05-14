import TP9.db.DatabaseManager;
import TP9.db.SQLServerManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SQLServerTest {

    private static DatabaseManager db;

    @BeforeAll
    public static void setup() throws Exception {
        db = new SQLServerManager("src/main/resources/db.properties");
        db.connect();
    }
    @AfterAll
    public static void teardown() throws Exception {
        db.disconnect();
    }

    @Test
    public void testSelectUserByNom() {
        List<Map<String, Object>> results = db.executeQuery("SELECT nom FROM utilisateurs");
        for (Map<String, Object> row : results) {
            System.out.println(row.get("nom"));
        }
    }

    @Test
    public void testInsertUser() throws Exception {
        String insertSQL = "INSERT INTO utilisateurs (id, nom, age) VALUES (88, 'Ismail', 20)";
        db.executeDML(insertSQL);
        System.out.println("insertion avec success");
    }

    @Test
    public void testUpdateUser() throws Exception {
        String updateSQL = "UPDATE utilisateurs SET nom = 'Ismail2' WHERE id = 88";
        db.executeDML(updateSQL);
        System.out.println("update avec success");
    }

    @Test
    public void testDeleteUser() throws Exception {
        String deleteSQL = "DELETE FROM utilisateurs WHERE id = 88";
        db.executeDML(deleteSQL);
        System.out.println("delete avec success");
    }

    @Test
    public void testInsertUserFromCSV() throws Exception {
        List<Map<String, String>> csvRows = CSVLoader.loadCSV("src/test/resources/test_data_sqlserver.csv");
        assertFalse(csvRows.isEmpty(), "Le fichier CSV est vide");

        boolean utilisateurTrouvee = false;

        for (Map<String, String> row : csvRows) {
            int id = Integer.parseInt(row.get("id"));
            String nom = row.get("nom");
            int age = Integer.parseInt(row.get("age"));

            List<Map<String, Object>> results = db.executeQuery("SELECT * FROM utilisateurs WHERE id = " + id);

            if (!results.isEmpty()) {
                Map<String, Object> user = results.get(0);
                if (user.get("nom").equals(nom) && Integer.parseInt(user.get("age").toString()) == age) {
                    utilisateurTrouvee = true;
                    System.out.println("l'utilisateur: '"+nom+"' est trouvee dans la base de donnee SQLServer");
                    break;
                }
            }
        }
        assertTrue(utilisateurTrouvee, "Aucun utilisateur du CSV n’a été trouvé dans la base de données.");
    }

}
