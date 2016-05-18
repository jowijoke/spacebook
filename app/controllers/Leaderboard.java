//package controllers;
//
//import play.*;
//import play.mvc.*;
//import utils.MessageDateComparator;
//import utils.MessageFromComparator;
////import utils.UserSocialComparator;
//import utils.UserTalkativeComparator;
//
//import java.util.*;
//
//import models.*;
//
//public class Leaderboard extends Controller {
//
//	public static void index() {
//		index(2);
//
//	}
//
//	public static void index(int sort) {
//
//		List<User> users = User.findAll();
//
//		if (sort != 1) {
//			switch (sort) {
//			case 1:
////				Collections.sort(users, new UserSocialComparator());
//				Logger.info("Sorting Leadaeboard by social users ");
//				break;
//
//			case 2:
//				Collections.sort(users, new UserTalkativeComparator());
//				Logger.info("Sorting Leadaeboard by talkative users ");
//				break;
//
//			default:
////				Collections.sort(users, new UserSocialComparator());
//				Logger.info("Sorting Leadaeboard by default ");
//
//			}
//		}
//
//		render(users);
//	}
//}
