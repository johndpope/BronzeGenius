package Chat;

import java.util.Date;

public class AddRequest {
	private User fromUser;
	private User toUser;
	private Date date;
	RequestStatus status;
	
	public AddRequest(User from, User to, Date date){}
	public RequestStatus getStatus(){return status;}
	public User getFromUser(){return fromUser;}
	public User getToUser(){return toUser;}
	public Date getDate(){return date;}
}


//hello world from idea 15
