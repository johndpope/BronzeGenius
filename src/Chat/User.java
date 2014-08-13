package Chat;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
	private int id;
	private UserStatus status = null;
	
	/* maps from the other participant's user id to the chat */
	private HashMap<Integer, PrivateChat> privateChats;
	
	/* maps from the group chat id to the group chat */
	private ArrayList<GroupChat> groupChats;
	
	/* maps from the other person's user id to the add request */
	private HashMap<Integer, AddRequest> sentAddRequests;
	
	/* maps from the user id to the add request */
	private HashMap<Integer, User> contacts;
	
	private String accountName;
	private String fullName;
	
	public User(int id, String accountName, String fullName){}
	public boolean sendMessageToUser(User to, String content){return false;}
	public boolean sendMessageToGroupChat(int id, String content){return false;}
	public void setStatus(UserStatus status){}
	public UserStatus getStatus(){return status;}
	public boolean addContact(User user){return false;}
	public void receiveAddRequest(AddRequest req){}
	public void sentAddRequest(AddRequest req){}
	public void removeAddRequest(AddRequest req){}
	public void requestAddUser(String accountName){}
	public void addConversation(PrivateChat conversation){}
	public void addConversation(GroupChat conversation){}
	public int getId(){return id;}
	public String getAccountName(){return accountName;}
	public String getFullName(){return fullName;}
	
}






















