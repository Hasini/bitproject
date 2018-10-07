package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import pro.bit.bitproject.domain.CashBook;
import pro.bit.bitproject.domain.LendingShedule;

public interface LendingDAO {
	public CashBook createCasahbook(CashBook cb);
	public String getcuscashbookid(int customerId);
	public double getmodepayment(String custcashbookid);
	public void saveArrearsAmt(String custcashbookid, LocalDateTime paiddate, double currentarr);
	public double gettotArr(String custcashbookid) throws SQLException;
	public int getcaid(String custcashbookid) throws SQLException;
	public void createls(LendingShedule ls) throws SQLException;
}
