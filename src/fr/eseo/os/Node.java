package fr.eseo.os;

import fr.eseo.os.visitor.VisitorNode;

public abstract class Node {

	protected String name;
	protected Node parent;

	public String getName() {
		return name;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public void setParent(Node n){
		this.parent = n;
	}
	
	public String toString(){
		return this.name;
	}

	public abstract String accept(VisitorNode vn);
}
