package airport.com.buffer;

/**
 * Created by johnny.dacosta on 15/05/2017.
 */
public interface CircularBuffer_I {
    public void putElement(Object element);
    public Object takeElement();
}
