package Chat;

import java.util.Date;

public class Message {
	private String content;
	private Date date;
	private User fromUser;
	public Message(String content, Date date){}
	public Message(String content, Date date, User fromUser){}
	public String getContent(){return content;}
	public Date getDate(){return date;}
}
