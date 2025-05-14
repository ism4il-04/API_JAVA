import TP9.db.DatabaseManager;
import TP9.db.PostgreSQLManager;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PostgreSQLTest {

    private static DatabaseManager db;

    @BeforeAll
    public static void setup() throws Exception {
        db = new PostgreSQLManager("src/main/resources/db.properties");
        db.connect();
        System.out.println("connection avec success");
    }
    @AfterAll
    public static void teardown() throws Exception {
        db.disconnect();
        System.out.println("deconnection avec success");
    }

    @Test
    public void testSelectUserByNom() {
        List<Map<String, Object>> results = db.executeQuery("SELECT nom FROM utilisateurs");
        System.out.println("liste des noms des utilisateurs : ");
        for (Map<String, Object> row : results) {
            System.out.println(row.get("nom"));
        }
    }

    @Test
    public void testInsertUser() throws Exception {
        String insertSQL = "INSERT INTO utilisateurs (id, nom, age) VALUES (20, 'Yassir', 28)";
        db.executeDML(insertSQL);
        System.out.println("insertion avec success");
    }

    @Test
    public void testUpdateUser() throws Exception {
        String updateSQL = "UPDATE utilisateurs SET nom = 'Yassir2' WHERE id = 20";
        db.executeDML(updateSQL);
        System.out.println("update avec success");
    }
    @Test
    public void testDeleteUser() throws Exception {
        String deleteSQL = "DELETE FROM utilisateurs WHERE id = 20";
        db.executeDML(deleteSQL);
        System.out.println("delete avec success");
    }

    @Test
    public void testInsertUserFromCSV() throws Exception {
        List<Map<String, String>> csvRows = CSVLoader.loadCSV("src/test/resources/test_data_postgres.csv");
        assertFalse(csvRows.isEmpty(), "Le fichier CSV est vide");
        System.out.println("Le fichier CSV contient " + csvRows.size() + " lignes");

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
                    System.out.println("l'utilisateur :'"+nom+"' est trouvee dans la base de de donnee PostgresSQL");
                    break;
                }
            }
        }
        assertTrue(utilisateurTrouvee, "Aucun utilisateur du CSV n’a été trouvé dans la base de données.");
    }

}
