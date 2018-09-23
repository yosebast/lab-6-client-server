package org.test.client.service;

import org.test.client.exception.ClientException;
import org.test.client.model.IsterDTO;


public interface ClientService {
	IsterDTO existMsisdn(IsterDTO isterDTO);
	void existMsisdn(String msisdn) throws ClientException;
	IsterDTO upDateMsisdnByMsisdnOld(String msisdn, String msisdnOld) throws ClientException;

}
