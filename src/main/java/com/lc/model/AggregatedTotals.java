package com.lc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AggregatedTotals {

    private BigDecimal amountApplied;
    private BigDecimal amountFunded;
    private BigDecimal amountCommitedByInvestors;

    public AggregatedTotals(BigDecimal amountApplied, BigDecimal amountFunded, BigDecimal amountCommitedByInvestors) {
	this.amountApplied = amountApplied;
	this.amountFunded = amountFunded;
	this.amountCommitedByInvestors = amountCommitedByInvestors;
    }

    public BigDecimal getAmountApplied() {
	return amountApplied.setScale(0, RoundingMode.HALF_UP);
    }

    public void setAmountApplied(BigDecimal amountApplied) {
	this.amountApplied = amountApplied;
    }

    public BigDecimal getAmountFunded() {
	return amountFunded.setScale(0, RoundingMode.HALF_UP);
    }

    public void setAmountFunded(BigDecimal amountFunded) {
	this.amountFunded = amountFunded;
    }

    public BigDecimal getAmountCommitedByInvestors() {
	return amountCommitedByInvestors.setScale(0, RoundingMode.HALF_UP);
    }

    public void setAmountCommitedByInvestors(BigDecimal amountCommitedByInvestors) {
	this.amountCommitedByInvestors = amountCommitedByInvestors;
    }

    @Override
    public String toString() {
	return "AggregatedTotals [amountApplied=" + amountApplied + ", amountFunded=" + amountFunded
		+ ", amountCommitedByInvestors=" + amountCommitedByInvestors + "]";
    }

}
