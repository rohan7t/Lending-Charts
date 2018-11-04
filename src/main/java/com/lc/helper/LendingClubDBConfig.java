package com.lc.helper;

public enum LendingClubDBConfig {
    DRIVER_NAME("com.mysql.cj.jdbc.Driver"),
    URL("jdbc:mysql://rds-mysql.cqu7arbrruen.us-east-1.rds.amazonaws.com:3306/lclub"), USERNAME("lcadmin"),
    PASSWORD("lendingcharts");

    private String value;

    private LendingClubDBConfig(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}
