/**
 *  Implementation of Distinct Elements class
 *  @author Sai Krishna Reddy Syamala (sxs169430)
 *  @author Mihir Hindocha (mxh170027)
 *  Ver 1.0: 10/21/2018
 */
package sxs169430;

public class DistinctElements {
	static<T> int distinctElements(T[ ] arr) { 
		DoubleHashing<T> dh = new DoubleHashing<>(100);
		for(T i : arr) {
			dh.add(i);
		}
		return dh.size();
	}
}
