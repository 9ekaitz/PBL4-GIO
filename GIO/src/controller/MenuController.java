package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import screens.StockView;
import screens.ApplicationFrame;
import screens.OrderScreen;
import screens.TemperatureScreen;
import screens.PedidosMenu;
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
		case "screen-temp":
			frame.changePanel(new TemperatureScreen(frame));
			break;
		case "screen-pedidos":
			frame.changePanel(new PedidosMenu(frame));
			break;
		case "screen-list":
			frame.changePanel(new OrderScreen());
			break;
		default:
			break;
		}
	}

}