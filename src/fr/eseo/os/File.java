package fr.eseo.os;

import fr.eseo.os.visitor.VisitorNode;

public class File extends Node {

	private int size;
	private String data;
	
	/**
	 * Creates a new file with data
	 * @param name the name of the new file
	 * @param data the data of the new file
	 */
	public File(String name, String data) {
		this.name = name;
		this.data = data;
		this.size = data.length()*8;
	}
	
	public int getSize(){
		return this.size;
	}

	public String getData() {
		return this.data;
	}

	@Override
	public String accept(VisitorNode vn) {
		return vn.visit(this);
	}
}
