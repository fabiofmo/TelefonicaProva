package com.telefonica.prova.utils.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.BufferedReader;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telefonica.prova.utils.CSVReaderUtils;

@ExtendWith(MockitoExtension.class)class CSVReaderUtilsTest {
	
	@InjectMocks private CSVReaderUtils csvReaderUtils;
	@Mock private BufferedReader bufferedReader;

	@Test
	void testCsvReader() {
        List<String[]> result = CSVReaderUtils.csvReader();
        assertNotEquals(1, result.size());		
	}

}
