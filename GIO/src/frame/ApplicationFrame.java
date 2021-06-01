package frame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import connector.DBConnector;
import controller.FrameController;
import gio.gfx.ResourceHandler;
import screens.CustomPanel;
import screens.MainMenu;

public class ApplicationFrame extends JFrame{
	
	private final static String LOGO_PATH = "res/GIOicon.png";
	FrameController controller;
	JPanel mainPanel;
	JPopupMenu userMenu;
	
	public ApplicationFrame() {
		super("GIO");
		DBConnector.openConnectionToDB();
		controller = new FrameController(this);
		this.setSize(new Dimension(1280,720));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setContentPane(createAppPanel());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private Container createAppPanel() {
		mainPanel = new JPanel(new BorderLayout());
		JPanel subPanel = new CustomPanel(ResourceHandler.getTopBar());
		BoxLayout b = new BoxLayout(subPanel, BoxLayout.X_AXIS);
		subPanel.setLayout(b);
		subPanel.add(createLogoButton());
		subPanel.add(Box.createHorizontalGlue());
		subPanel.add(createUserButton());
		subPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		userMenu = new JPopupMenu();
		JMenuItem show = new JMenuItem("showwww");
		userMenu.add(show);
		this.add(userMenu);
		mainPanel.add(subPanel, BorderLayout.NORTH);
		mainPanel.add(new MainMenu(this), BorderLayout.CENTER);
		return mainPanel;
	}
	
	

	private Component createUserButton() {
		JLabel user = new JLabel();
		user.setIcon(new ImageIcon("res/user.png"));
		user.setFocusable(false);
		user.setBorder(null);
		user.addMouseListener(new MouseAdapter() {
			 @Override 
			 public void mousePressed(MouseEvent e) {
			      userMenu.show(ApplicationFrame.this, 1220, 70);
			 }
		});
		return user;
	}

	private Component createLogoButton() {
		JButton button = new JButton();
		button.setIcon(new ImageIcon(LOGO_PATH));
		button.setFocusable(false);
		button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setActionCommand("exit-menu");
		button.addActionListener(controller);
		return button;
	}

	public void changePanel(JPanel panel) {
		BorderLayout layout = (BorderLayout) mainPanel.getLayout();
		mainPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.validate();
	}
}
