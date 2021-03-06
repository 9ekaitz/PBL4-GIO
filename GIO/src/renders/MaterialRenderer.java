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

import gfx.ResourceHandler;
import model.stock.Material;
import screens.templates.CustomPanel;

public class MaterialRenderer implements ListCellRenderer<Material> {

	private final static String KG_5 = "box5.png";
	private final static String KG_10 = "box10.png";
	private final static String KG_15 = "box15.png";
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Material> list, Material value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new CustomPanel(ResourceHandler.getBackground(), new BorderLayout(10, 10));
		JPanel leftPanel = new JPanel(new BorderLayout(0, 10));
		JLabel image = new JLabel();
		image.setIcon(value.getIcon());
		leftPanel.add(image, BorderLayout.CENTER);
		panel.setOpaque(false);
		JLabel name = new JLabel(value.getName(), JLabel.CENTER);
		name.setFont(new Font("Arial", Font.PLAIN, 40)); // Para poder usar oliciy hace falta tenerla instalada en el
															// sistema, en el instalador del programa deberia instalarse
		leftPanel.add(name, BorderLayout.SOUTH);

		panel.add(leftPanel, BorderLayout.WEST);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10),
				BorderFactory.createLineBorder(Color.red)));

		JPanel rightPanel = new JPanel(new GridLayout(1, 3, 10, 0));
		rightPanel.add(createBoxView(value, 5, KG_5));
		rightPanel.add(createBoxView(value, 10, KG_10));
		rightPanel.add(createBoxView(value, 15	, KG_15));
		panel.add(rightPanel, BorderLayout.CENTER);

		return panel;
	}

	private Component createBoxView(Material m, int motaId, String path) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel image = new JLabel(new ImageIcon(getClass().getResource("/" + path)));
		JLabel count = null;
		image.setPreferredSize(new Dimension(256,256));
		image.setBorder(null);
		panel.add(image, BorderLayout.CENTER);
		
		count = new JLabel("Cantidad: "+String.valueOf(m.getBoxCount()[motaId/5-1]), JLabel.CENTER);
		count.setFont(new Font("Aral", Font.PLAIN, 24));
	
		panel.add(count, BorderLayout.SOUTH);
		return panel;
	}

}
