package com.imzoee.convention;

/**
 * Created by zoey on 2016/4/23.
 */
public class ConstConv {
    /* the server ip */
    public static final String API_URL = "http://110.64.86.208:8080/caikid/";

    //public static final String HEADKEY_SESSIONID = "Caikid-SessionID";
    public static final String HEADKEY_RESPONSTATUS = "Caikid-ResponseStatus";

    public static final String HEADKEY_ACCOUNT = "caikid-account";
    public static final String HEADKEY_ID = "caikid-id";
    public static final String HEADKEY_PWD = "caikid-pwd";
	
	
	
	/* request key */
	public static final String RESKEY_ID = "id";
	public static final String RESKEY_ACCOUNT = "account";
	public static final String RESKEY_NAME = "name";
	public static final String RESKEY_PWD = "pwd";
	public static final String RESKEY_VERIFYCODE = "verify-code";
	
	
	/* response status code */
	public static final String RET_STATUS_SESSIONNOTEXIST = "session-not-exist";
	public static final String RET_STATUS_OK = "ok";
	public static final String RET_STATUS_IDNULL = "id-null";
	public static final String RET_STATUS_ACCOUNTNULL = "account-null";
	public static final String RET_STATUS_NAMENULL = "name-null";
	public static final String RET_STATUS_PWDNULL = "pwd-null";
	public static final String RET_STATUS_ACCOUNTREGED = "account-registered";
	public static final String RET_STATUS_ACCOUNTINVALID = "account-invalid";
	public static final String RET_STATUS_NAMEUSED = "name-used";
	public static final String RET_STATUS_NAMEINVALID = "name-invalid";
	public static final String RET_STATUS_PWDERR = "pwd-error";
	public static final String RET_STATUS_VERIFYERR = "verify-error";
	public static final String RET_STATUS_TIMEOUT = "verify-timeout";
	public static final String RET_STATUS_NOTSIGNUPSESSION = "not-signup-session";
	public static final String RET_STATUS_RELOGIN = "relogin";
	public static final String RET_STATUS_USERNOTEXIST = "user-not-exist";

    /*
     * following are some key or value used to debug. These fileds will be
     * remove after the app has completed.
     */
    public static final String RESKEY_TEST1 = "test-request-key1";
    public static final String RESKEY_TEST2 = "test-request-key2";
    public static final String RESKEY_TEST3 = "test-request-key3";

    public static final String HEADKEY_TEST1 = "test-header-key1";
    public static final String HEADKEY_TEST2 = "test-header-key2";
    public static final String HEADKEY_TEST3 = "test-header-key3";
}
