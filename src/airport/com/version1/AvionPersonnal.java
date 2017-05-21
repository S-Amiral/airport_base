package airport.com.version1;

import airport.com.Tools;

public class AvionPersonnal implements Runnable {

	private AirportFramePersonnal airportFrame;
	private String codePlane;

	// version personnalisée
	private CircularBuffer_I<AvionPersonnal> airArr;
	private CircularBuffer_I<AvionPersonnal> tarmacLand;
	private CircularBuffer_I<AvionPersonnal> tarmacTakeOff;
	private CircularBuffer_I<AvionPersonnal> terminal;
	private CircularBuffer_I<AvionPersonnal> airDep;

	private int nbAvion;
	private int nbPisteArr;
	private int nbPisteDep;
	private int nbPlace;

	public AvionPersonnal(AirportFramePersonnal _airportFrame, String _codePlane, CircularBuffer_I<AvionPersonnal> _airArr,
			CircularBuffer_I<AvionPersonnal> _tarmacLand, CircularBuffer_I<AvionPersonnal> _tarmacTakeOff,
			CircularBuffer_I<AvionPersonnal> _terminal, CircularBuffer_I<AvionPersonnal> _airDep, int _nbAvion, int _nbPisteArr,
			int _nbPisteDep, int _nbPlace) {
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

	@Override
	public void run() {
		runVersionPersonnalBuffer();
	}

	// Version avec tampon personnel
	private void runVersionPersonnalBuffer() {
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

}
