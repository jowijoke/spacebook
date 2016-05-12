package models;

import java.util.Date;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Message extends Model
{
  public String subject;
  public String messageText;
  public Date postedAt;

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
    postedAt = new Date();
    pause();
  }
  
  /*
   * Pause briefly to ensure the Date postedAt values are sufficiently different
   * to allow sorting. Here 10 milliseconds are used with effect but this figure
   * may vary on other systems.
   */
  private void pause()
  {
    try
    {
      Thread.sleep(10);
    }
    catch (InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
  }  
  
  
  
  
  
  
}
