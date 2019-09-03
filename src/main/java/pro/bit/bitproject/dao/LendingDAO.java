package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.json.JSONArray;

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
	public JSONArray viewCustomerDetails(String cus_nic,String cus_home_tel) throws SQLException, Exception;
	public JSONArray getLendDetailsByNIC(String cus_nic) throws SQLException, Exception;
	public JSONArray getLendDetailsByNo(String cus_no) throws SQLException, Exception;
	public String getSheduledSts(String custcashbookid);
	public void updateInsPay(String custcashbookid, int noofins,String sts) throws Exception;
	public int getpaidscount(String custcashbookid);
	
	
	/**reshedule**/
	public void createLendReschdule(String custCashBookId, int noOfIns, double newInstallment,LocalDateTime created_date, 
			LocalDateTime created_date2, int previousMode,double previousInstallmentPayment) throws SQLException;
	public String getReSheduledSts(String custcashbookid);
	public double getReschPaymentInstallment(String custcashbookid) throws SQLException;
	public void updateCashBook(String custCashBookId) throws SQLException;
}
