package model;

import javax.swing.ImageIcon;

public class Material {

	private String name, id;
	private ImageIcon img;

	public Material(String id, String name, String path) {
		this.id = id;
		this.name = name;
		this.img = new ImageIcon(path);
	}

	@Override
	public String toString() {
		return name;
	}
	
	public ImageIcon getIcon() {
		return img;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
}