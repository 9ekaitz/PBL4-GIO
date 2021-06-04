package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import connector.DBConnector;

public class BoxDAOImpl extends AbstractListModel<Box> implements BoxDAO{

	List<Box> lst;
	
	public BoxDAOImpl(Material m) {
		lst = getAllBoxes(m);
		fireContentsChanged(lst, 0, getSize());
	}

	@Override
	public int getSize() {
		return lst.size();
	}

	@Override
	public Box getElementAt(int index) {
		return lst.get(index);
	}

	@Override
	public List<Box> getAllBoxes(Material m)  {
		List<Box> boxLst = new ArrayList<>();
		
		ResultSet rs = DBConnector.executeQuery("SELECT kutxaId, motaId, jatorrizko_eskaera, e.jasotze_data "
				+ "FROM kutxa k JOIN eskaera_hornitzailea e "
				+ "ON k.jatorrizko_eskaera = e.eskaeraId "
				+ "WHERE lehengaiaId = \""+m.getId()+"\"");
		try {
			while (rs.next()) {
				Box b = new Box(rs.getInt("kutxaId"), rs.getString("jasotze_data"), rs.getInt("motaId"), rs.getInt("jatorrizko_eskaera"));
				boxLst.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boxLst;
	}
	
}