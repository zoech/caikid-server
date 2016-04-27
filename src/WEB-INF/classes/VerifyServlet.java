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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

import com.imzoee.model.User;
import com.imzoee.convention.ConstConv;
import com.imzoee.convention.VerifyConv;
import com.imzoee.convention.SignupConv;


public class VerifyServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {
	//HttpSession s = request.getSession();
	//s.setMaxInactiveInterval(30);
	
	String account = request.getParameter(VerifyConv.RESKEY_ACCOUNT);
	String pwd = request.getParameter(VerifyConv.RESKEY_PWD);
	String name = request.getParameter(VerifyConv.RESKEY_NAME);
	
	
	/*
	 * check if any of these query parameter is null, and set the result coressponding
	 */
	boolean partialErr = false;
	if (account == null){
	  addResponseStatus(response, VerifyConv.RET_STATUS_ACCOUNTNULL);
	  partialErr = true;
	}
	if (pwd == null){
	  addResponseStatus(response, VerifyConv.RET_STATUS_PWDNULL);
	  partialErr = true;
	}
	if (name == null){
	  addResponseStatus(response, VerifyConv.RET_STATUS_NAMENULL);
	  partialErr = true;
	}
	if (partialErr){
	  return;
	}
	
	
	/* check if the account, name, pwd is valid */
	if (!isAccountValid(account)){
	  addResponseStatus(response, VerifyConv.RET_STATUS_ACCOUNTINVALID);
	  partialErr = true;
	} else {
	  if(isAccountUsed(account)) {
		addResponseStatus(response, VerifyConv.RET_STATUS_ACCOUNTREGED);
		partialErr = true;
	  }
	}
	
	if (!isNameValid(name)){
	  addResponseStatus(response, VerifyConv.RET_STATUS_NAMEINVALID);
	  partialErr = true;
	} else {
	  if(isNameUsed(name)) {
		addResponseStatus(response, VerifyConv.RET_STATUS_NAMEUSED);
		partialErr = true;
	  }
	}
	
	if (!isPwdValid(pwd)){
	  addResponseStatus(response, VerifyConv.RET_STATUS_PWDFOMATERR);
	  partialErr = true;
	}

	if (partialErr){
	  return;
	}
	
	
	

	/* 
	 * if the servlet run arrived here, that means all the 
	 * account, name, pwd are valid and unsed by others. then,
	 * we generate a verify code and send this code to register
	 * user by the phone num,that the account; and then, create
	 * a session for this request, associate the verify code
	 * we just generated with this session.
	 */
	 
	 /* generate verify code */
	String verifyCode = generateVerifyCode();
	
	if (verifyCode == null){
	  return;
	}
	
	/* send the verify code to the user using the account */
	sendVerifyCode(verifyCode);
	
	/* create session */
	HttpSession s = request.getSession();
	/* set this session inactive interval */
	s.setMaxInactiveInterval(2*60); // for temporary test,we set it to 2*60, 2 minutes
	
	/* associate the verify code with the session */
	s.setAttribute(SignupConv.RESKEY_VERIFYCODE, verifyCode);

	/* and dont forget to set the ok status in the header line */
	response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, VerifyConv.RET_STATUS_OK);

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
  
  private static String generateVerifyCode(){
	  // temporary inplement, need further complete
	  return "1234";
  }
  
  
  /*
   * currently we dont know how to send a message to a phone using servlet code
   */
  private static void sendVerifyCode(String code){
	
  }

}
