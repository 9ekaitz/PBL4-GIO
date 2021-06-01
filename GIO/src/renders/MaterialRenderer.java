package renders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.mysql.cj.protocol.ValueDecoder;
import com.mysql.cj.xdevapi.Schema.Validation;

import connector.DBConnector;
import gio.gfx.ResourceHandler;
import model.Material;
import screens.CustomPanel;

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

		JLabel name = new JLabel(value.getName(), JLabel.CENTER);
		name.setFont(new Font("Arial", Font.PLAIN, 40)); // Para poder usar oliciy hace falta tenerla instalada en el
															// sistema, en el instalador del programa deberia instalarse
		leftPanel.add(name, BorderLayout.SOUTH);

		panel.add(leftPanel, BorderLayout.WEST);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10),
				BorderFactory.createLineBorder(Color.black)));

		JPanel rightPanel = new JPanel(new GridLayout(1, 3, 10, 0));
		rightPanel.add(createBoxView(value.getId(), 5, KG_5));

		return panel;
	}

	private static Component createBoxView(String lehengaiaId, int motaId, String path) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon("res/" + path));
		panel.add(image, BorderLayout.CENTER);
		JLabel count = new JLabel();
		ResultSet rs = DBConnector.executeQuery("SELECT COUNT(kutxaId) AS \"count\" FROM kutxa WHERE motaId = " + motaId
				+ " AND lehengaiaId = \"" + lehengaiaId + "\"");
		try {
			while (rs.next()) {
				count.setText(String.valueOf(rs.getInt("count")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(count, BorderLayout.SOUTH);
		return panel;
	}

}
