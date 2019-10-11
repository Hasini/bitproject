package pro.bit.bitproject.dao;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import org.json.JSONArray;

import pro.bit.bitproject.domain.DailyExpense;
import pro.bit.bitproject.domain.DailyIncome;

public interface FinanceDAO {
	public void createDailyExpense(DailyExpense dex);
	void createDailyIncome(DailyIncome dic, InputStream inputStream);
	JSONArray getUserList ();
	JSONArray getExpenseTypeList();
	JSONArray getIncomeTypeList();
	public Double getTotalExpences(int branchId, Date today) throws SQLException, Exception;
	public Double getTotalIncome(int branchId, Date today) throws SQLException, Exception;
	

}
