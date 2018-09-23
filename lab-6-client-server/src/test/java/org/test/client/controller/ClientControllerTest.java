package org.test.client.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.client.exception.ClientException;
import org.test.client.model.IsterDTO;
import org.test.client.service.ClientService;



@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

	@Mock
	ClientService clientService;
	
	@InjectMocks
	ClientController clientController;

	String msisdn;
	String msisdnOld;
	
	@Before
	public void setUp() {		
		msisdn = "691347667";
		msisdnOld = "691347667";		
	}	
	
	@Test	
	public void testUpDateMsisdn() throws ClientException {		
		doNothing().when(clientService).existMsisdn(Mockito.anyString());
		when(clientService.upDateMsisdnByMsisdnOld(Mockito.anyString(), Mockito.anyString())).thenReturn(getIsterDto());
		Assert.assertNotNull(this.clientController.upDateMsisdn(msisdn, msisdnOld));		
	}
	
	private IsterDTO getIsterDto() {
		IsterDTO isterDTO = new IsterDTO();
		isterDTO.setError(null);
		isterDTO.setResponseData(null);
		isterDTO.setMessage("OK");	
		return isterDTO;
	}
}
