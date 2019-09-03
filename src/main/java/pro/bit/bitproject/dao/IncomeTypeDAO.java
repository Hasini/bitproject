/**
 * 
 */
package pro.bit.bitproject.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.json.JSONArray;

import pro.bit.bitproject.domain.ExpenseType;
import pro.bit.bitproject.domain.IncomeType;

/**
 * @author Hasini
 *
 */
public interface IncomeTypeDAO {
	public IncomeType updateIT (IncomeType it) throws SQLException, Exception;
	public ExpenseType updateET (ExpenseType et) throws SQLException, Exception;
	public IncomeType deleteIT (int incomeTypeId) throws SQLException, Exception;
	public JSONArray viewET() throws SQLException, Exception;
	public JSONArray viewIT() throws SQLException, Exception;
	void createET(String code, String descr, LocalDateTime createddate);
		
	

}
