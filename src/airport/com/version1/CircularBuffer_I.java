package airport.com.version1;

/**
 * Created by johnny.dacosta on 15/05/2017.
 */
public interface CircularBuffer_I<T> {
    public void put(T element);
    public T remove();
}
