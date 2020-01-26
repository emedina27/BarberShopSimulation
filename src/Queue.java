//Barber Shop Simulation
//
// Emmanuel Medina
// May 12th 2015
//
//The purpose of this code to create the Queue of customers

//Instance Variables 
public class Queue {
	private Customer[] customers;
	private int size;
	private int numItems;

	
    //Constructor
	public Queue(int size) {
		this.size = size;
		customers = new Customer[size];
		numItems = 0;
	}
	
	//Instance Methods checking  if queue is empty.
	public boolean isEmpty() {
		return numItems == 0;
	}
	//Instance method checking if queue is full.
	public boolean isFull() {
		return numItems >= size;
	}
	//Instance method for adding a customer to the back of the line
	public void enqueue(Customer customer) {
		if (isFull()) {
			System.err.println("attempt to enqueue a full customer queue");
			System.exit(1);
		}
		customers[numItems] = customer;
		numItems++;
	}
	//Instance method for removing a customer from the front of the line
    public Customer dequeue() {
		if (isEmpty()) {
			System.err.println("attempt to dequeue an empty customer queue");
			System.exit(1);
		}
		Customer first = customers[0];
		// move subsequent elements one position to the left
		for (int i = 1; i < customers.length; i++) {
			customers[i - 1] = customers[i];
		}
		numItems--;
		return first;
	}
	//display
	public void display() {
		for (int i = 0; i < numItems; i++) {
			System.out.println(customers[i]);
		}
	}

}
