import java.util.Scanner;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Principal implements SerialPortEventListener{

	public static void main(String[] args) {
		Principal p = new Principal();
		String[] ports = p.getAvaiblePorts();
		for (int i = 0; i < ports.length; i++) {
			System.out.println(i+". "+ports[i]);
		}
		Scanner in = new Scanner(System.in);
		try {
			p.openConnection(ports[in.nextInt()]);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			p.port.addEventListener(p);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
		try {
			p.port.writeByte(in.nextByte());
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	private SerialPort port = null;
	private final int BAUDRATE = 115200, DATABITS_8 = 8, STOPBITS_1 = 1, PARITY_NONE = 0;
	
	/* Eskuragarri dauden portuak erakusten ditu */
	public String[] getAvaiblePorts() {
		return SerialPortList.getPortNames();
	}

	/* Pasatutako portua irekitzen du */
	public void openConnection(String selectedPort) throws SerialPortException {
		if (port != null) port.closePort();
		port = new SerialPort(selectedPort);
		port.openPort();
		port.setParams(BAUDRATE,  DATABITS_8, STOPBITS_1, PARITY_NONE);
	}
	
	public SerialPort getPort() {
		return port;
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		//System.out.println(arg0.getEventType());
		//System.out.println(arg0.getEventValue());
		if(arg0.isRXCHAR()) {
			try {
				System.out.println(port.readString());
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
