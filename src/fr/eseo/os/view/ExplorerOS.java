package fr.eseo.os.view;

import fr.eseo.os.Node;
import fr.eseo.os.User;
import fr.eseo.os.command.Command;
import fr.eseo.os.command.CommandEnum;

import javax.swing.*;

import static fr.eseo.os.command.CommandEnum.NOT_FOUND;

public class ExplorerOS extends Explorer {
	
	private User user;

	private Command commandCAT;
	private Command commandLS;
	private Command commandTOUCH;
	private Command commandRM;
	private Command commandMKDIR;
	private Command commandLN;

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
		String result = NOT_FOUND.getCommand();
		// followed by the parameters of the command
		if (args.length > 0) {
			CommandEnum commandEnum = CommandEnum.getEnum(args[0]);
			switch (commandEnum) {
				case LS:
					if (this.commandLS != null) {
						result = this.commandLS.execute(args);
					}
					break;
				case CAT:
					if (this.commandCAT != null) {
						result = this.commandCAT.execute(args);
					}
					break;
				case TOUCH:
					if (this.commandTOUCH != null) {
						result = this.commandTOUCH.execute(args);
					}
					break;
				case MKDIR:
					if (this.commandMKDIR != null) {
						result = this.commandMKDIR.execute(args);
					}
					break;
				case RM:
					if (this.commandRM != null) {
						result = this.commandRM.execute(args);
					}
					break;
				case LN:
					if (this.commandLN != null) {
						result = this.commandLN.execute(args);
					}
					break;
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

	public void setCommandTOUCH(Command commandTOUCH) {
		this.commandTOUCH = commandTOUCH;
	}

	public void setCommandRM(Command commandRM) {
		this.commandRM = commandRM;
	}

	public void setCommandMKDIR(Command commandMKDIR) {
		this.commandMKDIR = commandMKDIR;
	}

	public void setCommandLN(Command commandLN) {
		this.commandLN = commandLN;
	}
}
