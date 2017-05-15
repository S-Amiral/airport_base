package airport.com.buffer;

import javafx.beans.binding.ObjectExpression;

/**
 * Created by johnny.dacosta on 15/05/2017.
 */
public class CircularBuffer implements CircularBuffer_I {

    /**
     * Attribut of the class
     */
    private final int max; //buffer size;
    private final Object[] data; //buffer who contain the data
    private int in = 0; //cursor for the producer
    private int out = 0; //cursor for the consumer
    private int nbMsg = 0; /* Space available in the buffer */

    /**
     * init. the size max and the buffer
     * @param max
     */
    public CircularBuffer(int max) {
        this.max = max;
        this.data = new Object[max];
    }

    /**
     * Can add an element in the buffer
     * @param element
     */
    @Override
    public synchronized void putElement(Object element) {
        while(nbMsg == this.max){ //until the buffered is full we still waiting
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Failed waiting : function putElement");
                e.printStackTrace();
            }
        }

        this.data[in] = element;    //insert the data in the buffer
        this.in = mod(in);          //update the modulo size
        this.nbMsg++;
        notify();                   //say that a message is ready to be consumed
    }

    /**
     * Take an element in the CircularBuffer
     * @return Object element
     */
    @Override
    public synchronized Object takeElement() {
        while(nbMsg == 0){ //until they are not message we still waiting
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Failed waiting : function takeElement");
                e.printStackTrace();
            }
        }
        Object el = this.data[this.out];    //we take element from the buffer
        this.out = mod(this.out);           //we udpate the modulo size
        this.nbMsg--;
        notify();                           //we notify that we take an element
        return el;
    }

    private int mod(int nb){
        return (nb+1) % this.max;
    }
}
