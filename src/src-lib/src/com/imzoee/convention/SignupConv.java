package com.imzoee.convention;

/**
 * Created by zoey on 2016/4/24.
 */
public class SignupConv {

    /*
     * RST, result
     */
    public static final String RET_STATUS_OK = "ok";
	public static final String RET_STATUS_VERIFYERR = "verify-unmatch";
	public static final String RET_STATUS_TIMEOUT = "verifyCode-timeout";
	public static final String RET_STATUS_NOTSIGNUPSESSION = "not-signup-session";
    public static final String RET_STATUS_ACCOUNTREGED = "account-registered";
    public static final String RET_STATUS_NAMEUSED = "name-used";
    public static final String RET_STATUS_PWD2SHORT = "pwd-too-short";
    public static final String RET_STATUS_PWDFMTERR = "pwd-format-incorrect";

    /*
     * RES, request
     */
    public static final String RESKEY_ACCOUNT = "account";
    public static final String RESKEY_PWD = "pwd";
    public static final String RESKEY_NAME = "name";
    public static final String RESKEY_TOKEN = "token";          /* session token */
    public static final String RESKEY_VERIFYCODE = "verifycode";   /* verify code */

    /*
     * RSP, response
     */
    public static final String RSPKEY_STATUS = "status";
}
