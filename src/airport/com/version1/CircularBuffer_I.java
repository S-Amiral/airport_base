/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 23 mai 2017
 ******************************************************************/

package airport.com.version1;

/**
 * Interface Circular buffer contient deux méthodes à implémenter pour
 * respecter le modèle producteur-consommateur
 * @param <T>
 */
public interface CircularBuffer_I<T> {
    public void put(T element);
    public T remove();
}
