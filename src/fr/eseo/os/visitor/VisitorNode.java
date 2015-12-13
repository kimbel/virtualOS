package fr.eseo.os.visitor;

import fr.eseo.os.File;
import fr.eseo.os.Folder;
import fr.eseo.os.Link;

public interface VisitorNode {

	String visit(File file);
	String visit(Folder folder);
	String visit(Link link);
}
