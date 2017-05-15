package airport.com;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
//represente l'avion

public class Avion implements Runnable {

	private AirportFrame airportFrame;
	private String codePlane;
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

	private Semaphore mutex;

	public Avion(AirportFrame _airportFrame, String _codePlane, BlockingQueue<Avion> _airArr,
			BlockingQueue<Avion> _tarmacLand, BlockingQueue<Avion> _tarmacTakeOff, BlockingQueue<Avion> _terminal,
			BlockingQueue<Avion> _airDep, int _nbAvion, int _nbPisteArr, int _nbPisteDep, int _nbPlace,
			Semaphore _mutex) {
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

		mutex = _mutex;
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
		// runVersionPersonnalBuffer();
	}

	private void runVersionBlockingQueue() {
		// ins√©rer le code ici pour la version 1

		try {

			// Initialisation des avion dans l'air
			airArr.put(this);

			// Mise ‡ jour de l'affichage du nombre d'avion en l'air
			mutex.acquire();
			airportFrame.nbOnAirLabel.setText("" + airArr.size());
			mutex.release();

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion arrive sur le tarmac d'atterissage
			tarmacLand.put(airArr.take());

			// Mise ‡ jour de l'affichage du nombre d'avion en l'air
			mutex.acquire();
			airportFrame.nbOnAirLabel.setText("" + (Integer.valueOf(airportFrame.nbOnAirLabel.getText()) - 1));
			mutex.release();

			// Mise ‡ jour de l'affichage des avions sur le tarmac d'arrivÈ
			int i = 0;
			mutex.acquire();
			JLabel labelLand = airportFrame.listArr.get(i);
			while (labelLand.isVisible()) {
				labelLand = airportFrame.listArr.get(i);
				i++;
				System.out.println(i);
			}

			airportFrame.nbLandingLabel.setText("" + i);
			labelLand.setText(codePlane);
			labelLand.setVisible(true);
			mutex.release();

			Tools.simulateTime(Tools.TEST_TIME);

			// L'avion se gare au terminal
			terminal.put(tarmacLand.take());

			// Mise ‡ jour de l'affichage du nombre d'avion sur le tarmac
			mutex.acquire();
			labelLand.setVisible(false);
			airportFrame.nbLandingLabel.setText("" + (i - 1));
			mutex.release();
			
			// Mise ‡ jour de l'affichage du nombre d'avion sur le parking
			int j = 0;
			mutex.acquire();
			JLabel labelTerminal = airportFrame.listTerm.get(j);
			while (labelTerminal.isVisible()) {
				labelTerminal = airportFrame.listTerm.get(j);
				j++;
			}

			airportFrame.nbTermLabel.setText("" + j);
			labelTerminal.setText(codePlane);
			labelTerminal.setVisible(true);
			mutex.release();
			
			Tools.simulateTime(Tools.TEST_TIME);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void runVersionPersonnalBuffer() {
		// ins√©rer le code ici pour la version 2
	}
}
