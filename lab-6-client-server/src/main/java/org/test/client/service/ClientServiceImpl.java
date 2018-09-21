package org.test.client.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.test.client.dao.ClientDao;
import org.test.client.dao.IValidator;
import org.test.client.exception.ClientException;
import org.test.client.model.IsterDTO;
import org.test.client.utils.StringConstants;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Autowired
	ClientDao clientDao;

	@Autowired
	IValidator ivalidator;
	
	@Value("${error.MSISDN_NO_EXISTE}")
	String MSISDN_NO_EXISTE;
	
	@Value("${error.ERROR_BBDD}")
	String ERROR_BBDD;

	@HystrixCommand(commandKey = "istclientCommand", groupKey = "istclientGroup", fallbackMethod = "fallBackCall")
	public IsterDTO existMsisdn(IsterDTO isterDTO) {
		return ivalidator.existMsisdn(isterDTO);
	}

	public IsterDTO fallBackCall(IsterDTO isterDTO, Throwable e) {
		LOGGER.debug("in fallBackCall {}", e);
		String fallback = "FAILED SERVICE CALL istvalidator! - FALLING BACK istvalidator";
		//isterDTO.setHasError(true);
		isterDTO.setMessage(new StringBuilder().append(fallback).append(e.getMessage()).toString());
		return isterDTO;
	}

	@Override
	public void validaExisteMsisdn(String msisdn) throws ClientException {
		
		try {
			List<String> listMsisdn = clientDao.getListMsisdn(msisdn);
			// verificamos si existe el msisdn que hemos recibido
			if (listMsisdn == null || listMsisdn.isEmpty()) {
				LOGGER.error("ClientServiceImpl - validaExisteMsisdn - msisdn={}, error={}", new Object[] {msisdn, MSISDN_NO_EXISTE});
				throw new ClientException(MSISDN_NO_EXISTE);
			}

		} catch (SQLException e) {
			LOGGER.error("ClientServiceImpl - validaExisteMsisdn:{}", e.getMessage());
			throw new ClientException(ERROR_BBDD);
		} catch (IOException e) {
			LOGGER.error("ClientServiceImpl - validaExisteMsisdn:{}", e.getMessage());
			throw new ClientException(ERROR_BBDD);//poner un error para el ioexception
		}
	}

	@Override	
	public IsterDTO upDateMsisdnByMsisdn_old(String msisdn, String msisdn_old) throws ClientException {		
		final IsterDTO isterDto = toIsterDTO();
		int rowsUpdated;
		try {
			rowsUpdated = clientDao.upDateMsisdnByMsisdn_old(msisdn, msisdn_old);
			String update_message = rowsUpdated > 0 ? StringConstants.UPDATE_OK : StringConstants.UPDATE_KO;
			isterDto.setMessage(update_message);			
		} catch (SQLException e) {
			LOGGER.error("ClientServiceImpl - upDateMsisdnByMsisdn_old:{}", e.getMessage());
			throw new ClientException(ERROR_BBDD);
		} catch (IOException e) {
			LOGGER.error("ClientServiceImpl - upDateMsisdnByMsisdn_old:{}", e.getMessage());
			throw new ClientException(ERROR_BBDD);//poner una exception del path
		}
		return isterDto;
	}	

	private IsterDTO toIsterDTO() {
		IsterDTO isterDTO = new IsterDTO();
		return isterDTO;
	}
}
