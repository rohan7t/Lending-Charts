package com.lc.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MonthValue implements Comparable<MonthValue> {
    private String month;
    private BigDecimal value;

    public MonthValue(String month, BigDecimal value) {
	this.month = getEquivalentMonthName(month);
	this.value = value;
    }

    private String getEquivalentMonthName(String monthNum) {
	switch (monthNum) {
	case "1":
	    return "Jan";
	case "2":
	    return "Feb";
	case "3":
	    return "Mar";
	case "4":
	    return "Apr";
	case "5":
	    return "May";
	case "6":
	    return "Jun";
	case "7":
	    return "Jul";
	case "8":
	    return "Aug";
	case "9":
	    return "Sept";
	case "10":
	    return "Oct";
	case "11":
	    return "Nov";
	case "12":
	    return "Dec";
	}
	return "";
    }

    public String getMonth() {
	return month;
    }

    public void setMonth(String month) {
	this.month = month;
    }

    public BigDecimal getValue() {
	return value;
    }

    public void setValue(BigDecimal value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return "MonthValue [month=" + month + ", value=" + value + "]";
    }

    @Override
    public int compareTo(MonthValue o) {
	try {
	    Date thisDate = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(this.getMonth());

	    Date d2 = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(o.getMonth());
	    return thisDate.compareTo(d2);
	} catch (ParseException e) {
	    e.printStackTrace();
	    return 0;
	}
    }

}
