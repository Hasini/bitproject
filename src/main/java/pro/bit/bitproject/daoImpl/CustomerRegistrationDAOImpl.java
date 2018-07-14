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
		String branchliststr = "select * from branch_det";
		PreparedStatement ps;
		
		ps = ConnectionUtil.openConnection().prepareStatement(branchliststr);
		ps.executeQuery();
		
		rs = ps.getResultSet();
		
		while (rs.next()){			
			 JSONObject jsonObject=new JSONObject();
			
			 jsonObject.put("id", rs.getString(1));
			 jsonObject.put("branch_descr", rs.getString(3));
			 
			 
			 System.out.println("id"+rs.getString(1));
			 System.out.println("branch_descr"+rs.getString(3));
			 jsonArray.put(jsonObject);
			
			
		}
		rs.close();
		return jsonArray;
	}

	@Override
	public CustomerRegistration createCus(CustomerRegistration customer) {
		String saveCusQuery = "INSERT INTO customer_details (branch_id,cus_fullname,cus_initials,cus_denotednames,cusshop_add1,"
				+ "cusshop_add2,cusshop_add3,cushome_add1,cushome_add2,cusshop_tel,cus_home_tel,cus_mobile,cushome_add3,cus_nic,"
						+ "cus_email,sp_fn,sp_initials,sp_initials_denotions,sp_add1,sp_add2,sp_add3,sp_home_tel,sp_mobile,sp_nic,sp_email,created_time,status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
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
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public String checkNIC(String nic) {
		String availablenic = "select cus_nic from customer_details";
		String sts = null;
		
		if (nic == availablenic){
			sts = "A";
		}else {
			sts = "NA";
		}
		return sts;
	}
	

}



