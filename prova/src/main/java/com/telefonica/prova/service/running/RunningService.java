package com.telefonica.prova.service.running;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.telefonica.prova.utils.CSVReaderUtils;
import com.telefonica.prova.utils.ProvaUtils;

@Service
public class RunningService {
	
	
	public String finishLine() {		
		return ranking(CSVReaderUtils.csvReader());
	}
	
	public String ranking(List<String[]> runningList) {
        
		String output = "";
		int laps = 4;
		String csvSplitBy = ";";

	    
        // sort by super-hero
        Collections.sort(runningList, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[1];
                final String time2 = entry2[1];
                return time1.compareTo(time2);
            }
        });

        List<String[]> rankingRow = new ArrayList<>();
        List<String> notFinisher = new ArrayList<>();
        
        String hero = "";        
        int voltas = 0;
        String totalTime = "00:00.000";
        String[] fastestLap = {"", "00:00.000"};
        String fastestLapRunner = "00:00.000";
        
        int countRow = 0;
        for(String[] s : runningList) {
        	countRow++;
        	
        	if (hero.isEmpty()) {
        		hero = s[1];
        		voltas = 0;
        		
        		fastestLap[0] = s[1];
        		fastestLap[1] = s[3];
        		fastestLapRunner = s[3];
        	}
        	
        	if (hero.equals(s[1])) {
        		voltas++;        		
        		totalTime = ProvaUtils.runningSumTime(totalTime, s[3]);
        		
        		fastestLapRunner = ProvaUtils.runningFastestTime(fastestLapRunner, s[3]);
        		String faster = ProvaUtils.runningFastestTime(fastestLap[1], s[3]);
        		
        		if (!faster.equals(fastestLap[1])) {
            		fastestLap[0] = s[1];
            		fastestLap[1] = faster;            		
        		}        		
        	}        	
        	
        	if (!hero.equals(s[1])) {
        		String row = hero + csvSplitBy + voltas + csvSplitBy + totalTime + csvSplitBy + fastestLapRunner;
        		rankingRow.add(row.split(csvSplitBy));        				
        		
        		hero = s[1];
        		voltas = 1;
        		totalTime = "00:00.000";
        		totalTime = ProvaUtils.runningSumTime(totalTime, s[3]);
        		fastestLapRunner = s[3];
        		
        		String faster = ProvaUtils.runningFastestTime(fastestLap[1], s[3]);        		
        		if (!faster.equals(fastestLap[1])) {
            		fastestLap[0] = s[1];
            		fastestLap[1] = faster;        			
        		}
        	}
        	
        	// end of runningList, add the last runner to the ranking
        	if (countRow == runningList.size()) {
        		String row = hero + csvSplitBy + voltas + csvSplitBy + totalTime + csvSplitBy + fastestLapRunner;
        		rankingRow.add(row.split(csvSplitBy));
        	}
        }

        // remove not finishers
        for (int i =0; i< rankingRow.size(); i++) {        	
        	if (!rankingRow.get(i)[1].equals(laps+"")) {
        		notFinisher.add(rankingRow.get(i)[0]);
        		rankingRow.remove(i);
        	}
        }        
        
        // sort by total time
        Collections.sort(rankingRow, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[2];
                final String time2 = entry2[2];
                return time1.compareTo(time2);
            }
        });
        
        output += "--- Pódio\n";
        int place = 1;
        for(String[] r : rankingRow) {
        	output += place++ + "º >> " + r[0] + ", Voltas: " + r[1] + ", Tempo de Prova: " + r[2] + ", Melhor Volta: " + r[3] + ", Velocidade Média: " + ProvaUtils.runningAverageTime(r[2], laps) + "\n";
        }        
        
        output += "\n> Melhor Volta da Prova: " + fastestLap[0] + " em " + fastestLap[1] + "\n";
       	
       	for(String f : notFinisher) {
       		output += "> " + f + " não terminou a prova\n";
       	}
       	
       	return output;
	}

	
}
