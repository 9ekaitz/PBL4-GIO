package model.box;

import java.util.List;

import model.stock.Material;

public interface BoxDAO {
	List<BoxVO> fetchBoxes(Material m);
	List<BoxVO> getAllBoxes();
}
