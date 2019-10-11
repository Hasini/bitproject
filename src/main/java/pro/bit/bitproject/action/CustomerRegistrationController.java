package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;
import pro.bit.bitproject.domain.CustomerRegistration;



@WebServlet("/CustomerRegistrationController")
public class CustomerRegistrationController extends HttpServlet {
	 static final long serialVersionUID = 1L;
     
    public CustomerRegistrationController() {
        super();
   }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray jsonArray = new JSONArray();
		JSONArray cusJsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		String method = request.getParameter("method");
		String nic = request.getParameter("nic");
		String reason = request.getParameter("reason");
		String shoptel = request.getParameter("shoptel");
		String hometel = request.getParameter("hometel");
		String mobileno = request.getParameter("mobileno");
		String spouseAdd1 = request.getParameter("spouseAdd1");
		String spouseAdd2 = request.getParameter("spouseAdd2");
		String spouseAdd3 = request.getParameter("spouseAdd3");
		String spouseHomeTel = request.getParameter("spouseHomeTel");
		String SpouseMobileNo = request.getParameter("SpouseMobileNo");
		String branchId = request.getParameter("branch");
		
		
		switch (method) {
		case "view":
			try {
				jsonArray = viewBranches();
				cusJsonArray = getCustomerbyBranch();
				json.put("branchobj", jsonArray);
				json.put("cusObj", cusJsonArray);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "loadCustomersByNic":
			String Selectednic = request.getParameter("cus_nic");
			try {
				jsonArray = loadCustomersByNic(Selectednic);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			break;
		case "createCus":
			try {
				 if (checkAvailability(nic) == false){
					 createCustomer(request,response);
					 json.put("suc", "Record Successfully Created..!");
				 }else {
					 json.put("error", "Nic Already Exist..!");
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "viewUsers":
			try {
				jsonArray = viewAllUsers();
				json.put("userObj", jsonArray);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "viewallCus":
			try {
				jsonArray = viewAllCus();
				json.put("cusObj", jsonArray);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "deleteCus":
			try {
				deleteCustomer(nic,"D",reason);
				json.put("succ", "Customer is removed..!");
			} catch (Exception e) {
				try {
					json.put("error", "An error occured");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			break;
		case "editCus":
			try {
				editCustomer(shoptel,hometel,mobileno,spouseAdd1,spouseAdd2,spouseAdd3,spouseHomeTel,SpouseMobileNo,nic,"E");
				json.put("succ", "Customer is updated..!");
			} catch (Exception e) {
				try {
					json.put("error", "An error occured");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			break;
			

		default:
			break;
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

	

	private JSONArray loadCustomersByNic(String selectednic) throws SQLException, Exception {
		JSONArray allDetailsArr = new JSONArray();
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		allDetailsArr = cusdaoimpl.loadCustomersByNic(selectednic);
		return null;
	}

	private JSONArray getCustomerbyBranch() {
  		JSONArray cusArr = new JSONArray();
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		try {
			cusArr = cusdaoimpl.getCustomerbyBranch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cusArr;
	}

	private JSONArray viewAllCus() {
		JSONArray cusarr = new JSONArray();
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		
		try {
			cusarr = cusdaoimpl.viewAllCus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cusarr;
	}
	
	public JSONArray viewAllUsers() {
		JSONArray usersarr = new JSONArray();
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		try {
			usersarr = cusdaoimpl.viewAllUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersarr;
	}

	public boolean checkAvailability(String nic) {
		CustomerRegistrationDAOImpl cusDaoImp= new CustomerRegistrationDAOImpl();
		boolean sts = true;
		try {
			sts = cusDaoImp.checkNIC (nic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sts;
	}

	public void createCustomer(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		
		 CustomerRegistration customer = new CustomerRegistration();
		 CustomerRegistrationDAOImpl cusDaoImpl = new CustomerRegistrationDAOImpl();
		
		 int branchId = Integer.parseInt(request.getParameter("branch"));
		 String cusfullname = request.getParameter("fname");
		 String initials = request.getParameter("cus_initials");
		 String initialsextraction = request.getParameter("cus_othername");
		 String cusshopAddresslineone = request.getParameter("shad1");
		 String cusshopAddresslinetwo = request.getParameter("shad2");
		 String cusshopAddresslinethree = request.getParameter("shad3");
		 String cushomeAddresslineone = request.getParameter("hmad1");
		 String cushomeAddresslinetwo = request.getParameter("hmad2");
		 String cushomeAddresslinethree = request.getParameter("hmad3");
		
		 String cusnic = request.getParameter("nic");
		 String cusemail = request.getParameter("emal");
		
		 String cusspfullname = request.getParameter("sfname");
		 String spinitials = request.getParameter("sp_initials");
		 String spinitialsextraction = request.getParameter("sp_othername");
		 String spcusAddresslineone = request.getParameter("sad1");
		 String spcusAddresslinetwo = request.getParameter("sad2");
		 String spcusAddresslinethree = request.getParameter("sad3");
		 String spcushometel = request.getParameter("stel");
		 String shoptel = request.getParameter("shtel");
		 String cushometel = request.getParameter("hmtel");
		 String cusmobile = request.getParameter("mob");
		 String spcusmobile = request.getParameter("smob");
		 String cusspnic = request.getParameter("snic");
		 String cusspemail = request.getParameter("semail");
		 int customerType = Integer.parseInt(request.getParameter("cusT"));
		 LocalDateTime createdtime = LocalDateTime.now();
		 
		
		 customer.setBranchId(branchId);
		 customer.setCusfullname(cusfullname);
		 customer.setInitials(initials);
		 customer.setInitialsextraction(initialsextraction);
		 customer.setCusshopAddresslineone(cusshopAddresslineone);
		 customer.setCusshopAddresslinetwo(cusshopAddresslinetwo);
		 customer.setCusshopAddresslinethree(cusshopAddresslinethree);
		 customer.setCushomeAddresslineone(cushomeAddresslineone);
		 customer.setCushomeAddresslinetwo(cushomeAddresslinetwo);
		 customer.setShoptel(shoptel);
		 customer.setCushometel(cushometel);
		 customer.setCusmobile(cusmobile);
		 customer.setCushomeAddresslinethree(cushomeAddresslinethree);
		 customer.setCusnic(cusnic);
		 customer.setCusemail(cusemail);
		 customer.setCusspfullname(cusspfullname);
		 customer.setSpinitials(spinitials);
		 customer.setSpinitialsextraction(spinitialsextraction);
		 customer.setSpcusAddresslineone(spcusAddresslineone);
		 customer.setSpcusAddresslinetwo(spcusAddresslinetwo);
		 customer.setSpcusAddresslinethree(spcusAddresslinethree);
		 customer.setSpcushometel(spcushometel);
		 customer.setSpcusmobile(spcusmobile);
		 customer.setCusspnic(cusspnic);
		 customer.setCusspemail(cusspemail);
		 customer.setCreatedtime(createdtime);
		 customer.setStatus('A');
		 int cusId = getCusIdByNic(cusnic);
		 customer.setCustomerType(customerType);
		
		 cusDaoImpl.createCus(customer); 
	}

	private JSONArray viewBranches() {
		JSONArray brancharr = new JSONArray();
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		try {
			brancharr = cusdaoimpl.viewBranches();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brancharr;
	}
	
	public int getCusIdByNic(String nic){
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		return cusdaoimpl.getCusIdByNic(nic); 
	}
	
	private void deleteCustomer(String NIC,String sts,String reason){
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		try {
			cusdaoimpl.deleteCustomer(NIC,sts,reason);
		} catch (SQLException e) {
			System.out.println(e+"sql exception");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e+"exception");
			e.printStackTrace();
		}
	}
	
	private void editCustomer(String shoptel,String hometel,String mobileno,String spouseAdd1,
			String spouseAdd2,String spouseAdd3,String spouseHomeTel,String SpouseMobileNo,String NIC,String sts) throws SQLException, Exception{
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		cusdaoimpl.editCustomer(shoptel,hometel,mobileno,spouseAdd1,spouseAdd2,spouseAdd3,spouseHomeTel,SpouseMobileNo,NIC,sts);
	}
}