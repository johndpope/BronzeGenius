package Chat;

public class UserStatus {
	private String message;
	private UserStatusType type;
	public UserStatus(UserStatusType type, String message){this.message = message; this.type = type;};
	public UserStatusType getStatusType(){return this.type;};
	public String getMessage(){return this.message;};
}
