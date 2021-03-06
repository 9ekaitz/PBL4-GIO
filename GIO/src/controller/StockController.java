package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.box.BoxVO;
import model.box.BoxDAOImpl;
import model.stock.Material;
import model.stock.MaterialDAO;
import model.stock.MaterialDAOFiltered;
import model.stock.MaterialDAOImpl;
import screens.frame.ApplicationFrame;
import screens.panel.DetailsPanel;
import screens.panel.StockView;

public class StockController implements KeyListener, ActionListener, ListSelectionListener, ItemListener{

	private StockView view;
	private MaterialDAO model;
	private ApplicationFrame frame;
	private DetailsPanel boxView;

	public StockController(StockView stockView, MaterialDAOImpl model, ApplicationFrame frame) {
		this.view = stockView;
		this.model = model;
		this.frame = frame;
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
		String searchTerm = view.getSearch();
		return lst.stream().filter(
				item -> item.getName().toLowerCase().substring(0, searchTerm.length()).equals(searchTerm.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "create-box":
			break;
		case "show-box-10":

			break;
		case "show-box-15":

			break;

		default:
			break;
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) return;
		if (e.getSource() instanceof JList<?>) {
			JList<Material> lst = (JList<Material>) e.getSource();
			Material m = lst.getSelectedValue();
			frame.changePanel(new DetailsPanel(m, this));
		}
	}
	
	public void setBoxView(DetailsPanel view) {
		boxView = view;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		List<BoxVO> allLst = boxView.getModel().getAllBoxes();
		boxView.setModel(new BoxDAOImpl (allLst.stream().filter(b->b.getWeight() == 5 && boxView.is5Checked()
				|| b.getWeight() == 10 && boxView.is10Checked()
				|| b.getWeight() == 15 && boxView.is15Checked()).collect(Collectors.toList())));
	}

}
