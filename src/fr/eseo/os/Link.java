package fr.eseo.os;

import fr.eseo.os.visitor.VisitorNode;

public class Link extends Node {

	private Node target;
	
	/**
	 * Creates a new link
	 * @param name the name of the link
	 * @param target the target of the link
	 */
	public Link(String name, Node target) {
		this.name = name;
		this.target = target;
	}
	
	public Node getTarget(){
		return this.target;
	}

	@Override
	public String accept(VisitorNode vn) {
		return vn.visit(this);
	}
}
