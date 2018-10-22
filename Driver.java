/**
 *  Driver for Implementation of Double Hashing in Java
 *  @author Sai Krishna Reddy Syamala (sxs169430)
 *  @author Mihir Hindocha (mxh170027)
 *  Ver 1.0: 10/21/2018
 *  
 *  Sample Implementation
 *  Timer for our Hashing Implementation: 
	Time: 204 msec.
	Memory: 34 MB / 155 MB.
	
	Timer for original HashSet Implementation: 
	Time: 649 msec.
	Memory: 102 MB / 238 MB.
	
	Our number of distinct elements - 63391
	Original Hashset elements number - 63391
	Our HashSet elements number - 63391
 */
package sxs169430;
import java.util.HashSet;
import java.util.Random;

public class Driver {
	public static void main(String args[]) {
		Timer timer = new Timer();
		DoubleHashing<Integer> dh = new DoubleHashing<>();
		for (int i = 0; i < 1000000; i++) {
			dh.add(i);
		}
		for (int i = 0; i < 1000000; i++) {
			dh.contains(i);
		}
		for (int i = 0; i < 1000000; i++) {
			dh.remove(i);
		}
		for (int i = 0; i < 1000000; i++) {
			dh.add(i); // adding elements again
		}
		timer.end();
		System.out.println("Timer for our Hashing Implementation: ");
		System.out.println(timer+ "\n");
		Timer timer1 = new Timer();
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < 1000000; i++) {
			hs.add(i);
		}
		for (int i = 0; i < 1000000; i++) {
			hs.contains(i);
		}
		for (int i = 0; i < 1000000; i++) {
			hs.remove(i);
		}
		for (int i = 0; i < 1000000; i++) {
			hs.remove(i);
		}
		for (int i = 0; i < 1000000; i++) {
			hs.add(i); // adding elements again
		}
		timer1.end();
		System.out.println("Timer for original HashSet Implementation: ");
		System.out.println(timer1 + "\n");
		distinctElements();
	}
	/**
	 * function to generate a random integer
	 * @param upperRange - range of the max number
	 * @return - returns an integer between 0 and upperRange
	 */
	public static int generateRandomInt(int upperRange){
	    Random random = new Random();
	    return random.nextInt(upperRange);
	}
	public static void distinctElements() {
		// create a random array of 1000000 elements
		Integer array[] = new Integer[1000000];
		for(int i = 0;i<array.length;i++) {
			array[i] = generateRandomInt(1000000);
		}
		DoubleHashing<Integer> DH = new DoubleHashing<>();
		int result = DistinctElements.distinctElements(array);
		System.out.println("Our number of distinct elements - "+result);
		HashSet<Integer> HS = new HashSet<Integer>();
		for(int i : array) {
			HS.add(i);
		}
		for(int i : array) {
			DH.add(i);
		}
		System.out.println("Original Hashset elements number - "+ HS.size());
		System.out.println("Our HashSet elements number - "+ DH.size());
	}
}
