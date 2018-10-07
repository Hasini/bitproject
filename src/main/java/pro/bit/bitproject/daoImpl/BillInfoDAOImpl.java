package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.BillInfoDAO;
import pro.bit.bitproject.domain.BillInfo;

public class BillInfoDAOImpl implements BillInfoDAO {

	@Override
	public BillInfo createBillInfo(BillInfo bi) {
		String insertQuery = "INSERT INTO bill_info (bill_code,bill_amount,submitted_by,entered_by,submit_time,entered_time,bill_date,"
				+ "incomeType,expenseType,cusId) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement(insertQuery);
			ps.setString(1, bi.getBillCode());
			ps.setDouble(2, bi.getBillAmount());
			ps.setInt(3, bi.getSubmittedUserId());
			ps.setInt(4, bi.getEnteredUserId());
			ps.setTimestamp(5, Timestamp.valueOf(bi.getSubmitTime()));
			ps.setTimestamp(6, Timestamp.valueOf(bi.getEnteredTime()));
			ps.setTimestamp(7, Timestamp.valueOf(bi.getBillDate()));
			//image
			//ps.setString(8, "wqrqw");
			ps.setInt(8, bi.getIncomeType());
			ps.setInt(9, bi.getExpenseType());
			ps.setInt(10, bi.getCustId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
