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
	public void createIT (String code, String descr,LocalDateTime createddate);
	public IncomeType updateIT (IncomeType it) throws SQLException, Exception;
	public ExpenseType updateET (ExpenseType et) throws SQLException, Exception;
	public IncomeType deleteIT (String coded) throws SQLException, Exception;
	public JSONArray viewIT() throws SQLException, Exception;
		
	

}