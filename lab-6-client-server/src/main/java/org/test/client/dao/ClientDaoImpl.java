package org.test.client.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.test.client.jdbc.NamedParameterStatement;
import org.test.client.jdbc.SQLReader;
import org.test.client.jdbc.SqlUtils;

@Repository
public class ClientDaoImpl implements ClientDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientDaoImpl.class);
	
	//private static final String FILENAME_UPDATE_MSISDN_BY_MSISDN_OLD = "getAllConsultaMovimientos";
	//private static final String FILENAME_GET_MSISDN = "getMsisdn";
	
	@Autowired
	DataSource dataSource;
	
	@Value("${filename.FILENAME_UPDATE_MSISDN_BY_MSISDN_OLD}")
	String FILENAME_UPDATE_MSISDN_BY_MSISDN_OLD;
	
	@Value("${filename.FILENAME_GET_MSISDN}")
	String FILENAME_GET_MSISDN;
	
	@Override
	public List<String> getListMsisdn(String msisdn) throws SQLException, IOException {
		Connection conn = dataSource.getConnection();
		NamedParameterStatement namedParameterStatement = null;		
		ResultSet rs = null;
		List<String> listaMsisdn = new ArrayList<String>();		
		
		try {
			final SQLReader sqlReader = new SQLReader();
			final String sQuery = sqlReader.getQuery(SQLReader.QUERY_FOLDER.ISTERCAMBIOMSISDN, FILENAME_GET_MSISDN);
			
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);
			namedParameterStatement.setString("msisdn", msisdn);
			rs = namedParameterStatement.executeQuery();
			while(rs.next()) {				
				listaMsisdn.add(rs.getString("MSISDN"));			
			}
						
			
		} finally {
			SqlUtils.closeResulSet(rs);
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();		
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("msisdn:{}", new Object[] {msisdn});
		}
		return listaMsisdn;
	}
		
	@Override
	public int upDateMsisdnByMsisdn_old(String msisdn, String msisdn_old) throws SQLException, IOException {
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		NamedParameterStatement namedParameterStatement = null;
		
		try {
			final SQLReader sqlReader = new SQLReader();
			final String sQuery = sqlReader.getQuery(SQLReader.QUERY_FOLDER.ISTERCAMBIOMSISDN, FILENAME_UPDATE_MSISDN_BY_MSISDN_OLD);
			
			namedParameterStatement = new NamedParameterStatement(conn, sQuery);			
			namedParameterStatement.setString("msisdn", msisdn);
			namedParameterStatement.setString("msisdn_old", msisdn_old);
			int result =  namedParameterStatement.executeUpdate();			
			/*if(result > 0) {
				throw new SQLException("este es el error que envio a posta" + result);//esto es para probar la transaccion
			}*/
			conn.commit();
			return result;			
		}catch( SQLException e) {
			conn.rollback();			
			throw new SQLException(e.getMessage());
			
		} finally {
			SqlUtils.closeNamedParameterStatement(namedParameterStatement);
			conn.close();			
		}		
	}
}
