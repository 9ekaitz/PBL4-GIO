package model;

import javax.swing.ImageIcon;

public class Material {

	private String name, id;
	private ImageIcon img;
	private int boxCount[];

	public Material(String id, String name, String path, int[] count) {
		this.id = id;
		this.name = name;
		this.img = new ImageIcon(path);
		this.boxCount = count;
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
	
	public int[] getBoxCount() {
		return boxCount;
	}
}
