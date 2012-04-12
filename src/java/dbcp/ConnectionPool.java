/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/**
 *
 * @author Marco
 */
public class ConnectionPool implements ServletContextListener {
    private int pooledCount;
    private int totalUsedCon;
    private static DataSource datasource;

    /**
     * Returns the connection object.
     *
     * @return A <tt>Connection</tt> object.
     */
    public synchronized Connection getConnection() throws SQLException {
      pooledCount++;
      totalUsedCon++;
      return datasource.getConnection(); // Allocate and use a connection from the pool
  }
    
    public void freeConnection(Connection con) throws SQLException {
        pooledCount--;
        con.close();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Obtain our environment naming context
            Context envCtx = (Context) new InitialContext().lookup("java:comp/env");

            // Look up our data source
            datasource = (DataSource) envCtx.lookup("jdbc/TestDB");
        } catch (NamingException e) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Do nothing
    }
}
