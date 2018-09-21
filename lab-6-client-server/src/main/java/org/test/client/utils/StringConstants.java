package org.test.client.utils;

public class StringConstants {

	public static final String TRUE_STRING = "true";
	public static final String FALSE_STRING = "false";
	
	public static final String EMPTY_STRING = "";
	public static final String SPACE = " ";

	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String DASH = "-";
	public static final String DOT = ".";
	public static final String SLASH = "/";
	public static final String UNDERSCORE = "_";
	public static final String VERTICAL_BAR = "|";
	public static final String STAR_KEY = "*";
	public static final String ROUND_BRACKETS = ")";
	
	
	public static final String NOT_APPLICABLE = "N/A";
	public static final String ALL_PARTITIONS = "ALL_PARTITIONS";
	public static final String UNION_ALL = "UNION ALL";
	public static final String SELECT = "SELECT";
	public static final String FROM = "FROM";
	public static final String USUARIO_TABLA_PARTITIONS = "TER_WRITE.TB_HIST_MOVIMIENTOS PARTITION(TB_HIST_MOVIMIENTOS_";	

	public static final String MSISDN = "msisdn";
	
	public static final String UPDATE_OK = "OK";	
	public static final String UPDATE_KO = "KO";
	
	//errores comunes
	/*public static final String ERROR_MSISDN = "115, El parámetro msisdn es obligatorio";
	public static final String ERROR_FORMATO_MSISDN = "116, Formato de MSISDN no valido";
	public static final String ERROR_FORMATO_FECHAS = "103, El formato de la fecha introducida no es correcto (dd-MM-YY HH:mm:ss)";*/
	
	//errores del microservicio
	/*public static final String MSISDN_NO_EXISTE = "600, El MSISDN informado no existe para el Customer_ID informado en la BBDD";
	public static final String ERROR_BBDD = "401, Error al conectar con la base de datos";*/

	private StringConstants() {
		throw new IllegalAccessError("Constants class");
	}
}
