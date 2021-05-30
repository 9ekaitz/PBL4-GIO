package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondo extends JPanel{

	private static final long serialVersionUID = 1L;
	
	ImageIcon icon;
	
	public PanelFondo(ImageIcon icon) {
		super();
		this.icon = icon;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D gr = (Graphics2D) g;
		
		
		if (this.icon != null) {
			gr.drawImage(this.icon.getImage(), 0, 0, null);
		} else {
			gr.setColor(Color.WHITE);
			gr.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	
	

}
