package com.lc.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lc.handler.LendingClubHandler;
import com.lc.model.YearAggregate;

@RestController
public class LendingClubController {

    Logger logger = LoggerFactory.getLogger(LendingClubController.class);

    @Autowired
    LendingClubHandler lendingClubHandler;

    private static final String REQUESTED_YEAR = "year";

    @GetMapping(path = "/loadYears", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ArrayList<Integer> loadYears() {
	try {
	    logger.info("Calling handler");
	    ArrayList<Integer> response = lendingClubHandler.fetchAllYears();
	    logger.info("Recieved data from handler");
	    logger.info("RESPONSE data :" + response);
	    return response;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;

    }

    @GetMapping(path = "/fetchData/{" + REQUESTED_YEAR + "}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public YearAggregate fetchData(@PathVariable int year) {

	try {
	    logger.info("Calling handler");
	    YearAggregate response = lendingClubHandler.fetchDataForYear(year);
	    logger.info("Recieved data from handler");
	    logger.info("RESPONSE data :" + response);
	    return response;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	// TODO: Check what to be returned to the front end with Rohan
	return null;
    }

}
