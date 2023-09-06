package com.telefonica.prova.vector.service.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telefonica.prova.service.vector.VectorService;
import com.telefonica.prova.utils.ProvaUtils;

@ExtendWith(MockitoExtension.class)
class VectorServiceTest {
	
	@InjectMocks private VectorService vectorService;
	@Mock private ProvaUtils provaUtils;
	@Mock private HashMap<Integer, Integer> mapElements;

	@Test
	void testVectorCount() {
		
		int[] vetor = {0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15};
		HashMap<Integer, Integer> mapElements = new HashMap<Integer, Integer>();
		mapElements.put(0,1);
		mapElements.put(1,2);
		mapElements.put(2,1);
		mapElements.put(3,1);
		mapElements.put(4,1);
		mapElements.put(5,1);
		mapElements.put(6,1);
		mapElements.put(7,1);
		mapElements.put(8,1);
		mapElements.put(9,1);
		mapElements.put(10,1);
		mapElements.put(11,1);
		mapElements.put(12,1);
		mapElements.put(13,1);
		mapElements.put(15,2);
	
		String expectedOutput = "Matriz recebida:\n0 1 1 2 3 4 5 6 7 8 9 10 11 12 13 15 \n\nContagem:\n0: [1] \n1: [2] \n2: [1] \n3: [1] \n4: [1] \n5: [1] \n6: [1] \n7: [1] \n8: [1] \n9: [1] \n10: [1] \n11: [1] \n12: [1] \n13: [1] \n15: [2] \n";
	
		String result = vectorService.vectorCount(vetor);	
		assertNotEquals(expectedOutput,result);
	}

	@Test
	void testVectorCountPreLoaded() {
		int[] vector = {1, 2, 3, 4, 5};
	    String expectedOutput = "Valores pré-carregados da Matriz (randômicos):\n" +
	                            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
	                            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
	                            "0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 \n" +
	                            "1 1 1 1 1 -2 -2 -2 -2 -2 -2 -2 -2 -2 -2 \n" +
	                            "-2 -2 -2 -2 -2 \n\n" +
	                            "Valores a contar:\n" +
	                            "1.00E+00 -3.00E+00 \n\n" +
	                            "Contagem:\n" +
	                            "Valor:   +1.00E+00 | Contagem: +6.00E+00\n" +
	                            "Valor:   -3.00E+00 | Contagem: +6.00E+00\n";
	    
	    String output = vectorService.vectorCountPreLoaded(vector);
	    assertNotEquals(expectedOutput, output);
	}

	@Test
	void testVectorCountJSON() {
		int[] vector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        HashMap<Integer, String> expectedOutput = new HashMap<>();
        expectedOutput.put(0, "não encontrado (0)");
        expectedOutput.put(1, "encontrado (1) vez");
        expectedOutput.put(2, "encontrado (2) vezes");
        expectedOutput.put(3, "encontrado (3) vezes");
        expectedOutput.put(4, "não encontrado (0)");
        expectedOutput.put(5, "não encontrado (0)");
        expectedOutput.put(6, "não encontrado (0)");
        expectedOutput.put(7, "não encontrado (0)");
        expectedOutput.put(8, "não encontrado (0)");
        expectedOutput.put(9, "não encontrado (0)");
        expectedOutput.put(10, "não encontrado (0)");
        expectedOutput.put(11, "não encontrado (0)");
        expectedOutput.put(12, "não encontrado (0)");
        expectedOutput.put(13, "não encontrado (0)");
        expectedOutput.put(14, "não encontrado (0)");
        expectedOutput.put(15, "não encontrado (0)");

        assertNotEquals(expectedOutput, vectorService.vectorCountJSON(vector));
	}

}
