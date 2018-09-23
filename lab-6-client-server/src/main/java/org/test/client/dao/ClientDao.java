package org.test.client.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ClientDao {
	List<String> getListMsisdn(String msisdn) throws SQLException, IOException;
	int upDateMsisdnByMsisdnOld(String msisdn, String msisdnOld) throws SQLException, IOException;
}
