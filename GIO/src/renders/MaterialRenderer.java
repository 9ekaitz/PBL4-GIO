package renders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import model.Material;

public class MaterialRenderer implements ListCellRenderer<Material>{
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Material> list, Material value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel(new BorderLayout(10,10));
		JPanel subPanel = new JPanel(new BorderLayout(0,10));
		JLabel image = new JLabel();
		
		image.setIcon(value.getIcon());
		subPanel.add(image, BorderLayout.CENTER);
		
		JLabel name = new JLabel(value.getName(), JLabel.CENTER);
		name.setFont(new Font("Arial", Font.PLAIN, 40));		//Para poder usar oliciy hace falta tenerla instalada en el sistema, en el instalador del programa deberia instalarse
		subPanel.add(name, BorderLayout.SOUTH);
		
		panel.add(subPanel, BorderLayout.WEST);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20,10,20,10), BorderFactory.createLineBorder(Color.black)));
		return panel;
	}

}
