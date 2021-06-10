package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import screens.frame.ApplicationFrame;
import screens.panel.OrderScreen;
import screens.panel.PedidosMenu;
import screens.panel.PersonalView;
import screens.panel.StockView;
import screens.panel.TemperatureScreen;

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