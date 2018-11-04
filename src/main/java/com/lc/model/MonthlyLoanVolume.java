package com.lc.model;

import java.math.BigDecimal;
import java.util.HashMap;

public class MonthlyLoanVolume {
    private HashMap<Integer, BigDecimal> monthlyLoanVolumeMap;

    public MonthlyLoanVolume(HashMap<Integer, BigDecimal> monthlyLoanVolumeMap) {
	this.monthlyLoanVolumeMap = monthlyLoanVolumeMap;
    }

    public HashMap<Integer, BigDecimal> getMonthlyLoanVolumeMap() {
	return monthlyLoanVolumeMap;
    }

    public void setMonthlyLoanVolumeMap(HashMap<Integer, BigDecimal> monthlyLoanVolumeMap) {
	this.monthlyLoanVolumeMap = monthlyLoanVolumeMap;
    }

    @Override
    public String toString() {
	return "MonthlyLoanVolume [monthlyLoanVolumeMap=" + monthlyLoanVolumeMap + "]";
    }

}
