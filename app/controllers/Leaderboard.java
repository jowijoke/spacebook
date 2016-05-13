package controllers;

import play.*;
import play.mvc.*;
import utils.MessageDateComparator;
import utils.MessageFromComparator;
import utils.UserSocialComparator;
import utils.UserTalkativeComparator;

import java.util.*;

import models.*;

public class Leaderboard extends Controller {

	public static void index() {
		index("social");

	}

	public static void index(String sort) {

		List<User> users = User.findAll();

		if (sort != null) {
			switch (sort) {
			case "social":
				Collections.sort(users, new UserSocialComparator());
				break;

			case "talkative":
				Collections.sort(users, new UserTalkativeComparator());
				break;

			default:
				Collections.sort(users, new UserSocialComparator());

			}
		}

		render(users);
	}
}
