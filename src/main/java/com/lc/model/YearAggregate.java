package com.lc.model;

public class YearAggregate {

    private int year;
    private AggregatedTotals aggregateTotals;
    private LoansByCreditGrade loansByCreditGrade;
    private MonthlyLoanVolume monthlyLoanVolume;

    public YearAggregate(int year, AggregatedTotals aggregateTotals, LoansByCreditGrade loansByCreditGrade,
	    MonthlyLoanVolume monthlyLoanVolume) {
	this.year = year;
	this.aggregateTotals = aggregateTotals;
	this.loansByCreditGrade = loansByCreditGrade;
	this.monthlyLoanVolume = monthlyLoanVolume;
    }

    public MonthlyLoanVolume getMonthlyLoanVolume() {
	return monthlyLoanVolume;
    }

    public void setMonthlyLoanVolume(MonthlyLoanVolume monthlyLoanVolume) {
	this.monthlyLoanVolume = monthlyLoanVolume;
    }

    public LoansByCreditGrade getLoansByCreditGrade() {
	return loansByCreditGrade;
    }

    public void setLoansByCreditGrade(LoansByCreditGrade loansByCreditGrade) {
	this.loansByCreditGrade = loansByCreditGrade;
    }

    public AggregatedTotals getAggregateTotals() {
	return aggregateTotals;
    }

    public void setAggregateTotals(AggregatedTotals aggregateTotals) {
	this.aggregateTotals = aggregateTotals;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    @Override
    public String toString() {
	return "YearAggregate [year=" + year + ", aggregateTotals=" + aggregateTotals + ", loansByCreditGrade="
		+ loansByCreditGrade + ", monthlyLoanVolume=" + monthlyLoanVolume + "]";
    }

}
