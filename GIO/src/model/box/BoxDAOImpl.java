package model.box;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import connector.DBConnector;
import model.stock.Material;

public class BoxDAOImpl extends AbstractListModel<BoxVO> implements BoxDAO{

	List<BoxVO> lst;
	
	public BoxDAOImpl(Material m) {
		lst = fetchBoxes(m);
		fireContentsChanged(lst, 0, getSize());
	}
	
	public BoxDAOImpl(List<BoxVO> lst) {
		this.lst = lst;
		fireContentsChanged(lst, 0, getSize());
	}

	@Override
	public int getSize() {
		return lst.size();
	}

	@Override
	public BoxVO getElementAt(int index) {
		return lst.get(index);
	}

	@Override
	public List<BoxVO> fetchBoxes(Material m)  {
		List<BoxVO> boxLst = new ArrayList<>();
		
		ResultSet rs = DBConnector.executeQuery("SELECT kutxaId, motaId, jatorrizko_eskaera, e.jasotze_data "
				+ "FROM kutxa k JOIN eskaera_hornitzailea e "
				+ "ON k.jatorrizko_eskaera = e.eskaeraId "
				+ "WHERE lehengaiaId = \""+m.getId()+"\"");
		try {
			while (rs.next()) {
				BoxVO b = new BoxVO(rs.getInt("kutxaId"), rs.getString("jasotze_data"), rs.getInt("motaId"), rs.getInt("jatorrizko_eskaera"));
				boxLst.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boxLst.sort((b1,b2)->b1.getWeight()-b2.getWeight());
		return boxLst;
	}

	@Override
	public List<BoxVO> getAllBoxes() {
		return lst;
	}
}
