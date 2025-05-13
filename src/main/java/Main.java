import db.SQLServerManager;

public class Main {
    public static void main(String[] args) {
        try {
            SQLServerManager mysql = new SQLServerManager();
            String sql = "insert into utilisateurs values (15, 'ahmed', 20)";
            mysql.executeDML(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
