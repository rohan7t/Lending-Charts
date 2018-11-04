package com.lc.repo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.lc.model.LoansByCreditGrade;
import com.lc.model.MonthValue;
import com.lc.model.MonthlyLoanVolume;

public class LendingClubRepositoryHelper {

    public static LoansByCreditGrade populateLoansByCreditGrade(ResultSet rs) throws SQLException {
	HashMap<String, HashMap<Integer, BigDecimal>> gradeMap = new HashMap<String, HashMap<Integer, BigDecimal>>();
	while (rs.next()) {
	    String grade = rs.getString("grade");
	    Integer month = rs.getInt("month_no");
	    BigDecimal averageAmount = rs.getBigDecimal("aver_amt");
	    if (gradeMap.containsKey(grade)) {
		gradeMap.get(grade).put(month, averageAmount);
	    } else {
		HashMap<Integer, BigDecimal> innerMap = new HashMap<Integer, BigDecimal>();
		innerMap.put(month, averageAmount);
		gradeMap.put(grade, innerMap);
	    }
	}
	return new LoansByCreditGrade(gradeMap);
    }

    public static MonthlyLoanVolume populateMonthlyLoanVolume(ResultSet rs) throws SQLException {
	ArrayList<MonthValue> monthlyLoanVolumeArray = new ArrayList<MonthValue>();
	while (rs.next()) {
	    String month = rs.getString("month_no");
	    BigDecimal averageAmount = rs.getBigDecimal("loan_volume");
	    monthlyLoanVolumeArray.add(new MonthValue(month, averageAmount));
	}
	return new MonthlyLoanVolume(monthlyLoanVolumeArray);
    }

    public static ArrayList<Integer> populateAllYears(ResultSet rs) throws SQLException {
	ArrayList<Integer> years = new ArrayList<Integer>();
	while (rs.next()) {
	    years.add(rs.getInt("year_value"));
	}
	return years;
    }
}
