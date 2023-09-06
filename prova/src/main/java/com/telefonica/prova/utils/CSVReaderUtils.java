package com.telefonica.prova.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVReaderUtils {

	public static List<String[]> csvReader() {
	    String csvFile = ".\\running.csv";
	    String line = "";
	    String csvSplitBy = ";";

	    List<String[]> runningList = new ArrayList<>();
	
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        
	    	line = br.readLine();  // step the header
	    	while ((line = br.readLine()) != null) {
	    		String[] row = line.split(csvSplitBy);
                runningList.add(row);
	    	}
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return runningList;
	}

}
