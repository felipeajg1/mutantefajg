package com.mercadolibre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadolibre.dto.ConsultaTipoCandidatoDTO;
import com.mercadolibre.dto.IsMutantDTO;
import com.mercadolibre.dto.MutantInDTO;
import com.mercadolibre.repository.MutanteRepository;
import com.mercadolibre.service.MutanteService;

public class MutanteServiceTest {
	
	/**
	 * Atributo que determina el bean expedienteElectronicoBean
	 */
	@Mock
	private MutanteRepository mutanteRepository;
	
	@InjectMocks
	private MutanteService mutanteService;
	/**
     * Se ejecuta antes de cada una de las pruebas unitarias en este archivo.
     */
    @BeforeEach
    public void beforeMethod() {
    	MockitoAnnotations.initMocks(this);

    }

    @Test
    public void isMutantTest1() {    		
    	
    	try {
    		
    		String matriz[] = new String[6];
    		matriz[0] = "AtGCAA";
    		matriz[1] = "AAAAAA";
    		matriz[2] = "AAAAAA";
    		matriz[3] = "AtGCAA";
    		matriz[4] = "AtGCAA";
    		matriz[5] = "AtGCAA";
    	
    		MutantInDTO mutantInDTO = new MutantInDTO();
    		mutantInDTO.setDna(matriz);
    		IsMutantDTO resultadoDTO = mutanteService.isMutant(mutantInDTO);
    		
    		assertTrue(resultadoDTO.getIsMutant());
    		assertEquals(resultadoDTO.getHttpCodeMessage(), "200 OK");	
		} catch (Exception e) {			
		}   	
    }
    
    @Test
    public void isMutantTest2() {    		
    	
    	try {
    		
    		String matriz[] = new String[6];
    		matriz[0] = "AtGCAA";
    		matriz[1] = "AABAAA";
    		matriz[2] = "AABAAA";
    		matriz[3] = "AtGCAA";
    		matriz[4] = "AtGCAA";
    		matriz[5] = "AtGCAA";
    	
    		MutantInDTO mutantInDTO = new MutantInDTO();
    		mutantInDTO.setDna(matriz);
    		IsMutantDTO resultadoDTO = mutanteService.isMutant(mutantInDTO);
    		
    		assertTrue(resultadoDTO.getIsMutant());
    		assertEquals(resultadoDTO.getHttpCodeMessage(), "200 OK");	
		} catch (Exception e) {			
		}   	
    }
    
    @Test
    public void isMutantTest3() {    		
    	
    	try {
    		
    		String matriz[] = new String[6];
    		matriz[0] = "AtGCAA";
    		matriz[1] = "GACACA";
    		matriz[2] = "CAAACA";
    		matriz[3] = "AtACAA";
    		matriz[4] = "AAGCAA";
    		matriz[5] = "AtGCAA";
    	
    		MutantInDTO mutantInDTO = new MutantInDTO();
    		mutantInDTO.setDna(matriz);
    		IsMutantDTO resultadoDTO = mutanteService.isMutant(mutantInDTO);
    		
    		assertTrue(resultadoDTO.getIsMutant());
    		assertEquals(resultadoDTO.getHttpCodeMessage(), "200 OK");	
		} catch (Exception e) {			
		}   	
    }
    
    @Test
    public void isMutantTest4() {    		
    	
    	try {
    		
    		String matriz[] = new String[6];
    		matriz[0] = "AtGCAA";
    		matriz[1] = "GACACA";
    		matriz[2] = "CAAACA";
    		matriz[3] = "AtAAAA";
    		matriz[4] = "AAGCAA";
    		matriz[5] = "AtGCAA";
    	
    		MutantInDTO mutantInDTO = new MutantInDTO();
    		mutantInDTO.setDna(matriz);
    		IsMutantDTO resultadoDTO = mutanteService.isMutant(mutantInDTO);
    		
    		assertTrue(resultadoDTO.getIsMutant());
    		assertEquals(resultadoDTO.getHttpCodeMessage(), "200 OK");	
		} catch (Exception e) {			
		}   	
    }
    
    @Test
    public void isMutantTest5() {    		
    	
    	try {
    		
    		String matriz[] = new String[6];
    		matriz[0] = "AtGCAA";
    		matriz[1] = "GcCACA";
    		matriz[2] = "CAAACA";
    		matriz[3] = "AttAAA";
    		matriz[4] = "AAGCcA";
    		matriz[5] = "AtGCAA";
    	
    		MutantInDTO mutantInDTO = new MutantInDTO();
    		mutantInDTO.setDna(matriz);
    		IsMutantDTO resultadoDTO = mutanteService.isMutant(mutantInDTO);
    		
    		assertFalse(resultadoDTO.getIsMutant());
    		assertEquals(resultadoDTO.getHttpCodeMessage(), "403 FORBIDDEN");	
		} catch (Exception e) {			
		}   	
    }
    
    @Test
    public void isMutantTest6() {    		
    	
    	try {
    		
    		String matriz[] = new String[6];
    		matriz[0] = "AtGCAAa";
    		matriz[1] = "GcCACA";
    		matriz[2] = "CAAACA";
    		matriz[3] = "AttAAA";
    		matriz[4] = "AAGCcA";
    	
    		MutantInDTO mutantInDTO = new MutantInDTO();
    		mutantInDTO.setDna(matriz);
    		IsMutantDTO resultadoDTO = mutanteService.isMutant(mutantInDTO);
    		
    		assertFalse(resultadoDTO.getIsMutant());
    		assertEquals(resultadoDTO.getHttpCodeMessage(), "403 FORBIDDEN");	
		} catch (Exception e) {			
		}   	
    }
    
    @Test
    public void isMutantTest7() {    		
    	
    	try {
    		
    		String matriz[] = new String[6];
    		matriz[0] = "AtGCAA";
    		matriz[1] = "AACACA";
    		matriz[2] = "AAAACA";
    		matriz[3] = "AAtAAA";
    		matriz[4] = "AAGCcA";
    		matriz[5] = "AAGCcA";

    		MutantInDTO mutantInDTO = new MutantInDTO();
    		mutantInDTO.setDna(matriz);
    		IsMutantDTO resultadoDTO = mutanteService.isMutant(mutantInDTO);
    		
    		assertTrue(resultadoDTO.getIsMutant());
    		assertEquals(resultadoDTO.getHttpCodeMessage(), "200 OK");		
		} catch (Exception e) {			
		}   	
    }
    
    @Test
    public void consultaTipoCandidatosTest() {    		
    	
    	try {
    		
    		ConsultaTipoCandidatoDTO resultadoDTO = mutanteService.consultaTipoCandidatos();
    		
    		assertEquals(resultadoDTO.getCountHuman(), 0);		
		} catch (Exception e) {			
		}   	
    }

}
