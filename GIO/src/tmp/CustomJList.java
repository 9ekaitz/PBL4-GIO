package tmp;

import java.awt.Graphics;

import javax.swing.JList;

import gio.gfx.ResourceHandler;

public class CustomJList<E> extends JList<E> {

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(ResourceHandler.getBackground(), 0, 0, null);
		super.paintComponent(g);
		
	}
}