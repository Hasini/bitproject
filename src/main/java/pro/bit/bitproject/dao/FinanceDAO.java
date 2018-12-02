package pro.bit.bitproject.dao;


import org.json.JSONArray;

import pro.bit.bitproject.domain.DailyExpense;
import pro.bit.bitproject.domain.DailyIncome;

public interface FinanceDAO {
	public void createDailyExpense(DailyExpense dex);
	void createDailyIncome(DailyIncome dic);
	JSONArray getUserList ();
	JSONArray getExpenseTypeList();
	JSONArray getIncomeTypeList();
	

}
