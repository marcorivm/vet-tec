/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcp;

import java.sql.*;

/**
 * This class should be initialized using ConnectionManager.init() 
 * @author Marco
 */
public class ConnectionManager {

   private static ConnectionPool pool;
    /**
     * Initializes the <tt>con</tt> object of type {@link Connection}
     */
    public static void init() {
        if(pool == null){
            pool = new ConnectionPool();
        }
    }


    /**
     * Method that executes an update on a single field it relies on {@link ConnectionManager#update(java.lang.String[][], java.lang.String, java.lang.String) }
     * @param field The field to update.
     * @param newVal The new value that would be assigned to the <tt>field</tt>.
     * @param table The table to be updated.
     * @param whereClause Should the update use a where clause, it should be in the format <b>ID = 1 AND SecondID = 2</b> and so on, regular SQL operations apply.
     * @return <b>True</b> if the update is successful.
     * @throws SQLException  
     */
    public static boolean update(String field, String newVal, String table, String whereClause) throws SQLException {
        String[][] fields = {{field, newVal}};
        return update(fields, table, whereClause);
    }

    /**
     * Method that executes an update on a group of fields.
     * @param fields String array which should contain the field and values to be updated as a key pair.
     * @param table The table to be updated.
     * @param whereClause Should the update use a where clause, it should be in the format <b>ID = 1 AND SecondID = 2</b> and so on, regular SQL operations apply.
     * @return <b>True</b> if the update is successful.
     * @throws SQLException  
     */
    public static boolean update(String[][] fields, String table, String whereClause) throws SQLException {
        init(); Connection con = pool.getConnection();
        String query = "UPDATE " + table + " SET " + fields[0][0] + "='" + fields[0][1] + "'";
        for (int i = 1; i < fields.length; i++) {
            query += ", " + fields[i][0] + "='" + fields[i][1] + "'";
        }
        if (!whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }
        Statement st = con.createStatement();
        return (st.executeUpdate(query) != 0);
    }
    
    /**
     * Method that executes an update on a group of fields.
     * @param fields String array which should contain the field and values to be updated as a key pair.
     * @param table The table to be updated.
     * @param whereClause Should the update use a where clause, it should be in the format <b>ID = 1 AND SecondID = 2</b> and so on, regular SQL operations apply.
     * @param con Connection used for Transaction support.
     * @return <b>True</b> if the update is successful.
     * @throws SQLException  
     */
    public static boolean update(String[][] fields, String table, String whereClause, Connection con) throws SQLException {
        String query = "UPDATE " + table + " SET " + fields[0][0] + "='" + fields[0][1] + "'";
        for (int i = 1; i < fields.length; i++) {
            query += ", " + fields[i][0] + "='" + fields[i][1] + "'";
        }
        if (!whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }
        Statement st = con.createStatement();
        return (st.executeUpdate(query) != 0);
    }

    /**
     * Method that selects all <tt>Rows</tt> in a specific table. Its equivalent to "<b>SELECT * FROM <tt>table</tt></b>".
     * @param table The table to be updated.
     * @return A <tt>ResultSet</tt> containing the result of the SELECT
     * @throws SQLException 
     */
    public static ResultSet selectAllRows(String table) throws SQLException {
        String[] fields = {"*"};
        return ConnectionManager.select(fields, table, "");
    }

    /**
     * Method that selects all <tt>Columns</tt> in a specific <tt>table</tt> according to a <tt>whereClause</tt>. Its equivalent to "<b>SELECT * FROM <tt>table</tt> WHERE <tt>whereClause</tt></b>".
     * @param table The table to be selected.
     * @param whereClause Should the SELECT use a where clause, it should be in the format <b>ID = 1 AND SecondID = 2</b> and so on, regular SQL operations apply.
     * @return A <tt>ResultSet</tt> containing the result of the SELECT
     * @throws SQLException 
     */
    public static ResultSet selectAllColumns(String table, String whereClause) throws SQLException {
        String[] fields = {"*"};
        return ConnectionManager.select(fields, table, whereClause);
    }

    /**
     * Method that selects and specific <tt>field</tt> on a <tt>table</tt> according to if present a <tt>whereClause</tt>.
     * @param field The field to be selected.
     * @param table The table to be selected.
     * @param whereClause Should the SELECT use a where clause, it should be in the format <b>ID = 1 AND SecondID = 2</b> and so on, regular SQL operations apply.
     * @return A <tt>ResultSet</tt> containing the result of the SELECT
     * @throws SQLException 
     */
    public static ResultSet select(String field, String table, String whereClause) throws SQLException {
        String[] fields = {field};
        return ConnectionManager.select(fields, table, whereClause);
    }

    /**
     * Method that selects and specific <tt>field</tt> on a <tt>table</tt> according to if present a <tt>whereClause</tt>.
     * @param fields <tt>String</tt> array which should contain the fields to be SELECTED.
     * @param table The table to be selected.
     * @param whereClause Should the SELECT use a where clause, it should be in the format <b>ID = 1 AND SecondID = 2</b> and so on, regular SQL operations apply.
     * @return A <tt>ResultSet</tt> containing the result of the SELECT
     * @throws SQLException 
     */
    public static ResultSet select(String[] fields, String table, String whereClause) throws SQLException {
        init(); Connection con = pool.getConnection();
        String query = "SELECT " + fields[0];
        for (int i = 1; i < fields.length; i++) {
            query += ", " + fields[i];
        }
        query += " FROM " + table;
        if (!whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }
        Statement st = con.createStatement();
        System.out.println(query);
        return st.executeQuery(query);
    }

    /**
     * Method that does a INSERT INTO <tt>table</tt> VALUES (<tt>values</tt>)
     * @param values Array of strings containing the values to be inserted.
     * @param table Table where the values should be inserted into.
     * @return <b>True</b> if successful.
     * @throws SQLException
     */
    public static boolean insert(String[] values, String table) throws SQLException {
        init(); Connection con = pool.getConnection();
        String query = "INSERT INTO " + table + " VALUES ('" + values[0] + "'";
        for (int i = 1; i < values.length; i++) {
            query += ", '" + values[i] + "'";
        }
        query += ")";
        Statement st = con.createStatement();
        return (st.executeUpdate(query) != 0);
    }
    /**
     * Method that does a INSERT INTO <tt>table</tt> VALUES (<tt>values</tt>)
     * and returns the new generated key for the values inserted.
     * @param fields Array of strings containing the key pairs {{COLUMN, VALUE},{COLUMN, VALUE}} to be inserted.
     * @param table Table where the values should be inserted into.
     * @return a ResultSet containing the generated keys.
     * @throws SQLException 
     */
    public static int insertAndGetKey(String[][] fields, String table) throws SQLException {
        init(); Connection con = pool.getConnection();
        String query = "INSERT INTO `" + table +"`";
        String columns = "(`" + fields[0][0]+"`";
        String values = " (" + fields[0][1] + "";
        for (int i = 1; i < fields.length; i++) {
            columns += ", `" + fields[i][0] +"`";
            values += "," + fields[i][1] + "";
        }
        columns += ")";
        values += ")";
        query += columns + " VALUES" + values;
        PreparedStatement st = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Method that does a INSERT INTO <tt>table</tt> VALUES (<tt>values</tt>)
     * @param fields Array of strings containing the key pairs {{COLUMN, VALUE},{COLUMN, VALUE}} to be inserted.
     * @param table Table where the values should be inserted into.
     * @return <b>True</b> if successful.
     * @throws SQLException
     */
    public static boolean insert(String[][] fields, String table) throws SQLException {
        init(); Connection con = pool.getConnection();
        String query = "INSERT INTO " + table+"";
        String columns = " (" + fields[0][0]+"";
        String values = " ('" + fields[0][1] + "'";
        for (int i = 1; i < fields.length; i++) {
            columns += ", " + fields[i][0];
            values += ", '" + fields[i][1] + "'";
        }
        columns += ")";
        values += ")";
        query += columns + " VALUES" + values;
        Statement st = con.createStatement();
        System.out.println(query);
        return (st.executeUpdate(query) != 0);
    }
    
    /**
     * Public method that deletes all rows from a given table 
     * @param table Table from where the rows will be deleted
     * @return <b>True</b> if delete successful.
     * @throws SQLException
     */
    public static boolean deleteAll(String table) throws SQLException {
        return delete(table, "");
    }

    /**
     * Public method that deletes rows which match a whereClause from a given table 
     * @param table Table from where the rows will be deleted
     * @param whereClause Where clause to be matched
     * @return <b>True</b> if delete successful.
     * @throws SQLException
     */
    public static boolean delete(String table, String whereClause) throws SQLException {
        init(); Connection con = pool.getConnection();
        String query = "DELETE FROM " + table;
        if (!whereClause.isEmpty()) {
            query += " WHERE " + whereClause;
        }
        Statement st = con.createStatement();
        return (st.executeUpdate(query) != 0);
    }
    
    /**
     * Method that initiates a Transaction and returns the open connection.
     * @return Connection
     * @throws SQLException 
     */
    public static Connection initTransaction() throws SQLException{
        init(); Connection con = pool.getConnection();
        con.setAutoCommit(false);
        return con;
    }
    
    /**
     * Method that makes a commit on the connection
     * @param con
     * @throws SQLException 
     */
    public static void commit(Connection con) throws SQLException{
        con.commit();
        con.setAutoCommit(true);
    }
    
    /**
     * Method that makes a rollback using the received connection.
     * @param con
     * @throws SQLException 
     */
    public static void rollback(Connection con) throws SQLException{
        con.rollback();
        con.setAutoCommit(true);
    }
    
    /**
     * Method that does a INSERT INTO <tt>table</tt> VALUES (<tt>values</tt>)
     * This method is to be used with transactions.
     * @param fields Array of strings containing the key pairs {{COLUMN, VALUE},{COLUMN, VALUE}} to be inserted.
     * @param table Table where the values should be inserted into.
     * @param con Connection to use.
     * @return <b>True</b> if successful.
     * @throws SQLException
     */
    public static boolean insert(String[][] fields, String table, Connection con) throws SQLException {
        String query = "INSERT INTO " + table+"";
        String columns = " (" + fields[0][0]+"";
        String values = " ('" + fields[0][1] + "'";
        for (int i = 1; i < fields.length; i++) {
            columns += ", " + fields[i][0];
            values += ", '" + fields[i][1] + "'";
        }
        columns += ")";
        values += ")";
        query += columns + " VALUES" + values;
        Statement st = con.createStatement();
        System.out.println(query);
        return (st.executeUpdate(query) != 0);
    }
  
    /**
     * Method that does a INSERT INTO <tt>table</tt> VALUES (<tt>values</tt>)
     * and returns the new generated key for the values inserted.
     * This method is to be used with transactions.
     * @param fields Array of strings containing the key pairs {{COLUMN, VALUE},{COLUMN, VALUE}} to be inserted.
     * @param table Table where the values should be inserted into.
     * @param con Connection to use.
     * @return a ResultSet containing the generated keys.
     * @throws SQLException 
     */
    public static int insertAndGetKey(String[][] fields, String table, Connection con) throws SQLException {
        String query = "INSERT INTO `" + table +"`";
        String columns = "(`" + fields[0][0]+"`";
        String values = " (" + fields[0][1] + "";
        for (int i = 1; i < fields.length; i++) {
            columns += ", `" + fields[i][0] +"`";
            values += "," + fields[i][1] + "";
        }
        columns += ")";
        values += ")";
        query += columns + " VALUES" + values;
        PreparedStatement st = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
}
