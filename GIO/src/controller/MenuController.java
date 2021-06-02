package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import frame.ApplicationFrame;
import screens.StockView;
import screens.Pantalla;

public class MenuController implements ActionListener{

	ApplicationFrame frame;
	
	public MenuController(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "screen-stock":
			frame.changePanel(new StockView(frame));
			break;
		case "screen-users":
			frame.changePanel(new Pantalla());
			break;

		default:
			break;
		}
	}

}
