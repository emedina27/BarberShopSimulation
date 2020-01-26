//Barber Shop Simulation
//
// Emmanuel Medina
// May 12th 2015
//
//The purpose of this code is to create a Circular Queue of customers to simulate the waiting chairs.

 // Class CircularQueue 
public class CircularQueue {
	
	//Instance Variables
	private Customer[] customers;
	private int size; // size of customers array
	private int numItems; // number of customer in circular Q
	private int first ;  // index of first customer in circular Q
	private int last;    // index after the last customer in circularQ
	
	//Constructor
	public CircularQueue(int size) {
		this.size = size;
		customers = new Customer[size];
		numItems = 0;
		first = last = 0;
	}
	//Instance Method check if empty
	public boolean isEmpty() {
		return numItems == 0;
	}
	//Instance Method if full
	public boolean isFull() {
		return numItems >= size;
	}
	// Adding a customer to end of the Queue 
	public void enqueue(Customer customer) {
		if (isFull()) {
			System.err.println("attempt to enqueue a full customer queue");
			System.exit(1);
		}
		customers[last] = customer;
		last++;
		if (last >= size)
			last = 0; // reset to 0;
		
		numItems++;
	}
	// Removing customers from the front of the queue.
	public Customer dequeue() {
		if (isEmpty()) {
			System.err.println("attempt to dequeue an empty customer queue");
			System.exit(1);
		}
		//             last        first
		//    0         1           2
		// Greg                 Bob
		    
		Customer firstCustomer = customers[first];
		first++;
		if (first >= size)
			first = 0; // reset to 0;

		numItems--;
		return firstCustomer;
	}
	//Display
	public void display() {
		if (isEmpty())
			return;
			
		if (first < last) {
			for (int i = first; i < last; i++) {
				System.out.println(customers[i]);
			}
		} else {
			for (int i = first; i < size; i++) {
				System.out.println(customers[i]);
			}
			for (int i = 0; i < last; i++) {
				System.out.println(customers[i]);
			}
		}
	}

}
