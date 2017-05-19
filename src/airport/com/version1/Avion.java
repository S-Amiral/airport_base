package airport.com.version1;

import airport.com.version1.AirportFrame;
import airport.com.Tools;

public class Avion implements Runnable {

	private AirportFrame airportFrame;
	private String codePlane;

	//version personnalisée
	private CircularBuffer_I<Avion> airArr;
	private CircularBuffer_I<Avion> tarmacLand;
	private CircularBuffer_I<Avion> tarmacTakeOff;
	private CircularBuffer_I<Avion> terminal;
	private CircularBuffer_I<Avion> airDep;

	private int nbAvion;
	private int nbPisteArr;
	private int nbPisteDep;
	private int nbPlace;

	private int position;


	public Avion(AirportFrame _airportFrame, String _codePlane, CircularBuffer_I<Avion> _airArr,
                 CircularBuffer_I<Avion> _tarmacLand, CircularBuffer_I<Avion> _tarmacTakeOff, CircularBuffer_I<Avion> _terminal,
                 CircularBuffer_I<Avion> _airDep, int _nbAvion, int _nbPisteArr, int _nbPisteDep, int _nbPlace) {
		airportFrame = _airportFrame;
		codePlane = _codePlane;

		airArr = _airArr;
		tarmacLand = _tarmacLand;
		tarmacTakeOff = _tarmacTakeOff;
		terminal = _terminal;
		airDep = _airDep;

		nbAvion = _nbAvion;
		nbPisteArr = _nbPisteArr;
		nbPisteDep = _nbPisteDep;
		nbPlace = _nbPlace;

	}

	public String getCode() {
		return codePlane;
	}

	/**
	 * Faire deux version Version 1 -> utilisation de la blockingQueue Version 2
	 * -> utilisation de la version personnaliser
	 */
	@Override
	public void run() {
		runVersionBlockingQueue();
	}

	// Version avec blocking Queue
	private void runVersionBlockingQueue() {
		try {
			// Initialisation de l'avion dans l'air
			airArr.put(this);

			// Mise � jour de l'affichage du nombre d'avion en l'air
			airportFrame.arrive(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion arrive sur le tarmac d'atterissage
			tarmacLand.put(this);
			airArr.remove();

			// Mise � jour de l'affichage du nombre d'avion en l'air et sur le
			// tarmac d'arriv�
			airportFrame.land(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion se gare au terminal
			terminal.put(this);
			tarmacLand.remove();

			// Mise � jour de l'affichage du nombre d'avion sur le tarmac
			// d'arriv� et sur le parking
			airportFrame.park(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion arrive sur le tarmac de d�collage
			tarmacTakeOff.put(this);
			terminal.remove();

			// Mise � jour de l'affichage du nombre d'avion sur le parking et
			// sur le tarmac de sortie
			airportFrame.takeOff(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion arrive sur le tarmac de d�collage
			airDep.put(this);
			tarmacTakeOff.remove();

			// Mise � jour de l'affichage du nombre d'avion sur le parking et
			// sur le tarmac de sortie
			airportFrame.depart(this);

			Tools.simulateTime(Tools.TEST_TIME);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Version avec tampon personnel
	private void runVersionPersonnalBuffer() {

	}
}
