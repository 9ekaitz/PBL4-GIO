package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class MaterialDAOFiltered extends AbstractListModel<Material> implements MaterialDAO{

	List<Material> lst;

	public MaterialDAOFiltered(List<Material> lst) {
		this.lst = new ArrayList<>(lst);
		fireContentsChanged(lst, 0, getSize());
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
	public void createMaterial(Material material) {
	}

	@Override
	public List<Material> getMaterialLst() {
		return lst;
	}

}
