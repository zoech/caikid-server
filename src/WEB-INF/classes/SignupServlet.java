import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.imzoee.model.User;
import com.imzoee.convention.ConstConv;


public class SignupServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {
	
	String account = request.getParameter(ConstConv.RESKEY_ACCOUNT);
	String pwd = request.getParameter(ConstConv.RESKEY_PWD);
	String name = request.getParameter(ConstConv.RESKEY_NAME);
	String resVerifyCode = request.getParameter(ConstConv.RESKEY_VERIFYCODE);
	
	/* check if the session is a signup session */
	HttpSession s = request.getSession(false);
	if(s == null){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_TIMEOUT);
		return;
	}
	
	String verifyCode = (String) s.getAttribute(ConstConv.RESKEY_VERIFYCODE);
	if (verifyCode == null){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_TIMEOUT);
		return;
	}
	
	if(resVerifyCode == null || !resVerifyCode.equals(verifyCode)){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_VERIFYERR);
		return;
	}
	
	
	
	
	/*
	 * check if any of these query parameter is null, and set the result coressponding
	 */
	boolean partialErr = false;
	if (account == null){
	  addResponseStatus(response, ConstConv.RET_STATUS_ACCOUNTNULL);
	  partialErr = true;
	}
	if (pwd == null){
	  addResponseStatus(response, ConstConv.RET_STATUS_PWDNULL);
	  partialErr = true;
	}
	if (name == null){
	  addResponseStatus(response, ConstConv.RET_STATUS_NAMENULL);
	  partialErr = true;
	}
	if (partialErr){
	  return;
	}
	
	
	/* check if the account, name, pwd is valid */
	if (!isAccountValid(account)){
	  addResponseStatus(response, ConstConv.RET_STATUS_ACCOUNTINVALID);
	  partialErr = true;
	} else {
	  if(isAccountUsed(account)) {
		addResponseStatus(response, ConstConv.RET_STATUS_ACCOUNTREGED);
		partialErr = true;
	  }
	}
	
	if (!isNameValid(name)){
	  addResponseStatus(response, ConstConv.RET_STATUS_NAMEINVALID);
	  partialErr = true;
	} else {
	  if(isNameUsed(name)) {
		addResponseStatus(response, ConstConv.RET_STATUS_NAMEUSED);
		partialErr = true;
	  }
	}
	
	if (!isPwdValid(pwd)){
	  addResponseStatus(response, ConstConv.RET_STATUS_PWDERR);
	  partialErr = true;
	}

	if (partialErr){
	  return;
	}
	
	
	/*
	 * if the servlet reach here, it means the signup request is valid, and we
	 * create a new user for this request, and set the status code to ok;
	 */
	registForRequest(s);
	response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_OK);
	
	/* and dont forget to invalidate the session, this session only keep before user signup success */
	s.invalidate();
  }
  
  
  
  /*
   * 为信用户注册；所有账户、昵称、密码信息通过会话属性来获取
   */
  public void registForRequest(HttpSession s){
	  
  }
  
  private static void addResponseStatus(HttpServletResponse response, String status){
	  response.addHeader(ConstConv.HEADKEY_RESPONSTATUS, status);
  }
  
   /*
   * going to be completed, check if the account is an available phone num;
   */
  private static boolean isAccountValid(String account){
	return true; // currently we set this to true, because we now dont know how to check the phone num is valid;
  }
  
  /*
   * going to be completed, check if the account is already registered;
   */
  private static boolean isAccountUsed(String account) {
	  return false;
  }
  
  /*
   * going to be completed, check if the name is an valid string;
   */
  private static boolean isNameValid(String name){
	return true; // currently we set this to true, because we now dont know how to check the phone num is valid;
  }
  
  /*
   * going to be completed, check if the name is already used by another user;
   */
  private static boolean isNameUsed(String name) {
	  return false;
  }
  
  /*
   * going to be completed, check if the pwd is in right format;
   */
  private static boolean isPwdValid(String pwd){
	return true; // currently we set this to true, because we now dont know how to check the phone num is valid;
  }

}
