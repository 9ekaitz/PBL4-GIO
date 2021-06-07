package model;

import java.util.List;

import javax.swing.AbstractListModel;

public class BoxDAOFiltered extends AbstractListModel<Box> implements BoxDAO {

	List<Box> lst;

	public BoxDAOFiltered(List<Box> lst) {
		this.lst = lst;
		fireContentsChanged(lst, 0, getSize());
	}

	@Override
	public List<Box> fetchBoxes(Material m) {
		return lst;
	}

	@Override
	public List<Box> getAllBoxes() {
		return lst;
	}

	@Override
	public int getSize() {
		return lst.size();
	}

	@Override
	public Box getElementAt(int index) {
		return lst.get(index);
	}

}