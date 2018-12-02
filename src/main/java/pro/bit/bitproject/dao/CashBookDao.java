package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import pro.bit.bitproject.domain.CashBook;

public interface CashBookDao {
	public CashBook createCasahbook(CashBook cb);
	public String getcuscashbookid(int customerId);
	public double getmodepayment(String custcashbookid);
	public void saveArrearsAmt(String custcashbookid, LocalDateTime paiddate, double currentarr);
	public void saveArrearsAmttogetTot(String custcashbookid, LocalDateTime paiddate, double currentarr)throws SQLException;
	public void updateArrearsAmttogetTot(String custcashbookid, LocalDateTime paiddate, double availabletotarrearsforthecus,double extrayment,double todayarr) throws Exception ;
	public double getcurrenttotarr(String customer_cash_book_Id);
	public int getmode(String custcashbookid);
}
