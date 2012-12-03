package edu.uc.labs.loginwrapper.utils;

public enum ExitCodes {
	SUCCESS (0, null),
	CONNECTION_FAILED (1, "Unable to connect to the Connection Broker"),
	LOGIN_FAILED(2, "Failed to Login"),
	DESKTOP_FAILED_TO_START(3, "Desktop failed to start"),
	RDP_FAILED_TO_START(4, "Remote desktop failed to start"),
	RDP_OPERATION_FAILED(5, "Remote desktop operation failed"),
	TUNNEL_CONNECTION_LOST(6, "Tunnel connection was lost"),
	UNKNOWN_RESULT_DURING_AUTHENTICATION(11, "Unknown result during authentication");
	
	
	
	
	private int value;
	private String error_msg;

    private ExitCodes(int value, String error_msg) {
    	this.value = value;
    	this.error_msg = error_msg;
    }
    
    public int getCode(){
    	return value;
    }
    
    public String getError(){
    	return error_msg;
    }

}
