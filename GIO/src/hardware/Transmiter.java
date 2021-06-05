package hardware;

import java.io.IOException;
import java.io.OutputStream;

public class Transmiter {

	SERIALConnector connection;
	OutputStream data;
	
	
	public Transmiter(SERIALConnector connection) {
		this.connection = connection;
	}
	
	
	public void send(String command)
	{		
		try {
			data = connection.getSerialPort().getOutputStream();
			data.write(command.getBytes());
		} catch (IOException e) {e.printStackTrace();}
	}
	
	
}
