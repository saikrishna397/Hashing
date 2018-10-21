
//package sxs169430;

import java.util.HashSet;

public class DoubleHashing<T> {
	private T[] table; // The array of elements
	private int size; // Current size
	private int TABLE_SIZE;
	private int PRIME;

	/* Constructor */
	public DoubleHashing(int ts) {
		TABLE_SIZE = ts;
		formNewArray(ts);
		PRIME = getPrime(ts);
		clear();
	}

	@SuppressWarnings("unchecked")
	private void formNewArray(int arraySize) {
		table = (T[]) new Object[getPrime(arraySize)];
	}

	/* Function to get prime number less than table size for myhash2 function */
	public int getPrime(int n) {
//		n = TABLE_SIZE; /** is it required as we are passing ts to the function above anyways */
		while (!isPrime(--n))
			; 

		return n;/* Return a prime number */
	}

	/* Check if the numer is a prime number */
	protected static boolean isPrime(int m) {
		int n = m;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	// find - returns the entry
	// contains

	/* Function to get number of key-value pairs */
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/* Function to clear hash table */
	public void clear() {
		size = 0;
		for (int i = 0; i < table.length; i++)
			table[i] = null;
	}

	/* Function to get value of a key */
	public T get(T key) {
		int hash1 = find(key);
		if (table[hash1] != null)
			return table[hash1];
		else
			return null;
	}

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

	/* Function to add a key*/
	public boolean add(T x) {
		// Insert x
		int hash1 = find(x); 
		if (table[hash1] == x)
			return false;
		table[hash1] = x;
		size++;
		// Rehash if the table is half full
		if (size > table.length / 2)
			rehash();

		return true;
	}

	/**
	 * Expand the array and rehash all the old values.
	 */
	private void rehash() {
		T[] oldArray = table;
		/**
		 * If we take newsize = 2* old size then it doesn't remain prime anymore maybe
		 * that is the case. We need to find the next big prime number if we want to
		 * reduce collisions
		 */
		// Create a new double-sized, empty table
		formNewArray(2 * oldArray.length);
		size = 0;

		// Copy the old array
		for (T item : oldArray)
			if (item != null)
				add(item);
	}

	public boolean contains(T key) {
		int hash1 = find(key);
		if (table[hash1] != null)
			return true;
		return false;
	}

	/* Function to remove a key */
	public boolean remove(T key) {
		int hash1 = find(key);
		if (table[hash1] != null) {
			table[hash1] = null;
			size--;
			return true;
		} else
			return false;
	}

	private int myhash1(T x) {
		int hashVal = Math.abs(x.hashCode());
		hashVal %= table.length;
		if (hashVal < 0)
			hashVal += table.length;
		return hashVal;
	}

	private int myhash2(T x) {
		int hashVal = Math.abs(x.hashCode());
		hashVal %= PRIME;
		hashVal = PRIME - hashVal;
		return hashVal;
	}

	
}
