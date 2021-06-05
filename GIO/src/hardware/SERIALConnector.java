package hardware;

import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;


public class SERIALConnector {

	private final static int BAUD_RATE = 115200;
	private final static int DATA_BITS = 8;
	private final static int ESPERA_PUERTO = 2000; 
	
	SerialPort serialPort;
	SerialPort ports[];
	String port;
	boolean isConnected;
		
	public SERIALConnector(String port) {
		this.port = port;
		isConnected = false;
		openSerial();
	}
	
	
	private void openSerial()
	{
		if (!configureConnection()) {
			JOptionPane.showMessageDialog(null, "Dispositivo FPGA no conectado", "Error conexión", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean configureConnection()
	{
		if (checkSerialPort()){
			serialPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, ESPERA_PUERTO, 0);
			serialPort.setComPortParameters(BAUD_RATE, DATA_BITS, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
			serialPort.openPort();	
			isConnected = true;
			return true;
		}		
		
		return false;
	}
	
	private boolean checkSerialPort()
	{
		boolean find = false;
		int i = 0;
		ports = SerialPort.getCommPorts();

		if (ports.length == 0) return find;
		else {
			while (find != true && (i < ports.length)) {
				if (ports[i].getSystemPortName().equalsIgnoreCase(this.port)) {
					serialPort = ports[i];
					find = true;
				}
				i++;
			}
		}				
		return find;
	}
	
	
	public SerialPort getSerialPort() {
		return serialPort;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public void closeSerial()
	{
		serialPort.closePort();
	}
	
	
	
}
