package controllers;

import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

import java.util.*;

import models.*;

public class Profile extends Controller
{
  public static void index()
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    render(user);
  }
  
  public static void changeStatus(String profiletext)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    user.statusText = profiletext;
    user.save();
    Logger.info("Status changed to " + profiletext);
    index();
  } 
  
  public static void getPicture(Long id) 
  {
    User user = User.findById(id);
    Blob picture = user.profilePicture;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }
  
  public static void uploadPicture(Long id, Blob picture)
  {
    User user = User.findById(id);
    user.profilePicture = picture;
    user.save();
    index();
  }   
}