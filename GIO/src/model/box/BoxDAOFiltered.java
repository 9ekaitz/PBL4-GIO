package model.box;

import java.util.List;

import javax.swing.AbstractListModel;

import model.stock.Material;

public class BoxDAOFiltered extends AbstractListModel<BoxVO> implements BoxDAO {

	List<BoxVO> lst;

	public BoxDAOFiltered(List<BoxVO> lst) {
		this.lst = lst;
		fireContentsChanged(lst, 0, getSize());
	}

	@Override
	public List<BoxVO> fetchBoxes(Material m) {
		return lst;
	}

	@Override
	public List<BoxVO> getAllBoxes() {
		return lst;
	}

	@Override
	public int getSize() {
		return lst.size();
	}

	@Override
	public BoxVO getElementAt(int index) {
		return lst.get(index);
	}

}
