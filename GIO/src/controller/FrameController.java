package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.ApplicationFrame;
import tmp.MainMenu;

public class FrameController implements ActionListener{

	ApplicationFrame frame;
	
	public FrameController(ApplicationFrame applicationFrame) {
		this.frame = applicationFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "exit-menu":
			frame.changePanel(new MainMenu(frame));
			break;
		default:
			break;
		}
	}

}
