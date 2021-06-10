package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import screens.StockView;
import screens.ApplicationFrame;
import screens.PersonalView;

public class MenuController implements ActionListener{

	private ApplicationFrame frame;
	
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
			frame.changePanel(new PersonalView());
			break;

		default:
			break;
		}
	}

}
