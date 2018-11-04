package com.lc.model;

import java.math.BigDecimal;
import java.util.HashMap;

public class LoansByCreditGrade {
    private HashMap<String, HashMap<Integer, BigDecimal>> gradeMap;

    public LoansByCreditGrade(HashMap<String, HashMap<Integer, BigDecimal>> gradeMap) {
	this.gradeMap = gradeMap;
    }

    public HashMap<String, HashMap<Integer, BigDecimal>> getGradeMap() {
	return gradeMap;
    }

    public void setGradeMap(HashMap<String, HashMap<Integer, BigDecimal>> gradeMap) {
	this.gradeMap = gradeMap;
    }

    @Override
    public String toString() {
	return "LoansByCreditGrade [gradeMap=" + gradeMap + "]";
    }

}
