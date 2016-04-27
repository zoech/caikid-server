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
import javax.servlet.http.Cookie;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

import com.imzoee.model.User;
import com.imzoee.convention.LoginConv;
import com.imzoee.convention.ConstConv;


public class TestCookieServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {

    

    JSONObject obj = new JSONObject();
	Cookie[] c = request.getCookies();

	if (c != null && c.length > 0){
	for(int i = 0; i < c.length; ++i){
	  obj.put(c[i].getName(),c[i].getValue());
	}}


	obj.put("name","zoey");
	obj.put("pwd","2b2b");
	obj.put("credit",1000000);

	String jsonStr = JSON.toJSONString(obj);

	//PrintWriter out = response.getWriter();
    //out.print(jsonStr);


	response.addHeader("Content-Type", "application/json;charset=UTF-8");

	
	HttpSession s = request.getSession();
	s.setMaxInactiveInterval(30);
	
	if (s.isNew()){
		return;
	}
	
	Cookie ca = new Cookie("name1", "value1");
	Cookie cb = new Cookie("name2", "value2");
	

	response.addCookie(ca);
	response.addCookie(cb);




  }

  @Override
  public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {

    User user1 = new User(100,"zoey@imzoee.com","abababab","zoey",10);
    User user2 = new User(101,"jack@imzoee.com","2b2b2b2b","jack",1000);
    User user3 = new User(110,"user@imuser.com","2b2b2b2b","user",500);

    List<User> user = new ArrayList<User>();
    user.add(user1);
    user.add(user2);
    user.add(user3);

	//HttpSession s = request.getSession();
	//s.setMaxInactiveInterval(30);

    PrintWriter out = response.getWriter();
	response.addHeader("Content-Type", "application/json;charset=UTF-8");
	//String ssId = request.getHeader(ConstConv.HEADKEY_SESSIONID);

	String lgAccount = request.getParameter(LoginConv.RESKEY_ACCOUNT);
	String lgPwd = request.getParameter(LoginConv.RESKEY_PWD);

	String status = LoginConv.RET_STATUS_USERNOTEXIST;
	String uName = "login error";

	if(lgAccount != null && lgPwd != null){
	  for (int i = 0; i < user.size(); ++i){
	    if (lgAccount.equals(user.get(i).getAccount())){
		  if (lgPwd.equals(user.get(i).getPwd())){
		    status = LoginConv.RET_STATUS_OK;
			uName = user.get(i).getName();
		  } else{
		    status = LoginConv.RET_STATUS_PWDERR;
		  }
		}
	  }
	}

    JSONObject resObj = new JSONObject();
	resObj.put(LoginConv.RSPKEY_STATUS,status);
	resObj.put(LoginConv.RESKEY_ACCOUNT, uName);
	//resObj.put(ConstConv.HEADKEY_SESSIONID, ssId);
//	String jsonStr = JSON.toJSONString(foo);
    String resStr = JSON.toJSONString(resObj);


	out.println(resStr);

  }

}
