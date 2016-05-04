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


public class VerifyServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {
	
	String account = request.getParameter(ConstConv.RESKEY_ACCOUNT);
	
	
	if (account == null){
	  response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, 
						ConstConv.RET_STATUS_ACCOUNTNULL);
	  return;
	}
	
	/* check if the account is valid */
	if (!isAccountValid(account)){
	  response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, 
						ConstConv.RET_STATUS_ACCOUNTINVALID);
	  return;
	} else if(isAccountUsed(account)) {
	  response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, 
						ConstConv.RET_STATUS_ACCOUNTREGED);
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
	if(!s.isNew()){
	  s.invalidate();
	  s = request.getSession();
	}
	/* set this session inactive interval */
	s.setMaxInactiveInterval(20*60); // for temporary test,we set it to 20*60, 20 minutes
	
	/* associate the verify code and the user's account,pwd,name, etc with the session */
	s.setAttribute(ConstConv.RESKEY_VERIFYCODE, verifyCode);
	s.setAttribute(ConstConv.RESKEY_ACCOUNT, account);

	/* and dont forget to set the ok status in the header line */
	response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_OK);

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
