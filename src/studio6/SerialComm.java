package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte b){
		try {
			port.writeByte(b);
			if(debug){
				System.out.println(b);
			}
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// TODO: Add available() method
	public boolean available() throws SerialPortException{
		return(port.getInputBufferBytesCount()>0);
	}
	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException{
		byte b = port.readBytes()[0];
		if(debug){
			System.out.println(String.format("%02x", b));
		}
		return(b);
	}
	// TODO: Add a main() method
	public static void main(String[] args) throws SerialPortException{
		SerialComm sc = new SerialComm("COM4");
		sc.setDebug(true);
		while(true){
			if(sc.available()){
				System.out.println((char)sc.readByte() + "\n");
			}
		}
	}
}
