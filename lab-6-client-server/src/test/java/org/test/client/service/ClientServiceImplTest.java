package org.test.client.service;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.client.dao.ClientDao;
import org.test.client.exception.ClientException;
import org.test.client.service.ClientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

	@Mock
	ClientDao clientDao;
	
	@InjectMocks
	ClientServiceImpl clientServiceImpl;
	
	String msisdn;
	String msisdnOld;
	int rowsUpdated;
	
	@Before
	public void setUp() {		
		 msisdn = "665532538";
		 msisdnOld = "665532539";
		 rowsUpdated = 1;				
	}	
	
	@Test
	public void testExistMsisdn() throws SQLException, IOException, ClientException {
		when(clientDao.getListMsisdn(Mockito.anyString())).thenReturn(getListMsisdn());
		this.clientServiceImpl.existMsisdn(msisdn);
	}
	
	@Test(expected=ClientException.class)
	public void testExistMsisdnError() throws SQLException, IOException, ClientException  {	
	 doThrow(ClientException.class).when(clientDao).getListMsisdn(Mockito.anyString());
	this.clientServiceImpl.existMsisdn(msisdn);
	
	}
	
	@Test
	public void testUpDateMsisdnByMsisdn_old() throws SQLException, IOException, ClientException {
		when(clientDao.upDateMsisdnByMsisdnOld(Mockito.anyString(), Mockito.anyString())).thenReturn(rowsUpdated);
		this.clientServiceImpl.upDateMsisdnByMsisdnOld(msisdn, msisdnOld);
	}
	
	private List<String> getListMsisdn() {		
		List<String> lista = Arrays.asList(msisdn);		
		return lista;
	}	
	
}
