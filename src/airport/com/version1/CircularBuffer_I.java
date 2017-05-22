/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 28 mai 2017
 ******************************************************************/

package airport.com.version1;

public interface CircularBuffer_I<T> {
    public void put(T element);

    public T remove();
}
