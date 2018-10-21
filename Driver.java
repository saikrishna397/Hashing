import java.util.HashSet;
import java.util.Random;

public class Driver {

	public static void main(String args[]) {
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		DoubleHashing<Integer> dh = new DoubleHashing<>(100);

		for (int i = 0; i < 1000000; i++) {
			dh.add(i);
		}
		for (int i = 0; i < 1000000; i++) {
			dh.contains(i);
		}
		for (int i = 0; i < 1000000; i++) {
			dh.remove(i);
		}
		endTime = System.currentTimeMillis();
		System.out.println("My program run time: " + (endTime - startTime));
		HashSet<Integer> hs = new HashSet<Integer>();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			hs.add(i);
		}
		for (int i = 0; i < 1000000; i++) {
			hs.contains(i);
		}
		for (int i = 0; i < 1000000; i++) {
			hs.remove(i);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Original Hashset time " + (endTime - startTime));
		
		Integer array[] = new Integer[100];
		for(int i = 0;i<array.length;i++) {
			array[i] = generateRandomInt(100);
		}
		
		int result = DistinctElements.distinctElements(array);
		System.out.println("Distinct elements: "+result);
		for(int i: array) {
			hs.add(i);
		}
		System.out.println("HashSet output is: "+hs.size());
		for(int i: array) {
			dh.add(i);
		}
		System.out.println("DoubleHashing output is: "+dh.size());
		
	}
	public static int generateRandomInt(int upperRange){
	    Random random = new Random();
	    return random.nextInt(upperRange);
	}
}
