package com.lc.handler;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lc.model.AggregatedTotals;
import com.lc.model.LoansByCreditGrade;
import com.lc.model.MonthlyLoanVolume;
import com.lc.model.YearAggregate;
import com.lc.repo.LendingClubRepository;

@Component("lendingClubHandler")
public class LendingClubHandler {

    @Autowired
    LendingClubRepository lendingClubRepository;

    public ArrayList<Integer> fetchAllYears() throws SQLException {
	ArrayList<Integer> years = lendingClubRepository.fetchAllYears();
	return years;
    }

    public YearAggregate fetchDataForYear(int year) throws SQLException {
	AggregatedTotals aggregatedTotals = fetchAggregatedTotals(year);
	LoansByCreditGrade loansByCreditGrade = fetchLoansByCreditGrade(year);
	MonthlyLoanVolume monthlyLoanVolume = fetchMonthlyLoanVolumes(year);
	YearAggregate yearAggregate = new YearAggregate(year, aggregatedTotals, loansByCreditGrade, monthlyLoanVolume);
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
