package com.telefonica.prova.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class ProvaUtils {

	private ProvaUtils() {}
	
	public static int[][] vectorPreLoadMatrix(int M, int N) {		
		
		int[][] matrix = new int[M][N];
        
		Random random = new Random();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = random.nextInt(16);
            }
        }        
		return matrix;		
	}
	
	public static StringBuilder vectorFormatOutput(int key, HashMap<Integer, Integer> mapElements) {
		StringBuilder output = new StringBuilder();
		
		output.append(formatValue(key));
		
    	if (mapElements.containsKey(key)) {
            output.append(" => encontrado ").append(mapElements.get(key)).append(" vezes.\n");
        } else {
            output.append(" => não encontrado (0 vezes).\n");
        }
		
		return output;		
	}
	
	public static StringBuilder vectorFormatOutput(int vector, int count) {
		StringBuilder output = new StringBuilder();
		
		if (count>0) {
        	output.append(formatValue(vector)).append(" => encontrado ").append(count).append(" vezes.\n");
        } else {
        	output.append(formatValue(vector)).append(" => não encontrado (0).\n");
        }		
		return output;		
	}
	
	public static String formatValue(int value) {		
		return String.format("%02d", value);
	}
	
	public static String runningSumTime(String time1, String time2) {		
	    SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
	    Date d1 = null;
	    Date d2 = null;
		try {
			d1 = sdf.parse(time1);
			d2 = sdf.parse(time2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}	    
	    return sdf.format(new Date(d1.getTime() + d2.getTime()));
	}
	
	public static String runningAverageTime(String runningTime, int laps) {
		
		runningTime = runningTime.replace(".", ":");
		int min = 0;
		int sec = 0;
		int mil = 0;
		
		String[] t = runningTime.split(":");		
		min = Integer.parseInt(t[0])/laps;
		sec = Integer.parseInt(t[1])/laps;
		mil = Integer.parseInt(t[2])/laps;
		
		min = (min<0?0:min);
		sec = (sec<0?0:sec);
		mil = (mil<0?0:mil);
		
		return String.format("%02d", min) + ":" + String.format("%02d", sec) + "." + String.format("%-3s", mil).replace(" ", "0");
	}
	
	public static String runningFastestTime(String time1, String time2) {		
	    SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
	    Date faster = null;
	    
	    Date d1 = null;
	    Date d2 = null;	    
	    
		try {
			d1 = sdf.parse(time1);
			d2 = sdf.parse(time2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		faster = (d1.getTime() < d2.getTime())? d1:d2;
	    return sdf.format(new Date(faster.getTime()));
	}

}
