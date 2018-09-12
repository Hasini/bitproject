package pro.bit.bitproject.dao;



import org.json.JSONArray;



public interface IncomeExpenseDAO {
	JSONArray getUserList ();
	JSONArray getExpenseorIncomeTypeList();
	void createRecord (int type,int billId,double amount,int submittedUserId,int enteredUserId);
	

}
