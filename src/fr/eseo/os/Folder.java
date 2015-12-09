package fr.eseo.os;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Node {
	private List<Node> children;
	
	/**
	 * Creates a new empty folder
	 * @param name the name of the folder
	 */
	public Folder(String name){
		this.name = name;
		this.children = new ArrayList<Node>();
	}
	
	/**
	 * Creates a new folder with children
	 * @param name the name of the folder
	 * @param children the children associated with this folder
	 */
	public Folder(String name, List<Node> children){
		this(name);
		this.children.addAll(children);
	}
	
	/**
	 * Adds a new child to the current folder
	 * @param n the child to add
	 */
	public void addChild(Node n){
		this.children.add(n);
		n.setParent(this);
	}
	
	/**
	 * Returns the children of the folder
	 * @return a list of objects
	 */
	public List<Node> getChildren(){
		return this.children;
	}
}
