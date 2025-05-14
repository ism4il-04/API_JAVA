package TP9.db;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

public interface DatabaseManager {
    Statement connect() throws ConnectException;
    void disconnect() throws ConnectException;
    List<Map<String, Object>> executeQuery(String sql) throws DQLException;
    int executeDML(String sql) throws DMLException;
}
