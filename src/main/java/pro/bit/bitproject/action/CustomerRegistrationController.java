package pro.bit.bitproject.action;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import pro.bit.bitproject.daoImpl.CustomerRegistrationDAOImpl;
import pro.bit.bitproject.domain.CustomerRegistration;

/**
 * Servlet implementation class CustomerRegistrationController
 */
@WebServlet("/CustomerRegistrationController")
public class CustomerRegistrationController extends HttpServlet {
	 static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegistrationController() {
        super();
   }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		String method = request.getParameter("method");
		String nic = request.getParameter("nic");
		
		String flag = checkAvailability(nic);
		
		
		switch (method) {
		case "view":
			try {
				jsonArray = viewBranches();
				json.put("branchobj", jsonArray);
				System.out.println(json+"+++++++++++++++");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			break;
		case "createCus":
			try {
				createCustomer(request,response);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			break;
			

		default:
			break;
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}

	private String checkAvailability(String nic) {
		CustomerRegistrationDAOImpl cusDaoImpl = new CustomerRegistrationDAOImpl();
		String sts = cusDaoImpl.checkNIC (nic);
		return sts;
	}

	public void createCustomer(HttpServletRequest request, HttpServletResponse response) {
		
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
		 
		 if (checkAvailability(cusnic) != "A"){
			 cusDaoImpl.createCus(customer); 
		 }else {
			 System.out.println("aa");
		 }
		 
		 
		
		
	}

	public JSONArray viewBranches() {
		JSONArray brancharr = new JSONArray();
		CustomerRegistrationDAOImpl cusdaoimpl = new CustomerRegistrationDAOImpl();
		try {
			brancharr = cusdaoimpl.viewBranches();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brancharr;
	}
	
	

	
	

}