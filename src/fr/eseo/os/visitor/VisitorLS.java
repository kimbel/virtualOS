package fr.eseo.os.visitor;

import fr.eseo.os.File;
import fr.eseo.os.Folder;
import fr.eseo.os.Link;
import fr.eseo.os.Node;

public class VisitorLS implements VisitorNode {

	@Override
	public String visit(File file) {
		return file.getName() + " - " + file.getSize() + "o";
	}

	@Override
	public String visit(Folder folder) {
		StringBuffer sb = new StringBuffer("[" + folder.getName() + ":\n");
		for (Node node : folder.getChildren()) {
			sb.append(node.accept(this));
			if (folder.getChildren().indexOf(node) < folder.getChildren().size() - 1) {
				sb.append("\n");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String visit(Link link) {
		return link.getName() + " -> " + link.getTarget().getName();
	}

}
