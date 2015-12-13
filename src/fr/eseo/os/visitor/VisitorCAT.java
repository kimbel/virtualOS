package fr.eseo.os.visitor;

import fr.eseo.os.File;
import fr.eseo.os.Folder;
import fr.eseo.os.Link;

public class VisitorCAT implements VisitorNode {

	@Override
	public String visit(File file) {
		return file.getData();
	}

	@Override
	public String visit(Folder folder) {
		return "Command not allowed on a folder.";
	}

	@Override
	public String visit(Link link) {
		return link.getTarget().accept(this);
	}

}
