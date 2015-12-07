package fr.eseo.os;

public class Link extends Node {
	private Node target;
	
	/**
	 * Creates a new link
	 * @param name the name of the link
	 * @param target the target of the link
	 */
	public Link(String name, Node target){
		this.name = name;
		this.target = target;
	}
	
	public Node getTarget(){
		return this.target;
	}
}
