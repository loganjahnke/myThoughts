package edu.uga.cs.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DbAccessInterface
 * @author Logan Jahnke
 * @created March 18, 2017
 */
public class DbAccessInterface {

	/**
	 * Connects the myThoughts Web App to the myThoughts database
	 * @return the Connection object
	 */
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName(DbAccessConfig.DRIVE_NAME);
            con = DriverManager.getConnection(DbAccessConfig.CONNECTION_URL,
            								  DbAccessConfig.DB_CONNECTION_USERNAME,
            								  DbAccessConfig.DB_CONNECTION_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * Used to execute a SELECT sql statement
     * @param con - the connection object
     * @param query - the SELECT query
     * @return the ResultSet with the results of the query
     */
    public static ResultSet retrieve(Connection con, String query) {
        ResultSet rset = null;
        try {
            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            return rset;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rset;
    }

    /**
     * Closes the connection object
     * @param con - the connection object to close
     */
    public static void disconnect(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to execute an INSERT sql statement
     * @param con - the connection object
     * @param query - the INSERT query
     * @return the ID of the inserted object
     */
	public static int insert(Connection con, String query) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            if(rs.next())
                return rs.getInt(1);
        }
        catch (Exception e) {
            System.err.println("ERROR: cannot insert into database.");
            System.err.println(e.getMessage());
        }
        return -1;
	}

	/**
	 * Used to execute a DELETE sql statement
	 * @param con - the connection object
	 * @param query - the DELETE query
	 */
	public static void delete(Connection con, String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("ERROR: cannot be delete from database.");
            System.err.println(e.getMessage());
        }
	}

}
