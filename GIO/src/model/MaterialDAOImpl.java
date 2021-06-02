package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import connector.DBConnector;

public class MaterialDAOImpl extends AbstractListModel<Material> implements MaterialDAO{

	List<Material> lst = null;
	
	public MaterialDAOImpl() {
		updateLst();
	}
	
	private void updateLst() {
		if (lst == null) lst = new ArrayList<>();
		else lst.clear();
		ResultSet rs = DBConnector.executeQuery("SELECT * FROM lehengaia ORDER BY deskribapena");
		try {
			while (rs.next()) {
				String id = rs.getString("lehengaiaId");
				String name = rs.getString("deskribapena");
				Blob blob = rs.getBlob("img");
				if (blob != null) {
					 byte byteArray[] = blob.getBytes(1,(int)blob.length());
				     File file = new File("tmp/"+name+".png");
				     FileOutputStream outPutStream = new FileOutputStream(file);
				     outPutStream.write(byteArray);
				     lst.add(new Material(id, name, file.getAbsolutePath()));
				} else lst.add(new Material(id, name, "res/unknown.png"));
		       			}
		} catch (SQLException | IOException e) {
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