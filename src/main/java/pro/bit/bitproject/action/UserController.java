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

import pro.bit.bitproject.daoImpl.UserDAOImpl;
import pro.bit.bitproject.domain.User;



@WebServlet("/userservlet")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		String method = request.getParameter("method");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int usertypeid = 0;
		if (request.getParameter("usertypeid") != null) {
			usertypeid = Integer.parseInt(request.getParameter("usertypeid"));
		}
		
		
		switch (method) {
		case "signup" :
			LocalDateTime createddate =  LocalDateTime.now();
			try {
				createUser (response,username,password,usertypeid,createddate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "view" :
			try {
				jsonArray = viewusertype();
				json.put("usertypeobj", jsonArray);
				System.out.println(json+"+++++++++++++++");
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			break;
		case "login" :
			try {
				loginuser (request,response,username,password,usertypeid);
				json.put("success", "Log in successful");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			break;
			
		case "deleteUser" :
			try {
				deleteUser(username);
				json.put("success", "User deleted..!");
			} catch (Exception e) {
				e.printStackTrace();
				try {
					json.put("error", "Error Occured..!");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case "passwordchange" :
			try {
				changepw (username,password);
				json.put("success", "Password Changed..!");
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

	public void loginuser(HttpServletRequest request, HttpServletResponse response, String username, String password, int usertypeid) throws IOException {
		JSONObject json = new JSONObject();
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		//User user = new User();
		try {
			String sts = userdaoimpl.loginUser(username,password,usertypeid);
			 
			if (sts.equals("true") ){
				json.put("success", "Log in successful");
				System.out.println("trueee");
			}else {
				json.put("error", "Log in denied");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
		
	}

	public void createUser(HttpServletResponse response, String username, String password, int usertypeid, LocalDateTime createddate) throws Exception {
		JSONObject json = new JSONObject();
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		User user = new User();
		
		
		try {
			user.setUsername(username);
			user.setPassword(password);
			user.setUsertypeid(usertypeid);
			user.setCreatedtime(createddate);
			user.setUserstatus('A');
			
			userdaoimpl.createUser(user);
			json.put("successx", "Record successsfully created");
			System.out.println("yeeeeeeeeeeeeeee" + json);
		} catch (Exception e) {
			json.put("error", "error occured while saving user");
			System.out.println();
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
		
	}
	
	public JSONArray viewusertype() throws SQLException, Exception {
		UserDAOImpl userdaoImpl = new UserDAOImpl();
		JSONArray jsonArray=new JSONArray();
		try {
			jsonArray= userdaoImpl.viewusertype();
		} catch (Exception e) {
			System.out.println("eeee");
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	public void deleteUser(String username) {
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		try {
			userdaoimpl.deleteUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changepw(String username, String password) {
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		try {
			userdaoimpl.changepassword(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
