package db;

import util.DBConfigLoader;
import lombok.Data;

import java.sql.*;
import java.util.*;


@Data
public class SQLServerManager implements DatabaseManager{
    Properties props = DBConfigLoader.chargeDBConfig(configPath, "sqlserver");
    Connection connection;
    Statement stm;
    String url = props.getProperty("url");
    String username = props.getProperty("user");
    String password = props.getProperty("password");



    @Override
    public Statement connect() throws ConnectException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection(url, username, password);
            stm = connection.createStatement();
            return stm;
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectException("Erreur de connexion");
        }
    }

    @Override
    public void disconnect() throws ConnectException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ConnectException("Erreur de connexion");
        }
    }

    @Override
    public List<Map<String, Object>> executeQuery(String sql) throws DQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e){
            throw new DQLException("Erreur de selection");
        }
        return results;
    }

    @Override
    public int executeDML(String sql) throws DMLException {
        try(Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new DMLException("Erreur DML");
        }
    }

}
