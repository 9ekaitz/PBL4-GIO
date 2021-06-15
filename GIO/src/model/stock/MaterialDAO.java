package model.stock;

import java.util.List;

public interface MaterialDAO {

	void createMaterial(Material material);
	List<Material> getMaterialLst();
	
}
