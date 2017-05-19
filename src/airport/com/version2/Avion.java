package airport.com.version2;

import airport.com.Tools;
import java.util.concurrent.BlockingQueue;

public class Avion implements Runnable {

	private AirportFrame airportFrame;
	private String codePlane;

	//version Java
	private BlockingQueue<Avion> airArr;
	private BlockingQueue<Avion> tarmacLand;
	private BlockingQueue<Avion> tarmacTakeOff;
	private BlockingQueue<Avion> terminal;
	private BlockingQueue<Avion> airDep;

	private int nbAvion;
	private int nbPisteArr;
	private int nbPisteDep;
	private int nbPlace;

	private int position;


	public Avion(AirportFrame _airportFrame, String _codePlane, BlockingQueue<Avion> _airArr,
				 BlockingQueue<Avion> _tarmacLand, BlockingQueue<Avion> _tarmacTakeOff, BlockingQueue<Avion> _terminal,
				 BlockingQueue<Avion> _airDep, int _nbAvion, int _nbPisteArr, int _nbPisteDep, int _nbPlace) {
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

			// Mise � jour de l'affichage du nombre d'avion en l'aire
			airportFrame.arrive(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion arrive sur le tarmac d'atterissage
			tarmacLand.put(this);
			airArr.remove(this);

			// Mise � jour de l'affichage du nombre d'avion en l'air et sur le
			// tarmac d'arriv�
			airportFrame.land(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion se gare au terminal
			terminal.put(this);
			tarmacLand.remove(this);

			// Mise � jour de l'affichage du nombre d'avion sur le tarmac
			// d'arriv� et sur le parking
			airportFrame.park(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion arrive sur le tarmac de d�collage
			tarmacTakeOff.put(this);
			terminal.remove(this);

			// Mise � jour de l'affichage du nombre d'avion sur le parking et
			// sur le tarmac de sortie
			airportFrame.takeOff(this);

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion arrive sur le tarmac de d�collage
			airDep.put(this);
			tarmacTakeOff.remove(this);

			// Mise � jour de l'affichage du nombre d'avion sur le parking et
			// sur le tarmac de sortie
			airportFrame.depart(this);

			Tools.simulateTime(Tools.TEST_TIME);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Version avec tampon personnel
	private void runVersionPersonnalBuffer() {

	}
}
