//Barber Shop Simulation
//
// Emmanuel Medina
// May 12th 2015
//
// 

// Customer class
public class Customer {
	
	//Instance Variables arrivalTime, serviceTime, customerName.
	private int  _arrivalTime;
	private int  _serviceTime; 
	private String  _customerName;
	
	//Constructor 
	public Customer(int arrivalTime, int serviceTime, String customerName){
		_arrivalTime = arrivalTime;
		_serviceTime  =  serviceTime;
		_customerName   = customerName;
		}  
    // Instance Methods getters and setters 
	public int getArrivalTime() {
		return _arrivalTime;
	}

	public void setArrivalTime(int _arrivalTime) {
		this._arrivalTime = _arrivalTime;
	}

	public int getServiceTime() {
		return _serviceTime;
	}

	public void setServiceTime(int _serviceTime) {
		this._serviceTime = _serviceTime;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String _customerName) {
		this._customerName = _customerName;
	}

	// Method toString for instance variables
	public String toString () {
		return _customerName  + ": arrival = "
				+ _arrivalTime + ": service = "
				+ _serviceTime;
	}

}
