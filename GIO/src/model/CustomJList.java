package model;

import java.awt.Graphics;

import javax.swing.JList;

import renders.ResourceHandler;

public class CustomJList<E> extends JList<E> {

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(ResourceHandler.getBackground(), 0, 0, null);
		super.paintComponent(g);
		
	}
}
