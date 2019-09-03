package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.json.JSONArray;

import pro.bit.bitproject.domain.DailyExpense;
import pro.bit.bitproject.domain.DailyIncome;

public interface FinanceDAO {
	public void createDailyExpense(DailyExpense dex);
	void createDailyIncome(DailyIncome dic);
	JSONArray getUserList ();
	JSONArray getExpenseTypeList();
	JSONArray getIncomeTypeList();
	public Double getTotalExpences(int branchId, LocalDateTime today) throws SQLException, Exception;
	public Double getTotalIncome(int branchId, LocalDateTime today) throws SQLException, Exception;
	

}
