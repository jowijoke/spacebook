package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Message extends Model
{
  public String subject;
  public String messageText;

  @ManyToOne
  public User from;

  @ManyToOne
  public User to;
  
  public Message(User from, User to, String subject, String messageText)
  {
    this.from = from;
    this.to = to;
    this.subject = subject;
    this.messageText = messageText;
  }
}
