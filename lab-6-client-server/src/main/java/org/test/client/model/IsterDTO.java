package org.test.client.model;

import java.util.Collection;

public class IsterDTO {	
	public static final String MSISDN = "msisdn";	
		
	//private boolean hasError = false;
	private String message;		
	//private String msisdn;
	//private String msisdn_old;
	//private String init_date;
	//private String end_date;
	//private String PartnerName;
	//private Map<String, Object> actionObjects;	
	private Collection<?> responseData;
	private ResponseError error;
	//private String uuid;
	
	
	public IsterDTO() {
		super();
	}
/*aqui solo defino lo que sera parte del objeto de respuesta IsterDTO el resto se podra borra lo que pasa es que esta 
 * siendo usado por el primer metodo del controller*/
	public IsterDTO(/*boolean hasError,*/ String message, Collection<?> responseData, ResponseError error) {
		super();
		//this.hasError = hasError;
		this.message = message;
		this.responseData = responseData;
		this.error = error;
	}

/*	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}	

	public String getInit_date() {
		return init_date;
	}

	public void setInit_date(String init_date) {
		this.init_date = init_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getPartnerName() {
		return PartnerName;
	}

	public void setPartnerName(String partnerName) {
		PartnerName = partnerName;
	}

	public Map<String, Object> getActionObjects() {
		return actionObjects;
	}

	public void setActionObjects(Map<String, Object> actionObjects) {
		this.actionObjects = actionObjects;
	}*/	

	public Collection<?> getResponseData() {
		return responseData;
	}

	public void setResponseData(Collection<?> responseData) {
		this.responseData = responseData;
	}

/*	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}*/

	/*public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}*/

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/*public String getMsisdn_old() {
		return msisdn_old;
	}

	public void setMsisdn_old(String msisdn_old) {
		this.msisdn_old = msisdn_old;
	}*/

	public ResponseError getError() {
		return error;
	}

	public void setError(ResponseError error) {
		this.error = error;
	}	
}
