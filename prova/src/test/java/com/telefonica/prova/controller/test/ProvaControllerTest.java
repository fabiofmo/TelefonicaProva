package com.telefonica.prova.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.telefonica.prova.controller.ProvaController;
import com.telefonica.prova.service.running.RunningService;
import com.telefonica.prova.service.vector.VectorService;

@ExtendWith(MockitoExtension.class)
class ProvaControllerTest {
	
	@InjectMocks private ProvaController controller;
	@Mock private VectorService provaService;
	@Mock private RunningService runningService;
	    

    @Test
    public void testGetVectorCount() {
        int[] vector = {0, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15};
        when(provaService.vectorCount(vector)).thenReturn("result");
        assertEquals("result", controller.getVectorCount());
    }
    
    @Test
    public void testGetVectorCountPreLoaded() {
        int[] vector = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 18, 12, 13, 14, 15};
        when(provaService.vectorCountPreLoaded(vector)).thenReturn("result");
        assertEquals("result", controller.getVectorCountPreLoaded());
    }

    @Test
    public void testGetVectorCountJSON() {
        int[] vector = {0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 15};
        
        HashMap<Integer, String> mapOutput = new HashMap<Integer, String>();
        mapOutput.put(1, "result");
        
        when(provaService.vectorCountJSON(vector)).thenReturn(mapOutput);
        assertNotEquals(mapOutput, controller.getVectorCountJSON());
    }

    @Test
    public void testGetRunning() {
        when(runningService.finishLine()).thenReturn("result");
        assertEquals("result", controller.getRunning());
    }
    
    
    
}
