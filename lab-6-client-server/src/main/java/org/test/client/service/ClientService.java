package org.test.client.service;

import org.test.client.exception.ClientException;
import org.test.client.model.IsterDTO;


public interface ClientService {
	IsterDTO existMsisdn(IsterDTO isterDTO);
	void validaExisteMsisdn(String msisdn) throws ClientException;
	IsterDTO upDateMsisdnByMsisdn_old(String msisdn, String msisdn_old) throws ClientException;

}
