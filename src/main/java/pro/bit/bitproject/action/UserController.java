package pro.bit.bitproject.action;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String method = request.getParameter("method");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int usertypeid = Integer.parseInt(request.getParameter("usertypeid"));
		
		
		System.out.println(method);
		
		switch (method) {
		case "signup" :
			LocalDateTime createddate =  LocalDateTime.now();
			try {
				createUser (response,username,password,usertypeid,createddate);
			} catch (Exception e) {
				System.out.println("*********");
				e.printStackTrace();
			}
			break;
		case "login" :
			//LocalDateTime createddate =  LocalDateTime.now();
			try {
				loginuser (request,response,username,password,usertypeid);
			} catch (Exception e) {
				System.out.println("*********");
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	private void loginuser(HttpServletRequest request, HttpServletResponse response, String username, String password, int usertypeid) throws IOException {
		JSONObject json = new JSONObject();
		UserDAOImpl userdaoimpl = new UserDAOImpl();
		//User user = new User();
		try {
			String sts = userdaoimpl.loginUser(username, password, usertypeid);
			 
			if (sts.equals("true")){
				json.put("success", "Log in successful");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.jsp");
				dispatcher.forward(request, response);
			}else {
				json.put("error", "Log in denied");
			}
			
		} catch (Exception e) {
			System.out.println("ex");
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
			
			userdaoimpl.createUser(user);
			json.put("success", "Record successsfully created");
			
		} catch (Exception e) {
			json.put("error", "error occured while saving user");
			System.out.println();
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json.toString());
		
	}

}
