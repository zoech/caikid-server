import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import com.imzoee.model.User;
import com.imzoee.convention.ConstConv;


public class LoginServlet extends HttpServlet {
	
  private static final String KEY_SESSION_MODE = "session-key";
  private static final String LOGIN_MODE = "mode-login";
  private static final int STATUS_USERNOTEXIT = -1;
  private static final int STATUS_PWDERR = -2;
  
  
  private User lgUser = null;

  @Override
  public void doGet(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {
/*
    PrintWriter out = response.getWriter();

	response.addHeader("Content-Type", "application/json;charset=UTF-8");

	String parameter = request.getParameter("lgName");

    JSONObject obj = new JSONObject();
	obj.put("name","zoey");
	obj.put("pwd","2b2b");
	obj.put("credit",1000000);

	String jsonStr = JSON.toJSONString(obj);

	out.println("<html>");
	out.println("<head>");
	out.println("<title>example</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<p>" + parameter + "</p>");
	out.println("<p>" + jsonStr + "</p>");
	out.println("</body>");
	out.println("</html>");


    out.print(jsonStr);*/
  }

  @Override
  public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {

   
	
	//response.addHeader("Content-Type", "application/json;charset=UTF-8");

	String account = request.getParameter(ConstConv.RESKEY_ACCOUNT);
	String pwd = request.getParameter(ConstConv.RESKEY_PWD);
	
	if(account == null){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_ACCOUNTNULL);
		return;
	}
	
	if(pwd == null){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_PWDNULL);
		return;
	}

	int loginId = login(account, pwd);
	  
	if(loginId > 0){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_OK);
		response.setIntHeader(ConstConv.RESKEY_ID, loginId);
		
		HttpSession s = request.getSession();
		if(!s.isNew()){
			s.invalidate();
			s = request.getSession();
		}
		
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_OK);
		
		s.setAttribute(ConstConv.RESKEY_ID, loginId);
		s.setMaxInactiveInterval(0);
	} else {

		lgUser = null;
		switch (loginId){
			case STATUS_USERNOTEXIT:
				response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_USERNOTEXIST);
				break;
				
			case STATUS_PWDERR:
				response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_PWDERR);
				break;
				
			default:
				break;
		}
	}
	
	PrintWriter out = response.getWriter();
	String outStr = JSON.toJSONString(lgUser);
	out.print(outStr);
	
  }
  
  private int login(String account, String pwd){
	User user1 = new User(100,"zoey@imzoee.com","ab","zoey",10,"http://110.64.86.208:8080/caikid/eye_center.jpg");
    User user2 = new User(101,"13660560275","ab","zoe",1000,"http://110.64.86.208:8080/caikid/eye_center.jpg");
    User user3 = new User(110,"zoey","ab","zoe",100, "http://110.64.86.208:8080/caikid/eye_big.jpg");

    List<User> user = new ArrayList<User>();
    user.add(user1);
    user.add(user2);
    user.add(user3);
	
	for (int i = 0; i < user.size(); ++i){
	    if (account.equals(user.get(i).getAccount())){
		  if (pwd.equals(user.get(i).getPwd())){
			lgUser = user.get(i);
			return user.get(i).getId();
		  } else{
		    return STATUS_PWDERR;
		  }
		}
	}
	
	return STATUS_USERNOTEXIT;
  }
  
  /* notify the old login device, tell it its account relogin in new device, and force it logout */
  private void closeOldSession(HttpSession s){
	  
  }

}
