package controllers;

import play.*;
import play.mvc.*;
import utils.MessageDateComparator;
import utils.MessageFromComparator;

import java.util.*;

import models.*;

public class Home extends Controller
{
	public static void index(){
		index("date");
		
		
	}
  public static void index(String sort)
  {
	  try{
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    if( sort !=null){
    switch (sort){
    case "date": Collections.sort(user.inbox, new MessageDateComparator()); 
     break;
     
    case "user": Collections.sort(user.inbox, new MessageFromComparator());
     break;

     default:  Collections.sort(user.inbox, new MessageDateComparator());
    
    }
    }
 
 
    render(user);
	  }
	  catch (NumberFormatException e){
		  Accounts.index();		  
	  }
  }
  
  public static void drop(Long id)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    
    User friend = User.findById(id);
 
    user.unfriend(friend);
    Logger.info("Dropping " + friend.email);
    index();
  } 
}