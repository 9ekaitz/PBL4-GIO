package screens;

import java.awt.Graphics;

import javax.swing.JScrollPane;

import renders.ResourceHandler;

public class CustomScrollPanel extends JScrollPane {

	public CustomScrollPanel(int verticalScrollbar, int horizontalScrollbar) {
		super(verticalScrollbar, horizontalScrollbar);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ResourceHandler.getBackground(), 0, 0, null);
	}

}
