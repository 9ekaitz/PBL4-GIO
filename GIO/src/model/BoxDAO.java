package model;

import java.util.List;

import model.stock.Material;

public interface BoxDAO {
	List<Box> fetchBoxes(Material m);
	List<Box> getAllBoxes();
}
