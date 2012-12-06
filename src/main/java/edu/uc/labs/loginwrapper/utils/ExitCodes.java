package edu.uc.labs.loginwrapper.utils;

public enum ExitCodes {
	SUCCESS(0, null), CONNECTION_FAILED(1,
			"Unable to connect to the Connection Broker"), LOGIN_FAILED(2,
			"Failed to Login"), DESKTOP_FAILED_TO_START(3,
			"Desktop failed to start"), RDP_FAILED_TO_START(4,
			"Remote desktop failed to start"), RDP_OPERATION_FAILED(5,
			"Remote desktop operation failed"), TUNNEL_CONNECTION_LOST(6,
			"Tunnel connection was lost"), UNKNOWN_RESULT_DURING_AUTHENTICATION(
			11, "Unknown result during authentication"), AUTHENTICATION_ERROR(
			12, "Authentication Error"), UNKNOWN_AUTHENTICATION_METHOD(13,
			"Unknown authentication method used"), INVALID_SERVER_RESPONSE(14,
			"Invalid server response"), DESKTOP_DISCONNECTED(15,
			"Desktop was disconnected"), TUNNEL_DISCONNECTED(16,
			"Tunnel was disconnected"), RMKS_DISCONNECTED(20,
			"Remote Mouse, Keyboard and screen were disconnected"), PIN_ERROR(
			21, "PIN Error"), PIN_MISMATCH(22, "PIN Mismatch"), PASSWORD_MISMATCH(
			23, "Password Mismatch"), CONNECTION_SERVER_ERROR(24,
			"View Connection Server error"), DESKTOP_UNAVAILABLE(25,
			"Desktop was unavailable");

	private int value;
	private String error_msg;

	private ExitCodes(int value, String error_msg) {
		this.value = value;
		this.error_msg = error_msg;
	}

	public int getCode() {
		return value;
	}

	public String getError() {
		return error_msg;
	}

}
