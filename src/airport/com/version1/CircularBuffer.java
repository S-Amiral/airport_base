/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 28 mai 2017
 ******************************************************************/

package airport.com.version1;

import airport.com.version1.AvionPersonnal;

public class CircularBuffer implements CircularBuffer_I<AvionPersonnal> {

    /**
     * Attribut of the class
     */
    private final int max; // buffer size;
    private final AvionPersonnal[] data; // buffer who contain the data
    private int in = 0; // cursor for the producer
    private int out = 0; // cursor for the consumer
    private int nbMsg = 0; /* Space available in the buffer */

    /**
     * init. the size max and the buffer
     * 
     * @param max
     */
    public CircularBuffer(int max) {
	this.max = max;
	this.data = new AvionPersonnal[max];
    }

    /**
     * Can add an element in the buffer
     * 
     * @param element
     */
    @Override
    public synchronized void put(AvionPersonnal element) {
	while (nbMsg == this.max) { // until the buffered is full we still waiting
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function putElement");
		e.printStackTrace();
	    }
	}

	this.data[in] = element; // insert the data in the buffer
	this.in = mod(in); // update the modulo size
	this.nbMsg++;
	notifyAll(); // say that a message is ready to be consumed
    }

    /**
     * Take an element in the CircularBuffer
     * 
     * @return Avion element
     */
    @Override
    public synchronized AvionPersonnal remove() {
	while (nbMsg == 0) { // until they are not message we still waiting
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function takeElement");
		e.printStackTrace();
	    }
	}
	AvionPersonnal el = this.data[this.out]; // we take element from the buffer
	this.out = mod(this.out); // we udpate the modulo size
	this.nbMsg--;
	notifyAll(); // we notify that we take an element
	return el;
    }

    private int mod(int nb) {
	return (nb + 1) % this.max;
    }
}
