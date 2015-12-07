package fr.eseo.os.view;

import fr.eseo.os.Folder;
import fr.eseo.os.Historic;
import fr.eseo.os.Terminal;
import fr.eseo.os.User;
import fr.eseo.os.view.LogFrame;

import java.time.Clock;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TerminalOS is a specialization of the Terminal class.
 * Allows changing the way the textual command are handled
 */
public class TerminalOS extends Terminal {
	//private Set<User> users;
	private User user;
	private Folder currentFolder;
	
	/**
	 * Builds a new Terminal frame
	 * @param login the login of the user
	 * @param password the password of the user
	 */
	public TerminalOS(String login, String password){
		super(false);
		this.user = User.getInstance(login, password);
		this.setTitle(user.hashCode()+"@"+user.getLogin());
		this.setUserPrompt(user.getLogin());
		this.updatePrompt(this.user.getHistoric());
		this.getCommandTA().append(user.getLogin()+PROMPT);

	}

	private void updatePrompt(Historic historic) {
		for (Map.Entry<String, String> entry : historic.getCommands().entrySet()) {
			this.getCommandTA().append(user.getLogin() + PROMPT + entry.getKey() + '\n');
			this.getCommandTA().append(entry.getValue() + '\n');
		}
	}
	
	@Override
	public boolean isUserLoggedIn(){
		return userLoggedIn;
	}

	@Override
	public String handleCommand(String command){
		user.getHistoric().getCommands().put(command, "commande introuvable");
		return user.getHistoric().getCommands().get(command);
	}

	public static void main(String[] args) {
		LogFrame lf = new LogFrame(false);
		lf.setVisible(true);
	}
}

