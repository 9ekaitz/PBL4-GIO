package tmp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Material;
import model.MaterialDAOImpl;
import renders.MaterialRenderer;

public class StockView extends JPanel {

	JList<Material> list;
	MaterialDAOImpl model;

	public StockView() {
		this.setLayout(new BorderLayout());
		createTopBar();
		model = new MaterialDAOImpl();
		createMaterialView();
	}

	private void createMaterialView() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		list = new JList<>();
		list.setModel(model);
		list.setCellRenderer(new MaterialRenderer());
		panel.setViewportView(list);
		
		this.add(panel, BorderLayout.CENTER);
	}

	private void createTopBar() {
		JPanel bar = new JPanel();
		BoxLayout b = new BoxLayout(bar, BoxLayout.X_AXIS);
		bar.setLayout(b);

		JButton addMaterial = new JButton("Add material");
		addMaterial.setPreferredSize(new Dimension(100, 50));

		bar.add(addMaterial);
		bar.add(Box.createHorizontalGlue());
		this.add(bar, BorderLayout.NORTH);
	}
}
