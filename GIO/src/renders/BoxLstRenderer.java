package renders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import gio.gfx.ResourceHandler;
import model.Box;
import screens.CustomPanel;

public class BoxLstRenderer implements ListCellRenderer<Box> {

	private static final String BOX_ICON_PATH = "res/box";
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Box> list, Box value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JPanel panel = new CustomPanel(ResourceHandler.getBackground(), new BorderLayout());
		JPanel subPanel = new JPanel(new GridLayout());
		subPanel.add(createColumn("ID", String.valueOf(value.getId())));
		subPanel.add(createColumn("Peso", String.valueOf(value.getWeight())+"Kg"));
		subPanel.add(createColumn("Recibido en",(value.getDate())));
		subPanel.add(createColumn("Pedido de origen", String.valueOf(value.getOrder_origin())));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,2,10,2),BorderFactory.createLoweredBevelBorder()));

		JLabel icon = new JLabel(new ImageIcon(BOX_ICON_PATH+value.getWeight()+"_64.png"));
		icon.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel.add(icon, BorderLayout.WEST);
		panel.add(subPanel, BorderLayout.CENTER);
		if(isSelected) panel.setBackground(Color.CYAN);
		return panel;
	}

	private Component createColumn(String text, String value) {
		JLabel label = new JLabel(text+": "+value, JLabel.CENTER);
//		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		return label;
	}
	
}
