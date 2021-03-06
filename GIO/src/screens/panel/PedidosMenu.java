package screens.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import controller.MenuController;
import gfx.ResourceHandler;
import screens.frame.ApplicationFrame;
import screens.templates.CustomPanel;

public class PedidosMenu extends CustomPanel{

	private static final long serialVersionUID = 1L;
	private final static String BACKGORUND_PATH = "res/panel-fondo.png";
	private final static String PEDIDOS_PATH = "res/1080/pedidos.png";
	private final static String PEDIDOS_ROLL_PATH = "res/1080/pedidos-roll.png";
	private final static String STOCK_PATH = "res/1080/stock.png";
	private final static String STOCK_ROLL_PATH = "res/1080/stock-roll.png";
	private final static String TEMP_PATH = "res/1080/temp.png";
	private final static String TEMP_ROLL_PATH = "res/1080/temp-roll.png";
	
	ApplicationFrame frame;
	MenuController controller;
	
	public PedidosMenu(ApplicationFrame frame) {
		super(ResourceHandler.getBackground());
		this.frame = frame;
		this.controller = new MenuController(frame);
		this.setBorder(BorderFactory.createEmptyBorder(250,50,250,50));
		this.setLayout(new GridLayout(1,3,50,50));
		createRegistrarButton();
		createListadoButton();
		createProcesarButton();
	}

	private void createRegistrarButton() {
		JButton registrar = new JButton("Registrar pedido");
		registrar.setPreferredSize(new Dimension(550, 400));
		registrar.setBorder(null);
		registrar.addActionListener(controller);
		this.add(registrar);
	}
	

	private void createListadoButton() {
		JButton listado = new JButton("Ver lista pedidos");
		listado.setFont(new Font("Arial",Font.BOLD,25));
		listado.setPreferredSize(new Dimension(550, 400));
		listado.setBorder(null);
		listado.setActionCommand("screen-list");
		listado.addActionListener(controller);
		this.add(listado);
	}
	
	private void createProcesarButton() {
		JButton procesar = new JButton("Procesar pedido");
		procesar.setPreferredSize(new Dimension(550, 400));
		procesar.addActionListener(controller);
		procesar.setBorder(null);
		this.add(procesar);
	}

}