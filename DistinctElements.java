
public class DistinctElements {

	static<T> int distinctElements(T[ ] arr) { 
		DoubleHashing dh = new DoubleHashing(100);
		for(T i : arr) {
			dh.add(i);
		}
		return dh.size();
	}
}
