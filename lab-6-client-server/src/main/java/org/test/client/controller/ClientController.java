package org.test.client.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.client.exception.ClientException;
import org.test.client.exception.ValidationException;
import org.test.client.model.IsterDTO;
import org.test.client.model.ResponseError;
import org.test.client.service.ClientService;
import org.test.client.utils.StringConstants;
import org.test.client.utils.ValidatorUtils;


@RestController
public class ClientController {
	
	@Autowired
	ClientService clientService;	

	@RequestMapping(value="/orange/istclient", method=RequestMethod.POST)
	public IsterDTO getDateAltaProductByClient(@RequestBody IsterDTO isterDTO) {
		return isterDTO;		
	}
	
/*	@PostMapping(value="/orange/update_msisdn", produces=MediaType.APPLICATION_JSON_VALUE)
	public IsterDTO upDateMsisdn(@RequestBody IsterDTO isterDTO) {		
		//primero validamos los datos de entrada si so correctos
		//luego validamos si existe el msisdn 
		IsterDTO obj_isterDto = null;
		try {
			ValidatorUtils.validaIfMsisdnIsCorrect(IsterDTO.MSISDN, isterDTO.getMsisdn(), true);
			ValidatorUtils.validaIfMsisdnIsCorrect(IsterDTO.MSISDN, isterDTO.getMsisdn_old(), true);
			//aqui habria que validad el codigo de cliente pero no tenemos un valisdador para esto  ¿preguntar?
			
			//existe el msisdn  con el customer_id
			 clientService.existMsisdn(isterDTO);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return obj_isterDto;
	}	*/
	
	@GetMapping("actualiza_msisdn/{MSISDN_Nuevo}/{MSISDN_Viejo}")
	public IsterDTO upDateMsisdn(@PathVariable("MSISDN_Nuevo") String msisdn, @PathVariable("MSISDN_Viejo") String msisdnOld) {
		IsterDTO objIsterDto;	
		try {
			ValidatorUtils.validaIfMsisdnIsCorrect(StringConstants.MSISDN, msisdn, true);
			ValidatorUtils.validaIfMsisdnIsCorrect(StringConstants.MSISDN, msisdnOld, true);
			// buscamos si existe el msisdn_old informado
			clientService.existMsisdn(msisdnOld);
			objIsterDto = clientService.upDateMsisdnByMsisdnOld(msisdn, msisdnOld);
		} catch (ClientException | ValidationException e) {
			objIsterDto = createdMessageError(e.getMessage());
		}
		return objIsterDto;
	}

	private IsterDTO createdMessageError(String messageException) {
		IsterDTO isterDTO = new IsterDTO();
		String code = messageException.substring(0, messageException.indexOf(StringConstants.COMMA)).trim();
		String message = messageException.substring(messageException.indexOf(StringConstants.COMMA)+1, messageException.length()).trim();
		ResponseError responseError = new ResponseError();
		if (!StringUtils.isEmpty(code)) {
			responseError.setCode(Integer.parseInt(code));
		}
		if (!StringUtils.isEmpty(message)) {
			responseError.setMessage(message);
		}
		isterDTO.setError(responseError);
		return isterDTO;
	}
}
