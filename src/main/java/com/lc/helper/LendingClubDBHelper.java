package com.lc.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LendingClubDBHelper {

    static {
	try {
	    Class.forName(LendingClubDBConfig.DRIVER_NAME.getValue());
	} catch (ClassNotFoundException e) {
	    System.out.println("Driver class not found");
	}
    }

    public static Connection getConnection() throws SQLException {
	return DriverManager.getConnection(LendingClubDBConfig.URL.getValue(), LendingClubDBConfig.USERNAME.getValue(),
		LendingClubDBConfig.PASSWORD.getValue());
    }

    public static void closeConnection(Connection con) throws SQLException {
	if (con != null) {
	    con.close();
	}
    }

    public static void closePreparedStatement(PreparedStatement stmt) throws SQLException {
	if (stmt != null) {
	    stmt.close();
	}
    }

    public static void closeStatement(Statement stmt) throws SQLException {
	if (stmt != null) {
	    stmt.close();
	}
    }

    public static void closeResultSet(ResultSet rs) throws SQLException {
	if (rs != null) {
	    rs.close();
	}
    }
}
