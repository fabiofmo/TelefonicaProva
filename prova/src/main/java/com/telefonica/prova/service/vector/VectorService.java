package com.telefonica.prova.service.vector;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.telefonica.prova.utils.ProvaUtils;

@Service
public class VectorService {

	// vector count of the matrix passed as a parameter with a plain text output.
	public String vectorCount(int[] vector) {		
		StringBuilder output = new StringBuilder();
		HashMap<Integer, Integer> mapElements = new HashMap<Integer, Integer>();        
        
		output.append("Matriz recebida:\n");	    
	    for (int i = 0; i < vector.length; i++) {
	    	output.append(ProvaUtils.formatValue(vector[i])).append(" ");
	    }		
		
        for (int i = 0; i < vector.length; i++) {
            int element = vector[i];
            boolean found = (mapElements.containsKey(element));
            
            mapElements.put(element, (found?mapElements.get(element) + 1: 1));
        }

        output.append("\n\nContagem:\n");
        for (int key = 0; key < 16; key++) {
        	output.append(ProvaUtils.vectorFormatOutput(key, mapElements));
        }
        return output.toString();	
	}	

	// vector count of the matrix passed as a parameter inside a randomly preloaded matrix with a plain text output.
	// (developed 'just in case')
	public String vectorCountPreLoaded(int[] vector) {	    
        int M = 5;
        int N = 15;

        StringBuilder output = new StringBuilder();
        output.append("Valores pré-carregados da Matriz (randômicos):\n");
        
        int[][] matrix = ProvaUtils.vectorPreLoadMatrix(M, N);
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                output.append(ProvaUtils.formatValue(matrix[i][j])).append(" ");                
            }
            output.append("\n");
        }
        
	    output.append("\nValores a contar:\n");	    
	    for (int i = 0; i < vector.length; i++) {
	    	output.append(ProvaUtils.formatValue(vector[i])).append(" ");
	    }
	    
	    output.append("\n\nContagem:\n");
        for (int i = 0; i < vector.length; i++) {        	
        	int count = 0;
        	
	        for (int j = 0; j < matrix.length; j++) {

	            for (int k = 0; k < matrix[j].length; k++) {
	                if (matrix[j][k] == vector[i]) {
	                    count++;
	                }
	            }
	        }
	        output.append(ProvaUtils.vectorFormatOutput(vector[i], count));
	    }
	    return output.toString();
	}

	// vector count of the matrix passed as a parameter with JSON output.
	public HashMap<Integer, String> vectorCountJSON(int[] vector) {		
		String elementMsg = "";
		
		HashMap<Integer, Integer> mapElements = new HashMap<Integer, Integer>();
		HashMap<Integer, String> mapOutput = new HashMap<Integer, String>();
		
		for (int i = 0; i < vector.length; i++) {
			int element = vector[i];
            boolean found = (mapElements.containsKey(element));
            
            mapElements.put(element, (found?mapElements.get(element) + 1: 1));
        }

        for (int key = 0; key < 16; key++) {        	
        	boolean found = (mapElements.containsKey(key));
        	int element = mapElements.get(key)==null?0:mapElements.get(key);

        	elementMsg = (!found? "não ":"") + "encontrado (" + element + ")" + (found?" vezes":"");        	
        	mapOutput.put(key, elementMsg);
        }
        return mapOutput;	
	}
	
}
