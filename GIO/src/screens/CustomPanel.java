package screens;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class CustomPanel extends JPanel {

	Image bg;
	
	public CustomPanel(Image bg, LayoutManager layout) {
		super(layout);
		this.bg = bg;
	}
	
	public CustomPanel(Image bg) {
		this.bg = bg;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (bg != null) {
			g.drawImage(bg, 0, 0, null);
		}
	}

}
