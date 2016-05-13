package utils;

import java.util.Comparator;

import models.Message;

public class MessageDateComparator implements Comparator<Message>
{
  /**
   * compare the time-date attributes of each message use the Date compareTo
   * method use appropriate attribute of Message b as the parameter
   */

  @Override
  public int compare(Message a, Message b)
  {
    return a.postedAt.compareTo(b.postedAt);

  }
}
