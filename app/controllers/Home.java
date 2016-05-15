package controllers;

import play.*;
import play.mvc.*;
import utils.MessageDateComparator;
import utils.MessageFromComparator;

import java.util.*;

import models.*;

public class Home extends Controller {
	public static void index() {
		index("date");

	}

	public static void index(String sort) {
		try {
			String userId = session.get("logged_in_userid");
			User user = User.findById(Long.parseLong(userId));
			if (sort != null) {
				switch (sort) {
				case "date":
					Collections.sort(user.inbox, new MessageDateComparator());
					Logger.info("Sorting Messages by date ");
					break;

				case "user":
					Collections.sort(user.inbox, new MessageFromComparator());
					Logger.info("Sorting Messages by user ");
					break;

				case "con":
					List<List<Message>> conversations = byConversation(user);
					render(user, conversations);
					Logger.info("Sorting Messages by conversation ");

					break;

				default:
					Collections.sort(user.inbox, new MessageDateComparator());
					Logger.info("Sorting Messages by default ");

				}
			}

			render(user);
		} catch (NumberFormatException e) {
			Accounts.index();
		}
	}

	public static void drop(Long id) {
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));

		User friend = User.findById(id);

		user.unfriend(friend);
		Logger.info("Dropping " + friend.email);
		index();
	}

	public static List<List<Message>> byConversation(User user) {
		/*
		 * Create a list of list of messages Each list of messages is a single
		 * conversation A conversation takes place between 2 users
		 * 
		 * loop for all of param user friends create list of messages comprising
		 * conversation with this friend add list of messages (conversation) to
		 * list of conversations end loop render conversations in view
		 */

		List<List<Message>> conversations = new ArrayList<List<Message>>();
		{
			List<User> friends = new ArrayList<User>();
			for (Friendship f : user.friendships) {
				User source = f.sourceUser;
				if (!source.equals(user) && !friends.contains(source)) {
					friends.add(source);
				}

				User target = f.targetUser;
				if (!target.equals(user) && !friends.contains(target)) {
					friends.add(target);
				}
			}
			for (User friend : friends) {

				conversations.add(getConversation(user, friend));

			}
		}
		return conversations;
	}

	/**
	 * Generates a conversation comprising a list of messages between two users.
	 * The list is sorted by reference to date and time. Recall that a message
	 * object contains an instance of Date object
	 * 
	 * @param user
	 *            the user who initiates the conversation
	 * @param friend
	 *            the user with whom the initiator is having a conversation
	 * @return the list of messages which makes up the conversation
	 */
	static List<Message> getConversation(User user, User friend) {
		/*
		 * Create a list of messages to represent a conversation loop through
		 * user's outbox if the user to whom message sent is a friend then add
		 * the message to the conversation endif loop through user's inbox if
		 * the message is from a friend add the message to the conversation
		 * endif Sort the conversation by date return the conversation
		 */
		List<Message> conversation = new ArrayList<Message>();

		List<Message> inbox = user.inbox;
		for (Message msg1 : inbox) {
			if (msg1.from.equals(friend)) {
				conversation.add(msg1);
			}

		}

		List<Message> outbox = user.outbox;
		for (Message msg2 : outbox) {
			if (msg2.to.equals(friend)) {
				conversation.add(msg2);
			}

		}
		Collections.sort(conversation, new MessageDateComparator());
		return conversation;
	}
}