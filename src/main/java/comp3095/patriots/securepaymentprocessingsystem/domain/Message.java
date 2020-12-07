/********************************************************************************
 * Project: secure-payment-processing-system
 * Assignment: assignment 3
 * Author(s): Lasse Ken Berantzino
 * Student Number: 101326867
 * Date: 06/12/2020
 * Description: Model and Enitity class for messages (support)
 **********************************************************************************/

package comp3095.patriots.securepaymentprocessingsystem.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date dateAdded = new Date();
	private String subject;
	private String content;

	@ManyToOne
	@JoinColumn(name = "sender")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiver")
	private User receiver;

	//TODO: Add isRead bool
	private boolean read = false;

	public Message() {
	}

	public Message(String subject, String content, User sender) {
		this.subject = subject;
		this.content = content;
		this.sender = sender;
	}

	public Message(String subject, String content, User sender, User receiver) {
		this.subject = subject;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	@Override
	public String toString() {
		return "Message:\n" +
				"id=" + id +
				"\ndateAdded=" + dateAdded +
				"\nsubject='" + subject + '\'' +
				"\ncontent='" + content + '\'' +
				"\nsender=" + sender +
				"\nreceiver=" + receiver +
				"\nread=" + read;
	}
}
