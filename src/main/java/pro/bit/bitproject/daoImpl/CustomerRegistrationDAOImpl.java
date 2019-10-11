/**
 * 
 */
package pro.bit.bitproject.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.common.ConnectionUtil;
import pro.bit.bitproject.dao.CustomerRegistrationDAO;
import pro.bit.bitproject.domain.CustomerRegistration;

/**
 * @author Hasini
 *
 */
public class CustomerRegistrationDAOImpl implements CustomerRegistrationDAO {

	@Override
	public JSONArray viewBranches() throws SQLException, Exception {

		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String branchliststr = "select branch_id, branch_descr from branch_det";
		PreparedStatement ps;
		ps = ConnectionUtil.openConnection().prepareStatement(branchliststr);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("id", rs.getString(1));
			 jsonObject.put("branch_descr", rs.getString(2));
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}
	
	public JSONArray getCustomerbyBranch() throws Exception {
		ResultSet rs = null;
		JSONArray cusjsonArray=new JSONArray();
		String listusers = "select customer_id,cus_nic from customer_details";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(listusers);
		ps.executeQuery();
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject cusjsonObject=new JSONObject();
			 cusjsonObject.put("customer_id", rs.getInt(1));
			 cusjsonObject.put("cus_nic", rs.getString(2));
			 cusjsonArray.put(cusjsonObject);
		}
		rs.close();
		return cusjsonArray;
	}

	@Override
	public CustomerRegistration createCus(CustomerRegistration customer) {
		String saveCusQuery = "INSERT INTO customer_details (branch_id,cus_fullname,cus_initials,cus_denotednames,cusshop_add1,"
				+ "cusshop_add2,cusshop_add3,cushome_add1,cushome_add2,cusshop_tel,cus_home_tel,cus_mobile,cushome_add3,cus_nic,"
						+ "cus_email,sp_fn,sp_initials,sp_initials_denotions,sp_add1,sp_add2,sp_add3,sp_home_tel,sp_mobile,sp_nic,sp_email,created_time,status,customer_type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement ps;
		try {
			ps = ConnectionUtil.openConnection().prepareStatement(saveCusQuery);
			ps.setInt(1, customer.getBranchId());
			ps.setString(2, customer.getCusfullname());
			
			ps.setString( 3,customer.getInitials());
			ps.setString( 4,customer.getInitialsextraction());
			ps.setString(5,customer.getCusshopAddresslineone());
			ps.setString(6,customer.getCusshopAddresslinetwo());
			ps.setString(7,customer.getCusshopAddresslinethree());
			ps.setString(8,customer.getCushomeAddresslineone());
			ps.setString(9,customer.getCushomeAddresslinetwo());
			ps.setString(10,customer.getShoptel());
			ps.setString(11,customer.getCushometel());
			ps.setString(12,customer.getCusmobile());
			ps.setString(13,customer.getCushomeAddresslinethree());
			ps.setString(14,customer.getCusnic());
			ps.setString(15,customer.getCusemail());
			ps.setString(16,customer.getCusspfullname());
			ps.setString( 17,customer.getSpinitials());
			ps.setString(18,customer.getSpinitialsextraction());
			ps.setString(19,customer.getSpcusAddresslineone());
			ps.setString( 20,customer.getSpcusAddresslinetwo());
			ps.setString(21,customer.getSpcusAddresslinethree());
			ps.setString(22,customer.getSpcushometel());
			ps.setString(23,customer.getSpcusmobile());
			ps.setString(24,customer.getCusspnic());
			ps.setString(25,customer.getCusspemail());
			ps.setTimestamp(26,Timestamp.valueOf(customer.getCreatedtime()));
			ps.setString(27,Character.toString(customer.getStatus()));
			ps.setInt(28, customer.getCustomerType());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkNIC(String nic) throws Exception {
		boolean sts = false;
		PreparedStatement st = ConnectionUtil.openConnection().prepareStatement("select cus_nic from customer_details where cus_nic = ?");    
		st.setString(1, nic);   
		ResultSet rs = st.executeQuery();
		
		while (rs.next()){
			if(rs.getString(1).equalsIgnoreCase(nic)){
				sts = true;
			}else {
				sts = false;
			}
				
		}
		return sts;
	}

	public JSONArray viewAllUsers() throws SQLException, Exception{
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String listusers = "select username,usertypeid from user";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(listusers);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("name", rs.getString(1));
			 jsonObject.put("id", rs.getInt(2));
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}
	
	public JSONArray viewAllCus() throws SQLException, Exception{
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String listusers = "select customer_id,cus_fullname,cus_nic from customer_details";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(listusers);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("customer_id", rs.getInt(1));
			 jsonObject.put("cus_fullname", rs.getString(2));
			 jsonObject.put("cus_nic", rs.getString(3));
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}

	public int getCusIdByNic(String nic) {
		int cusId = 0;
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select customer_id from customer_details where cus_nic = ?");
			ps.setString(1, nic);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				cusId = rs.getInt(cusId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cusId;
	}

	public String getCusSTSById(String custcashbookid) {
		String status = "A";
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("select status from customer_details where cus_nic = ?");
			ps.setString(1, custcashbookid);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				status = rs.getString(status);
				System.out.println("status"+status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public void updateCusSts(CustomerRegistration cus) {
		
		try {
			
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("update customer_details set status = ? where customer_id = ?");
			ps.setString(1,Character.toString(cus.getStatus()));
			ps.setInt(2, cus.getCusId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public JSONArray loadCustomersByNic(String selectednic) throws SQLException, Exception {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String customersDetails = "select * from customer_details where selectednic = ?";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(customersDetails);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){	
			
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("id", rs.getString(1));
			 jsonObject.put("cus_fullname", rs.getString(3));
			 jsonObject.put("cus_initials", rs.getString(4));
			 jsonObject.put("cus_denotednames", rs.getString(5));
			 jsonObject.put("cusshop_add1", rs.getString(6));
			 jsonObject.put("cusshop_add2", rs.getString(7));
			 jsonObject.put("cusshop_add3", rs.getString(8));
			 jsonObject.put("cushome_add1", rs.getString(9));
			 jsonObject.put("cushome_add2", rs.getString(10));
			 jsonObject.put("cusshop_tel", rs.getString(11));
			 jsonObject.put("cus_home_tel", rs.getString(12));
			 jsonObject.put("cus_mobile", rs.getString(13));
			 jsonObject.put("cushome_add3", rs.getString(14));
			 jsonObject.put("cus_nic", rs.getString(15));
			 jsonObject.put("cus_email", rs.getString(16));
			 jsonObject.put("sp_fn", rs.getString(17));
			 jsonObject.put("sp_initials", rs.getString(18));
			 jsonObject.put("sp_initials_denotions", rs.getString(19));
			 jsonObject.put("sp_add1", rs.getString(20));
			 jsonObject.put("sp_add2", rs.getString(21));
			 jsonObject.put("sp_add3", rs.getString(22));
			 jsonObject.put("sp_home_tel", rs.getString(23));
			 jsonObject.put("sp_mobile", rs.getString(24));
			 jsonObject.put("sp_nic", rs.getString(25));
			 jsonObject.put("sp_email", rs.getString(26));
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}
	
	public void updateCustomer(int custId, String sts){
		try {
			PreparedStatement ps = ConnectionUtil.openConnection().prepareStatement("update customer_details set status = ? where customer_id = ?");
			ps.setString(1,sts);
			ps.setInt(2, custId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCustomer(String NIC, String sts,String reason) throws SQLException, Exception {
		PreparedStatement ps;
		ps = ConnectionUtil.openConnection().prepareStatement("update customer_details set status = ? delete_reason = ?  where cus_nic = ?");
		ps.setString(1, sts);
		ps.setString(2, reason);
		ps.setString(3, NIC);
		ps.executeUpdate();
	}

	@Override
	public void editCustomer(String shoptel, String hometel, String mobileno, String spouseAdd1, String spouseAdd2,
			String spouseAdd3, String spouseHomeTel, String SpouseMobileNo,String NIC,String sts) throws SQLException, Exception {
		
		PreparedStatement ps;
		ps = ConnectionUtil.openConnection().prepareStatement("update customer_details set cusshop_tel = ? cus_home_tel = ? cus_mobile = ? sp_add1 = ? sp_add2 = ? sp_add3 = ? sp_home_tel = ? sp_mobile = ?  where cus_nic = ?");
		ps.setString(1, shoptel);
		ps.setString(2, hometel);
		ps.setString(3, mobileno);
		ps.setString(4, spouseAdd1);
		ps.setString(5, spouseAdd2);
		ps.setString(6, spouseAdd3);
		ps.setString(7, spouseHomeTel);
		ps.setString(8, SpouseMobileNo);
		ps.setString(9, sts);
		ps.setString(10, NIC);
		ps.executeUpdate();
	}

	public JSONArray viewBlacklistedCustomers() throws SQLException, Exception {
		ResultSet rs = null;
		JSONArray jsonArray=new JSONArray();
		String listusers = "select customer_id,cus_fullname,cus_nic,cus_mobile from customer_details where status = ?";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(listusers);
		ps.setString(1, "B");
		ps.executeQuery();
		rs = ps.getResultSet();
		while (rs.next()){
			 JSONObject jsonObject=new JSONObject();
			 jsonObject.put("customer_id", rs.getInt(1));
			 jsonObject.put("cus_fullname", rs.getString(2));
			 jsonObject.put("cus_nic", rs.getString(3));
			 jsonObject.put("cus_mobile", rs.getString(4));
			 jsonArray.put(jsonObject);
		}
		rs.close();
		return jsonArray;
	}
}




