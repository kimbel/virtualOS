package fr.eseo.os.view;

import fr.eseo.os.Node;
import fr.eseo.os.User;
import fr.eseo.os.command.Command;

import javax.swing.*;

import static fr.eseo.os.command.CommandEnum.CAT;
import static fr.eseo.os.command.CommandEnum.LS;

public class ExplorerOS extends Explorer {
	
	private User user;

	private Command commandCAT;
	private Command commandLS;

	/**
	 * Creates an new graphical explorer
	 * @param parent the frame that creates this dialog
	 * @param user the user associated to this explorer
	 */
	public ExplorerOS(JFrame parent, User user){
		super(parent);
		this.user = user;
		updateContents();
	}

	@Override
	protected String runCommand(String... args) {
		String result = "";

		// followed by the parameters of the command
		if (args.length > 0) {
			if (args[0].equals(LS.getCommand()) && this.commandLS != null) {
				result = this.commandLS.execute(args);
			}

			if (args[0].equals(CAT.getCommand()) && this.commandCAT != null) {
				result = this.commandCAT.execute(args);
			}
		}

		updateContents();
		return result;
	}
	
	public void updateContents() {
		this.contents.clear();
		for(Node n : user.getHomeDir().getChildren()) {
			this.addContents(n.getName(), n.getClass().getSimpleName());
		}
		this.repaint();
	}

	public void setCommandCAT(Command commandCAT) {
		this.commandCAT = commandCAT;
	}

	public void setCommandLS(Command commandLS) {
		this.commandLS = commandLS;
	}
}
