package fr.eseo.os.view;

import fr.eseo.os.*;
import fr.eseo.os.command.Command;
import fr.eseo.os.proxy.*;

import java.util.*;

import static fr.eseo.os.command.CommandEnum.*;

/**
 * TerminalOS is a specialization of the Terminal class.
 * Allows changing the way the textual command are handled
 */
public class TerminalOS extends Terminal implements Observer {

	private User user;
	private Folder currentFolder;

	private Command commandCAT;
	private Command commandLS;
	private Command commandTOUCH;
	private Command commandRM;
	private Command commandMKDIR;
	private Command commandLN;

	/**
	 * Builds a new Terminal frame
	 * @param login the login of the user
	 * @param password the password of the user
	 */
	public TerminalOS(String login, String password, String access){
		super(true);

		this.user = User.getInstance(login, password, access);

		this.commandLS = new ProxyCommandLS(this.user);
		this.commandCAT = new ProxyCommandCAT(this.user);
		this.commandTOUCH = new ProxyCommandTOUCH(this.user);
		this.commandRM = new ProxyCommandRM(this.user);
		this.commandMKDIR = new ProxyCommandMKDIR(this.user);
		this.commandLN = new ProxyCommandLN(this.user);

		this.explorer = new ExplorerOS(this, this.user);

		((ExplorerOS)this.explorer).setCommandLS(this.commandLS);
		((ExplorerOS)this.explorer).setCommandCAT(this.commandCAT);
		((ExplorerOS)this.explorer).setCommandTOUCH(this.commandTOUCH);
		((ExplorerOS)this.explorer).setCommandRM(this.commandRM);
		((ExplorerOS)this.explorer).setCommandMKDIR(this.commandMKDIR);
		((ExplorerOS)this.explorer).setCommandLN(this.commandLN);

		this.setTitle(user.hashCode() + "@" + user.getLogin());
		this.setUserPrompt(user.getLogin());
		this.getCommandTA().append(user.getLogin() + PROMPT);

		this.fillHistory();

		this.user.addObserver(this);
	}

	@Override
	public void update(Observable o, Object caller) {
		if (caller != this) {
			this.getCommandTA().append(this.user.getHistories().getLast());
		}
	}

	/**
	 * Fill-in the terminal with history if it exists
	 */
	private void fillHistory() {
		for(String historic : this.user.getHistories()){
			getCommandTA().append(historic);
		}
	}

	@Override
	public boolean isUserLoggedIn(){
		return userLoggedIn;
	}

	@Override
	public String handleCommand(String command){
		String[] args = command.split(" ");
		String result = ((ExplorerOS)this.explorer).runCommand(args);
		// Store the command and the result in the history of the user
		this.user.addHistory(command + '\n' + result + '\n' + user.getLogin() + PROMPT, this);
		return result;
	}

	public static void main(String[] args) {
		LogFrame lf = new LogFrame(true);
		lf.setVisible(true);
	}
}

