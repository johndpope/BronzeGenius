package Chat;

import java.util.HashMap;

/* UserManager serves as a central place for core user actions */
public class UserManager {
	private static UserManager instance;
	
	/* maps from a user id to a user */
	private HashMap<Integer, User> usersById;
	
	/* maps from the user id to an online user */
	private HashMap<Integer, User> onlineUsers;
	
	public static UserManager getInstance()
	{
		if(instance == null) instance = new UserManager();
		return instance;
	}
	
	public void addUser(User fromUser, String toAccountName){}
	public void approveAddRequest(AddRequest req){}
	public void rejectAddRequest(AddRequest req){}
	public void userSignedOn(String accountName){}
	public void userSignedOff(String accountName){}
}