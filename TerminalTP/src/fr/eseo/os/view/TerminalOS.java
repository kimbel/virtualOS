package fr.eseo.os.view;

import fr.eseo.os.*;
import fr.eseo.os.view.LogFrame;

import java.time.Clock;
import java.util.*;

/**
 * TerminalOS is a specialization of the Terminal class.
 * Allows changing the way the textual command are handled
 */
public class TerminalOS extends Terminal implements Observer {
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
		this.user.addObserver(this);

		this.setTitle(user.hashCode()+"@"+user.getLogin());
		this.setUserPrompt(user.getLogin());

		this.copyCommands();

		this.getCommandTA().append(user.getLogin()+PROMPT);

	}

	@Override
	public void update(Observable o, Object arg) {
		this.getCommandTA().append(this.user.getLastHistoric() + '\n');
		this.getCommandTA().append("commande introuvable" + '\n');
		this.getCommandTA().append(user.getLogin() + PROMPT);
	}

	public void copyCommands() {
		for (String command : this.user.getHistoric().getCommands()) {
			this.getCommandTA().append(user.getLogin() + PROMPT + command + '\n');
			this.getCommandTA().append("commande introuvable" + '\n');
		}
	}

	@Override
	public boolean isUserLoggedIn(){
		return userLoggedIn;
	}

	@Override
	public String handleCommand(String command){
		this.user.deleteObserver(this); //To prevent update this PROMPT
		this.user.addCommand(command);
		this.user.addObserver(this);
		return "commande introuvable";
	}

	public static void main(String[] args) {
		LogFrame lf = new LogFrame(false);
		lf.setVisible(true);
	}
}

