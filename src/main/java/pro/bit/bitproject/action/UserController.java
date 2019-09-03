package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "login" :
			try {
				
				String sts=loginuser (request,response,username,password);
				String userType= viewusertype(username);
				if(sts.equalsIgnoreCase("true")){
					json.put("userType", userType);
					json.put("success", "Log in successful");
				}else
					json.put("error", "Login denied");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			break;
			
		case "deleteUser" :
			try {
				
				boolean sts = deleteUser(username);
				if (sts){
					json.put("success", "User Inactivated Permanently..!");
				} else {
					json.put("error", "User is already inactivated !");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					json.put("error", "User is inactivated..!");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case "passwordchange" :
			try {
				boolean sts = changepw (username,password);
				if (sts) {
					json.put("success", "Password Changed..!");
				} else {
					json.put("error", "User is inactivated..!");
				}
				
			} catch (Exception e) {
				try {
					json.put("error", "User is inactivated..!");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		case "logout" :
			try {
				logout(request,response);
				//json.put("success", "Session Expired..!");
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

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
        try {
			response.sendRedirect(request.getContextPath() + "/header.jsp");
			return;
		} catch (IOException e) {
			response.sendError(400,"Session Expired");
			e.printStackTrace();
		}
		
	}

	public String loginuser(HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException {
		HttpSession httpSession = request.getSession();
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		String sts=null;
		
		try {
			 sts = userdaoimpl.loginUser(username,password);
			 httpSession.setAttribute("username", username);
			 httpSession.setAttribute("password", password);
			 
			 //httpSession.setMaxInactiveInterval(60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sts;
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
			user.setUserstatus("A");
			user.setUpdatedtime(createddate);
			userdaoimpl.createUser(user);
			json.put("signupSuc", "Record successsfully created");
		} catch (Exception e) {
			json.put("error", "error occured while saving user");
		}
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
	}
	
	private JSONArray viewusertype() throws SQLException, Exception {
		UserDAOImpl userdaoImpl = new UserDAOImpl();
		JSONArray jsonArray=new JSONArray();
		try {
			jsonArray= userdaoImpl.viewusertype();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	private String viewusertype(String userName) throws SQLException, Exception {
		UserDAOImpl userdaoImpl = new UserDAOImpl();
		String userType = null;
		try {
			 userType= userdaoImpl.viewusertype(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  userType;
	}
	
	public boolean deleteUser(String username) throws Exception {
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		boolean sts = true;
		try {
			if (!userdaoimpl.viewUserSts(username).equals("D")) {
				userdaoimpl.deleteUser(username);
			} else {
				sts = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sts;
	}
	
	public boolean changepw(String username, String password) {
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		boolean sts = true;
		try {
			if (!userdaoimpl.viewUserSts(username).equals("D")) {
				userdaoimpl.changepassword(username,password);
			} else {
				sts = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sts;
		
	}
}
