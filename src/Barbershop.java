//Barber Shop Simulation
//
// Emmanuel Medina
// May 12th 2015
//
//The purpose of this code is to simulate a barber shop.


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// Class Barbershop represents the application that simulates
// the events at a barber shop
public class Barbershop {
	// static fields
	private static final int NUM_CUSTOMERS = 10;
	private static int time = 0 ; // current simulation time
	private static int nextArrivalTime;
	private static Queue arrivalList;
	private static CircularQueue waitingCustomers;
	private static Customer customerInBarberChair;
	
	
	// main method
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter number of waiting waitingCustomers: ");
		int numChairs = keyboard.nextInt();
		waitingCustomers = new CircularQueue(numChairs);
		arrivalList = new Queue(NUM_CUSTOMERS);
		readCustomersFile();
		customerInBarberChair = null;
		
		while (!arrivalList.isEmpty()) {
			// while there are still customers to arrive,
			// take the next arriving customer off the arrival list
			Customer nextCustomer = arrivalList.dequeue();
			nextArrivalTime = nextCustomer.getArrivalTime();
			// process each time step from current time
			// until the time of next customer arrival
			while (time <= nextArrivalTime) {
				processEachTimestep(nextCustomer);
			}
		}
		// The previous while loop terminates after processing the time step
		// at the arrival time of the last customer in arrival list
		// The next while loop process the remaining time steps until
		// the last customer finishes his/her haircut.
		while (!waitingCustomers.isEmpty()
				|| customerInBarberChair != null) {
			processEachTimestep(null);
			
		}
		System.out.println("\nProcess completed.");
	}

	
	// The method process the simulation events and prints out the barber shop
	// state for each time step
	private static void processEachTimestep(Customer nextCustomer) {
		printCurrentTimeStep();
		
		processBarberAndWaitingCustomers();
		// The next customer arriving at current time will sits in waiting
		// chairs if a waiting chair is available; otherwise, the arriving
		// customer leaves.
		
		if (nextCustomer != null && time == nextArrivalTime) {
			if (waitingCustomers.isFull()) {
				System.out.println("All waitingCustomers are full");
				System.out.println("\t" + nextCustomer.getCustomerName()  + " leaves");
			} else {
				System.out.println("A chair is available");
				System.out.println("\t" + nextCustomer.getCustomerName()  + " sits");
				waitingCustomers.enqueue(nextCustomer);
			}
		}
		
		// output waitingCustomers and barber status
		if (waitingCustomers.isEmpty()) {
			System.out.println("Chairs are empty");
		} else {
			System.out.println("Chairs");
			waitingCustomers.display();
			System.out.println();
		}
		
		if (customerInBarberChair == null)
			System.out.println("Barber's chair is empty");
		else {
			System.out.println("Barber");
			//System.out.println(customerInBarberChair);
			System.out.println("\t" + customerInBarberChair.getCustomerName() +
					" is in charir " + customerInBarberChair.getServiceTime() +
					" left\n");
		}
		
		// decrement service time left for customer in barber chair one time step ahead
		if (customerInBarberChair != null )
			customerInBarberChair.setServiceTime(customerInBarberChair.getServiceTime() - 1);
		
		// output arrival list
		System.out.println("Arrival List");
		arrivalList.display();
		System.out.println("\n---------------------------\n");
		
		time++; // increment simulation time step
	}

	// print current time step
	private static void printCurrentTimeStep() {
		System.out.println();
		System.out.println("Time = " + time);
		System.out.println();
	}

	// process barber chair and waitingCustomers
	// If no customer is having a hair cut or if the customer having a
	// haircut is done
	// output "barber takes a break if there are no waiting customers,
	// otherwise, move the next waiting customer to barber chair
	private static void processBarberAndWaitingCustomers() {
		if (customerInBarberChair == null ||customerInBarberChair.getServiceTime() == 0 ) {
			if (waitingCustomers.isEmpty()) {
				// barber takes a break;
				System.out.println("Barber takes a break");
				customerInBarberChair = null;
			} else {
				// first customer waiting in waitingCustomers moved to barber chair
				customerInBarberChair = waitingCustomers.dequeue();
			}
		}
	}
	
	// Reads customer input text file
	private static void readCustomersFile() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter arrivalList file name: ");
		String fileName = keyboard.next();
		String inputLine;
		
		Scanner fileInput;
		File inFile = new File(fileName);
		System.out.println("Opening and reading " + fileName);
				
		try{
			fileInput = new Scanner(inFile);
			while (fileInput.hasNextLine()) {
				if (!fileInput.hasNextInt())
					break;
				int arrivalTime = fileInput.nextInt();
				int serviceTime = fileInput.nextInt();
				String name = fileInput.next();
				
				arrivalList.enqueue(new Customer(arrivalTime, serviceTime, name));
			}
			fileInput.close();
		} // end try
			
		catch (FileNotFoundException e){
			System.out.println(e);
			System.exit(1);		// IO error; exit program
		} // end catch	
		System.out.println("Finished reading file");
	}	
}
