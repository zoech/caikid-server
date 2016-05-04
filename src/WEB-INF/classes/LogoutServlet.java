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

import com.imzoee.model.User;
import com.imzoee.convention.ConstConv;


public class LogoutServlet extends HttpServlet {
	
	
  private static final int STATUS_USERNOTEXIT = -1;
  private static final int STATUS_PWDERR = -2;

  @Override
  public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
			throws IOException, ServletException
  {

   
	
	//response.addHeader("Content-Type", "application/json;charset=UTF-8");

	String account = request.getHeader(ConstConv.HEADKEY_ACCOUNT);
	int resId = request.getIntHeader(ConstConv.HEADKEY_ID);
	
	if(account == null){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_ACCOUNTNULL);
		return;
	}
	
	if(resId == -1){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_PWDNULL);
		return;
	}
	
	
	HttpSession s = request.getSession(false);
	if(s == null){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_SESSIONNOTEXIST);
		return;
	}
	
	int id = (int) s.getAttribute(ConstConv.RESKEY_ID);
	if(id != resId){
		response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_SESSIONNOTEXIST);
		return;
	}
	
	response.setHeader(ConstConv.HEADKEY_RESPONSTATUS, ConstConv.RET_STATUS_OK);
	s.invalidate();
  }

}
