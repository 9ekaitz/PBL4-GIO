package hardware;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

public class Receiver implements Runnable{

	SERIALConnector connection;
	PropertyChangeSupport conector;
	private String data, temperatureData, weightData, keypadData;
	boolean temperatureState, weightState;
	String[] keys = {"1","2","3","4","5","6","7","8","9","*","#"};
	
	public Receiver(SERIALConnector connection) {
		this.connection = connection;
		temperatureData = "0";
		weightData = "0";
		temperatureState = false;
		weightState = false;
		conector = new PropertyChangeSupport(this);
	}	
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		conector.addPropertyChangeListener(listener);
	}
	
	
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		conector.removePropertyChangeListener(listener);
	}
	
	
	public void receive()
	{
		int size = connection.getSerialPort().bytesAvailable();
			
		if (size > 0) {
			byte[] buffer = new byte[size];
			connection.getSerialPort().readBytes(buffer, buffer.length);
			
			data = new String(buffer);
			
			if (data.startsWith("t")) temperatureState = true;
			
			if (temperatureState) {
				temperatureData = data;
				conector.firePropertyChange("Temperature", -1, temperatureData);
				temperatureState = false;
			}
			
			if (Arrays.asList(keys).contains(data)) {
				keypadData = data;
				conector.firePropertyChange("Keypad", -1, keypadData);
			}
			
			if (data.startsWith("g")) weightState = true;
			
			if (weightState) {
				weightData = data;
				conector.firePropertyChange("Weight", -1, weightData);
				weightState = false;
			}
		}		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {e.printStackTrace();}		
	}
	
	
	public String getTemperatureData() {
		return temperatureData;
	}
	
	public String getWeightData() {
		return weightData;
	}
	
	public String getKeypadData() {
		return keypadData;
	}
	
	
	
	@Override
	public void run() {
		while (true) {
			receive();
		}		
	}
	
	

}
