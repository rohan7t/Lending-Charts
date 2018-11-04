package com.lc.model;

import java.util.ArrayList;

public class MonthlyLoanVolume {
    private ArrayList<MonthValue> monthlyLoanVolumeArray;

    public MonthlyLoanVolume(ArrayList<MonthValue> monthlyLoanVolumeArray) {
	this.monthlyLoanVolumeArray = monthlyLoanVolumeArray;
    }

    public ArrayList<MonthValue> getMonthlyLoanVolumeArray() {
	return monthlyLoanVolumeArray;
    }

    public void setMonthlyLoanVolumeArray(ArrayList<MonthValue> monthlyLoanVolumeArray) {
	this.monthlyLoanVolumeArray = monthlyLoanVolumeArray;
    }

    @Override
    public String toString() {
	return "MonthlyLoanVolume [monthlyLoanVolumeArray=" + monthlyLoanVolumeArray + "]";
    }

}
