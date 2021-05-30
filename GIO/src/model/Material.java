package model;

import javax.swing.ImageIcon;

public class Material {

	private String name;
	private ImageIcon img;

	public Material(String name, String path) {
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
}
