package fr.eseo.os;

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
}
