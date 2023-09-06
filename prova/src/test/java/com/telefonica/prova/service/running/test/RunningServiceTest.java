package com.telefonica.prova.service.running.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telefonica.prova.service.running.RunningService;
import com.telefonica.prova.utils.CSVReaderUtils;
import com.telefonica.prova.utils.ProvaUtils;

@ExtendWith(MockitoExtension.class)
class RunningServiceTest {

	@InjectMocks private RunningService runningService;
	@Mock private CSVReaderUtils csvReaderUtils;
	@Mock private ProvaUtils provaUtils;
	
	
	@Test
	void testFinishLine() {
	    List<String[]> list = new ArrayList<>();
	    list.add("23:49:08.277;038-Superman;1;1:02.852;44,275".split(";"));
	    
	    String outLine = runningService.finishLine();	    
        assertNotNull(outLine);
	}

	@Test
	void testRanking() throws Exception {
		
        List<String[]> list = new ArrayList<>();
        list.add("23:49:08.277;038-Superman;1;1:02.852;44,275".split(";"));

        String result = runningService.ranking(list);
        assertNotNull(result);
    }
}
