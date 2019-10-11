package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import pro.bit.bitproject.domain.CashBook;

public interface CashBookDao {
	public CashBook createCasahbook(CashBook cb) throws SQLException;

	public String getcuscashbookid(int customerId);

	public double getmodepayment(String custcashbookid);

	public void saveArrearsAmt(String custcashbookid, LocalDateTime paiddate, double currentarr);

	public void saveArrearsAmttogetTot(String custcashbookid, LocalDateTime paiddate, double currentarr)
			throws SQLException;

	public void updateArrearsAmttogetTot(String custcashbookid, LocalDateTime paiddate,
			double availabletotarrearsforthecus, double extrayment, double todayarr) throws Exception;

	public double getcurrenttotarr(String customer_cash_book_Id);

	public int getmode(String custcashbookid);

	public int getNoOfPaidInstallments(String custcashbookid);

	public String getSchType(String custcashbookid) throws SQLException;

	public int getCusType(int customerId) throws SQLException;

	public int getCreditLimit(int customerType) throws SQLException;

	public double deductTotalArr(String custcashbookid,  double extraPay) throws SQLException;
}
