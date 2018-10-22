/**
 *  Implementation of Double Hashing in Java
 *  @author Sai Krishna Reddy Syamala (sxs169430)
 *  @author Mihir Hindocha (mxh170027)
 *  Ver 1.0: 10/21/2018
 */
package sxs169430;

public class DoubleHashing<T> {
	private T[] table; // The array of elements
	private int size; // Current size
	private int PRIME;
	private static final int DEFAULT_SIZE = 1000; // default array size if not provided
	/* Constructor */
	public DoubleHashing() {
		this(DEFAULT_SIZE);
	}
	
	public DoubleHashing(int ts) {
		formArray(ts);
		PRIME = getPrime(ts);
		clear();
	}
	/**
	 * creates a new array with the specified size
	 * @param arraySize
	 */
	@SuppressWarnings("unchecked")
	private void formArray(int arraySize) {
		table = (T[]) new Object[getPrime(arraySize)];
	}

	/* Function to get prime number less than table size for myhash2 function */
	public int getPrime(int n) {
		while (!isPrime(--n)); 

		return n;//Returns a prime number less than the table size
	}

	/* Check if the number is a prime number */
	protected static boolean isPrime(int m) {
		int n = m;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/* Function to get number of elements in the table */
	public int size() {
		return size;
	}
	// returns true if the table is empty
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * returns a hashValue for the element to be inserted
	 * @param x - element to be placed in the table
	 * @return hashValue
	 */
	private int myhash1(T x) {
		int hashVal = Math.abs(x.hashCode());
		hashVal %= table.length;
		if (hashVal < 0)
			hashVal += table.length;
		return hashVal;
	}
	/**
	 * second hash function to reduce the collision
	 * @param x - element to be inserted
	 * @return hashValue
	 */
	private int myhash2(T x) {
		int hashVal = Math.abs(x.hashCode());
		hashVal %= PRIME;
		if(hashVal < 0)
			hashVal += table.length;
		return PRIME - hashVal%PRIME;
	}	
	/* Function to clear hash table */
	public void clear() {
		size = 0;
		for (int i = 0; i < table.length; i++)
			table[i] = null;
	}
	/**
	 * returns a slot in the table for the given element
	 * @param key - element to be inserted
	 * @return - index in the table
	 */
	public int find(T key) {
		// returns the entry for the key
		int hash1 = myhash1(key);
		int hash2 = myhash2(key);
		while (table[hash1] != null && !table[hash1].equals(key)) {
			hash1 += hash2;
			if (hash1 >= table.length)
				hash1 -= table.length;
		}
		return hash1; 
	}

	/**
	 * function to add an element to the table
	 * @param x - element to be added
	 * @return - true if element is inserted, false if element already exists
	 */
	public boolean add(T x) {
		// Insert x
		int hash1 = find(x); 
		if (contains(x))
			return false; // if the element already exists in the table
		table[hash1] = x;
		size++;
		// Rehash if the table is half full
		if (size > table.length / 2)
			rehash();

		return true;
	}

	/**
	 * function to expand the array and rehash all the old values.
	 */
	private void rehash() {
		T[] oldArray = table;
		// Create a new double-sized, empty table
		formArray(2 * oldArray.length);
		size = 0;
		// Copy the old array
		for (T item : oldArray)
			if (item != null)
				add(item);
	}
	/**
	 * function to check if the table contains the given element
	 * @param key - element to be checked
	 * @return - true if the element exists in the table.
	 */
	public boolean contains(T key) {
		int hash1 = find(key);
		if (table[hash1] != null)
			return true;
		return false;
	}

	/**
	 * Function to remove a key
	 * @param key - element to be removed from the table
	 * @return - true if element is removed  
	 */
	public boolean remove(T key) {
		int hash1 = find(key);
		if (table[hash1] != null) {
			table[hash1] = null;
			size--;
			return true;
		} else
			return false;
	}
}
