package com.lc.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("lendingClubDBHelper")
public class LendingClubDBHelper {

    @Value("${db.connection.password}")
    private String password;

    @Value("${db.connection.username}")
    private String username;

    @Value("${db.connection.url}")
    private String url;

    static {
	try {
	    Class.forName(LendingClubDBConfig.DRIVER_NAME.getValue());
	} catch (ClassNotFoundException e) {
	    System.out.println("Driver class not found");
	}
    }

    public Connection getConnection() throws SQLException {
	return DriverManager.getConnection(url, username, password);
    }

    public void closeConnection(Connection con) throws SQLException {
	if (con != null) {
	    con.close();
	}
    }

    public void closePreparedStatement(PreparedStatement stmt) throws SQLException {
	if (stmt != null) {
	    stmt.close();
	}
    }

    public void closeStatement(Statement stmt) throws SQLException {
	if (stmt != null) {
	    stmt.close();
	}
    }

    public void closeResultSet(ResultSet rs) throws SQLException {
	if (rs != null) {
	    rs.close();
	}
    }
}
