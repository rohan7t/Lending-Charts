package com.lc.handler;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.model.AggregatedTotals;
import com.lc.model.LoansByCreditGrade;
import com.lc.model.MonthlyLoanVolume;
import com.lc.model.YearAggregate;
import com.lc.repo.LendingClubRepository;

@Component("lendingClubHandler")
public class LendingClubHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LendingClubHandler.class);

    @Autowired
    LendingClubRepository lendingClubRepository;

    public ArrayList<Integer> fetchAllYears() throws SQLException {
	LOGGER.info("Entering LendingClubHandler.fetchAllYears()");
	ArrayList<Integer> years = lendingClubRepository.fetchAllYears();
	LOGGER.info("Exiting LendingClubHandler.fetchAllYears()");
	return years;
    }

    public YearAggregate fetchDataForYear(int year) throws SQLException {
	LOGGER.info("Entering LendingClubHandler.fetchDataForYear()");
	AggregatedTotals aggregatedTotals = fetchAggregatedTotals(year);
	LoansByCreditGrade loansByCreditGrade = fetchLoansByCreditGrade(year);
	MonthlyLoanVolume monthlyLoanVolume = fetchMonthlyLoanVolumes(year);
	YearAggregate yearAggregate = new YearAggregate(year, aggregatedTotals, loansByCreditGrade, monthlyLoanVolume);
	LOGGER.info("Exiting LendingClubHandler.fetchDataForYear()");
	return yearAggregate;
    }

    private AggregatedTotals fetchAggregatedTotals(int year) throws SQLException {
	return lendingClubRepository.fetchAggregatedTotals(year);
    }

    private LoansByCreditGrade fetchLoansByCreditGrade(int year) throws SQLException {
	return lendingClubRepository.fetchLoansByCreditGrade(year);
    }

    private MonthlyLoanVolume fetchMonthlyLoanVolumes(int year) throws SQLException {
	return lendingClubRepository.fetchMonthlyLoanVolumes(year);

    }

}
