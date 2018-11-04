package com.lc.helper;

public enum LendingClubDBConfig {
    DRIVER_NAME("com.mysql.cj.jdbc.Driver");

    private String value;

    private LendingClubDBConfig(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}
