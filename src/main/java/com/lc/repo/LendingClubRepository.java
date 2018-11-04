package com.lc.repo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.helper.LendingClubDBHelper;
import com.lc.model.AggregatedTotals;
import com.lc.model.LoansByCreditGrade;
import com.lc.model.MonthlyLoanVolume;

@Component("lendingClubRepository")
public class LendingClubRepository {

    @Autowired
    LendingClubDBHelper lendingClubDBHelper;

    private static final String FETCH_AGGREGATED_TOTALS_SQL = "SELECT SUM(l.`loan_amnt`) AS amount_applied, SUM(l.`funded_amnt`) AS amount_funded, SUM(l.`funded_amnt_inv`) AS amount_inv_comm FROM loan l WHERE YEAR(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d'))=?";
    private static final String FETCH_LOANS_BY_CREDIT_GRADE_SQL = "SELECT l.`grade` as grade, MONTH(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d')) as month_no, AVG(l.`loan_amnt`) as aver_amt FROM loan l WHERE YEAR(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d'))=? GROUP BY MONTH(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d')), l.`grade`;";
    private static final String FETCH_MONHTLY_LOAN_VOLUME_SQL = "SELECT MONTH(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d')) AS month_no, COUNT(*) AS loan_volume FROM loan l WHERE YEAR(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d'))=? GROUP BY MONTH(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d'));";
    private static final String FETCH_ALL_YEARS_SQL = "SELECT DISTINCT YEAR(STR_TO_DATE(CONCAT(l.`issue_d`, '-01'), '%b-%Y-%d')) AS year_value from loan l;";

    public AggregatedTotals fetchAggregatedTotals(int year) throws SQLException {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	BigDecimal amountApplied = new BigDecimal(0);
	BigDecimal amountFunded = new BigDecimal(0);
	BigDecimal amountCommitedByInvestors = new BigDecimal(0);
	try {
	    System.out.println("Connecting to database...");
	    con = lendingClubDBHelper.getConnection();
	    if (con == null) {
		throw new SQLException();
	    }
	    System.out.println("Creating statement...");
	    stmt = con.prepareStatement(FETCH_AGGREGATED_TOTALS_SQL);
	    stmt.setInt(1, year);
	    rs = stmt.executeQuery();

	    while (rs.next()) {
		// Retrieve by column name
		amountApplied = rs.getBigDecimal("amount_applied");
		amountFunded = rs.getBigDecimal("amount_funded");
		amountCommitedByInvestors = rs.getBigDecimal("amount_inv_comm");

		// Display values
		System.out.println("amountFunded: " + amountApplied);
		System.out.println("amountFunded: " + amountFunded);
		System.out.println("amountCommitedByInvestors: " + amountCommitedByInvestors);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw e;
	} finally {
	    try {
		lendingClubDBHelper.closeResultSet(rs);
		lendingClubDBHelper.closePreparedStatement(stmt);
		lendingClubDBHelper.closeConnection(con);
	    } catch (SQLException e) {
		e.printStackTrace();
		throw e;
	    }
	}
	return new AggregatedTotals(amountApplied, amountFunded, amountCommitedByInvestors);
    }

    public LoansByCreditGrade fetchLoansByCreditGrade(int year) throws SQLException {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	LoansByCreditGrade loansByCreditGrade = null;
	try {
	    System.out.println("Connecting to database...");
	    con = lendingClubDBHelper.getConnection();
	    if (con == null) {
		throw new SQLException();
	    }
	    System.out.println("Creating statement...");
	    stmt = con.prepareStatement(FETCH_LOANS_BY_CREDIT_GRADE_SQL);
	    stmt.setInt(1, year);
	    rs = stmt.executeQuery();

	    loansByCreditGrade = LendingClubRepositoryHelper.populateLoansByCreditGrade(rs);

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw e;
	} finally {
	    try {
		lendingClubDBHelper.closeResultSet(rs);
		lendingClubDBHelper.closePreparedStatement(stmt);
		lendingClubDBHelper.closeConnection(con);
	    } catch (SQLException e) {
		e.printStackTrace();
		throw e;
	    }
	}
	return loansByCreditGrade;
    }

    public MonthlyLoanVolume fetchMonthlyLoanVolumes(int year) throws SQLException {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	MonthlyLoanVolume monthlyLoanVolume = null;
	try {
	    System.out.println("Connecting to database...");
	    con = lendingClubDBHelper.getConnection();
	    if (con == null) {
		throw new SQLException();
	    }
	    System.out.println("Creating statement...");
	    stmt = con.prepareStatement(FETCH_MONHTLY_LOAN_VOLUME_SQL);
	    stmt.setInt(1, year);
	    rs = stmt.executeQuery();

	    monthlyLoanVolume = LendingClubRepositoryHelper.populateMonthlyLoanVolume(rs);

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw e;
	} finally {
	    try {
		lendingClubDBHelper.closeResultSet(rs);
		lendingClubDBHelper.closePreparedStatement(stmt);
		lendingClubDBHelper.closeConnection(con);
	    } catch (SQLException e) {
		e.printStackTrace();
		throw e;
	    }
	}
	return monthlyLoanVolume;
    }

    public ArrayList<Integer> fetchAllYears() throws SQLException {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	ArrayList<Integer> years = new ArrayList<Integer>();
	try {
	    System.out.println("Connecting to database...");
	    con = lendingClubDBHelper.getConnection();
	    if (con == null) {
		throw new SQLException();
	    }
	    System.out.println("Creating statement...");
	    stmt = con.createStatement();
	    rs = stmt.executeQuery(FETCH_ALL_YEARS_SQL);

	    years = LendingClubRepositoryHelper.populateAllYears(rs);

	} catch (SQLException e) {
	    e.printStackTrace();
	    throw e;
	} finally {
	    try {
		lendingClubDBHelper.closeResultSet(rs);
		lendingClubDBHelper.closeStatement(stmt);
		lendingClubDBHelper.closeConnection(con);
	    } catch (SQLException e) {
		e.printStackTrace();
		throw e;
	    }
	}
	return years;
    }

}
