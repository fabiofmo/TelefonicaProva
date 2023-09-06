package com.telefonica.prova.utils.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telefonica.prova.utils.ProvaUtils;

@ExtendWith(MockitoExtension.class)
class ProvaUtilsTest {

	@InjectMocks private ProvaUtils provaUtils;
	@Mock private HashMap<Integer, Integer> mapElements;
	
	
	@Test
	void testVectorPreLoadMatrix() {
        int M = 3;
        int N = 3;

        int[][] result = ProvaUtils.vectorPreLoadMatrix(M, N);

        assertEquals(M, result.length);
        assertEquals(N, result[0].length);
	}

    @Test
    public void testVectorFormatOutput() {
        StringBuilder result = ProvaUtils.vectorFormatOutput(1, mapElements);
        assertNotEquals("XX => não encontrado (X vezes).", result.toString());
    }

	@Test
	void testVectorFormatOutput2() {
		int vector = 1;
        int count = 0;

        StringBuilder result = ProvaUtils.vectorFormatOutput(vector, count);
        assertNotEquals("1 => não encontrado (0).\n", result.toString());
	}

	@Test
	void testFormatValue() {
        assertEquals("01", ProvaUtils.formatValue(1));
	}

	@Test
	void testRunningSumTime() {
		assertEquals("02:00.000", ProvaUtils.runningSumTime("1:00.000", "1:00.000"));
	}

	@Test
	void testRunningAverageTime() {
		assertEquals("01:00.000", ProvaUtils.runningAverageTime("4:00.000", 4));
	}

	@Test
	void testRunningFastestTime() {
		assertEquals("02:00.000", ProvaUtils.runningFastestTime("2:00.000", "4:00.000"));
	}

}

