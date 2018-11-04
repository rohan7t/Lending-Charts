package com.lc.repo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lc.model.LoansByCreditGrade;
import com.lc.model.MonthValue;
import com.lc.model.MonthlyLoanVolume;

public class LendingClubRepositoryHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(LendingClubRepositoryHelper.class);

    public static LoansByCreditGrade populateLoansByCreditGrade(ResultSet rs) throws SQLException {
	LOGGER.info("Entering LendingClubRepositoryHelper.populateLoansByCreditGrade()");
	HashMap<String, ArrayList<MonthValue>> gradeMap = new HashMap<String, ArrayList<MonthValue>>();
	while (rs.next()) {
	    String grade = rs.getString("grade");
	    String month = rs.getString("month_no");
	    BigDecimal averageAmount = rs.getBigDecimal("aver_amt");
	    if (gradeMap.containsKey(grade)) {
		gradeMap.get(grade).add(new MonthValue(month, averageAmount));
	    } else {
		ArrayList<MonthValue> monthAverageValues = new ArrayList<MonthValue>();
		monthAverageValues.add(new MonthValue(month, averageAmount));
		gradeMap.put(grade, monthAverageValues);
	    }
	}

	for (Entry<String, ArrayList<MonthValue>> entry : gradeMap.entrySet()) {
	    Collections.sort(entry.getValue());
	}
	LOGGER.info("Exiting LendingClubRepositoryHelper.populateLoansByCreditGrade()");
	return new LoansByCreditGrade(gradeMap);
    }

    public static MonthlyLoanVolume populateMonthlyLoanVolume(ResultSet rs) throws SQLException {
	LOGGER.info("Entering LendingClubRepositoryHelper.populateMonthlyLoanVolume()");
	ArrayList<MonthValue> monthlyLoanVolumeArray = new ArrayList<MonthValue>();
	while (rs.next()) {
	    String month = rs.getString("month_no");
	    BigDecimal averageAmount = rs.getBigDecimal("loan_volume");
	    monthlyLoanVolumeArray.add(new MonthValue(month, averageAmount));
	}
	Collections.sort(monthlyLoanVolumeArray);
	LOGGER.info("Exiting LendingClubRepositoryHelper.populateMonthlyLoanVolume()");
	return new MonthlyLoanVolume(monthlyLoanVolumeArray);
    }

    public static ArrayList<Integer> populateAllYears(ResultSet rs) throws SQLException {
	LOGGER.info("Entering LendingClubRepositoryHelper.populateAllYears()");
	ArrayList<Integer> years = new ArrayList<Integer>();
	while (rs.next()) {
	    years.add(rs.getInt("year_value"));
	}
	LOGGER.info("Exiting LendingClubRepositoryHelper.populateAllYears()");
	return years;
    }
}
