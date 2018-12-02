package pro.bit.bitproject.dao;

import java.sql.SQLException;
import pro.bit.bitproject.domain.LendingShedule;


public interface LendSchDao {
	public double gettotArr(String custcashbookid) throws SQLException;
	public int getcaid(String custcashbookid) throws SQLException;
	public void createls(LendingShedule ls) throws SQLException;
	public String getSheduledSts(String custcashbookid);
	public void updateInsPay(String custcashbookid, int noofins) throws Exception;
	public int getpaidscount(String custcashbookid);

}

