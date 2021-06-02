package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.print.DocFlavor.CHAR_ARRAY;
import javax.swing.JPanel;

import frame.ApplicationFrame;
import model.Material;
import model.MaterialDAO;
import model.MaterialDAOFiltered;
import model.MaterialDAOImpl;
import screens.StockView;

public class StockController implements KeyListener, ActionListener {

	StockView view;
	MaterialDAO model;
	ApplicationFrame frame;

	public StockController(StockView stockView, MaterialDAOImpl model, ApplicationFrame frame) {
		this.view = stockView;
		this.model = model;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (view.getSearch().length() > 0) {
			view.setModel(new MaterialDAOFiltered(createSubLst(model.getMaterialLst())));
		} else {
			if (model != view.getModel())
				view.setModel((MaterialDAOImpl) model);
		}
	}

	private List<Material> createSubLst(List<Material> lst) {
		List<Material> filterdLst = new ArrayList<>();
		String searchTerm = view.getSearch();
		return lst.stream().filter(
				item -> item.getName().toLowerCase().substring(0, searchTerm.length()).equals(searchTerm.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "try":
			frame.changePanel(new JPanel());
			break;
		case "show-box-10":

			break;
		case "show-box-15":

			break;

		default:
			break;
		}
	}

}
