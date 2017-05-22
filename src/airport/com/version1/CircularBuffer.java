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
    private final int max; // taille du buffer;
    private final AvionPersonnal[] data; // le buffer qui va contenir nos avions
    private int in = 0; // curseur pour le producteur
    private int out = 0; // curseur pour le consommateur
    private int nbMsg = 0; /* espace valide dans le buffer */

    /**
     * init. le nombre max du buffer
     * 
     * @param max
     */
    public CircularBuffer(int max) {
	this.max = max;
	this.data = new AvionPersonnal[max];
    }

    /**
     * Ajoute un élément dans le buffer
     * 
     * @param element
     */
    @Override
    public synchronized void put(AvionPersonnal element) {
	while (nbMsg == this.max) { // tant que le buffer est plein
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function putElement");
		e.printStackTrace();
	    }
	}

	this.data[in] = element; // insère un élément dans le buffer
	this.in = mod(in); // met à jour le modulo du buffer
	this.nbMsg++;
	notifyAll(); // dis à tous les consommateurs qu'un message est prêt à être consommer
    }

    /**
     * Prend un élément dans le buffer
     * 
     * @return Avion element
     */
    @Override
    public synchronized AvionPersonnal remove() {
	while (nbMsg == 0) { //tant qu'il n'y pas de message on attend
	    try {
		wait();
	    } catch (InterruptedException e) {
		System.out.println("Failed waiting : function takeElement");
		e.printStackTrace();
	    }
	}
	AvionPersonnal el = this.data[this.out]; // on prend un élément dans le buffer
	this.out = mod(this.out); // on met à jour le modulo
	this.nbMsg--;
	notifyAll(); // on notify qu'il y de l'espace qui c'est libéré dans le buffer
	return el;
    }

    private int mod(int nb) {
	return (nb + 1) % this.max;
    }
}
