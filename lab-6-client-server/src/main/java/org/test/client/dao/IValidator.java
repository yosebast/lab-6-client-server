package org.test.client.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.client.model.IsterDTO;

@FeignClient("ISTVALIDATOR")
public interface IValidator {
	
	@RequestMapping(value="/orange/exist_msisdn", method=RequestMethod.POST)
	public @ResponseBody IsterDTO existMsisdn(@RequestBody IsterDTO isterDTO);
}
