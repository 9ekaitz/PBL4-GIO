package model.stock;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import connector.DBConnector;

public class MaterialDAOImpl extends AbstractListModel<Material> implements MaterialDAO{

	private PreparedStatement pStmt;
	private List<Material> lst = null;
	private static final String QUERY = "SELECT COUNT(kutxaId) AS \"count\" FROM kutxa WHERE motaId = ? AND lehengaiaId = ?";
	
	public MaterialDAOImpl() {
		pStmt = DBConnector.getPreparedStatement(QUERY);
		updateLst();
	}
	
	private void updateLst() {
		ResultSet rs;
		ResultSet rs2;
		PreparedStatement aux;
		if (lst == null) lst = new ArrayList<>();
		else lst.clear();
		rs = DBConnector.executeQuery("SELECT * FROM lehengaia ORDER BY deskribapena");
		
		try {
			while (rs.next()) {
				String id = rs.getString("lehengaiaId");
				String name = rs.getString("deskribapena");
				Blob blob = rs.getBlob("img");
				int[] boxCount = new int[3];
				for (int i = 0; i<3; i++) {
					aux = pStmt;
					aux.setInt(1, 5+i*5);
					aux.setString(2, id);
					rs2 = DBConnector.executeQuery(aux);
					while (rs2.next()) {
						boxCount[i] = rs2.getInt("count");
					}
				}
				if (blob != null) {
					 byte byteArray[] = blob.getBytes(1,(int)blob.length());
				     lst.add(new Material(id, name, new ImageIcon(byteArray), boxCount));
				} else lst.add(new Material(id, name, new ImageIcon(getClass().getResource("/unknown.png")), boxCount));
		       			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lst); //TODO remove line
		fireContentsChanged(lst, 0, lst.size());
	}

	@Override
	public void createMaterial(Material material) {
		lst.add(material);
		DBConnector.executeQuery("");
		fireContentsChanged(lst, 0, lst.size());
	}

	@Override
	public int getSize() {
		return lst.size();
	}

	@Override
	public Material getElementAt(int index) {
		return lst.get(index);
	}

	@Override
	public List<Material> getMaterialLst() {
		return lst;
	}

}
