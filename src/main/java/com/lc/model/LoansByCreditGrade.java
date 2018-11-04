package com.lc.model;

import java.util.ArrayList;
import java.util.HashMap;

public class LoansByCreditGrade {
    private HashMap<String, ArrayList<MonthValue>> gradeMap;

    public LoansByCreditGrade(HashMap<String, ArrayList<MonthValue>> gradeMap) {
	this.gradeMap = gradeMap;
    }

    public HashMap<String, ArrayList<MonthValue>> getGradeMap() {
	return gradeMap;
    }

    public void setGradeMap(HashMap<String, ArrayList<MonthValue>> gradeMap) {
	this.gradeMap = gradeMap;
    }

    @Override
    public String toString() {
	return "LoansByCreditGrade [gradeMap=" + gradeMap + "]";
    }

}
