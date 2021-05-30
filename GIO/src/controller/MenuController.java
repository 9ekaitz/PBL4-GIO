package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frame.ApplicationFrame;
import tmp.StockView;

public class MenuController implements ActionListener{

	ApplicationFrame frame;
	
	public MenuController(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "screen-stock":
			frame.changePanel(new StockView());
			break;

		default:
			break;
		}
	}

}
