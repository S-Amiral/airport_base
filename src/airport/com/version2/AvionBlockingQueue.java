/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 28 mai 2017
 ******************************************************************/

package airport.com.version2;

import airport.com.Tools;
import java.util.concurrent.BlockingQueue;

public class AvionBlockingQueue implements Runnable {

    private AirportFrameBlockingQueue airportFrame;
    private String codePlane;

    // version Java
    private BlockingQueue<AvionBlockingQueue> airArr;
    private BlockingQueue<AvionBlockingQueue> tarmacLand;
    private BlockingQueue<AvionBlockingQueue> tarmacTakeOff;
    private BlockingQueue<AvionBlockingQueue> terminal;
    private BlockingQueue<AvionBlockingQueue> airDep;

    private int nbAvion;
    private int nbPisteArr;
    private int nbPisteDep;
    private int nbPlace;

    public AvionBlockingQueue(AirportFrameBlockingQueue _airportFrame, String _codePlane,
	    BlockingQueue<AvionBlockingQueue> _airArr, BlockingQueue<AvionBlockingQueue> _tarmacLand,
	    BlockingQueue<AvionBlockingQueue> _tarmacTakeOff, BlockingQueue<AvionBlockingQueue> _terminal,
	    BlockingQueue<AvionBlockingQueue> _airDep, int _nbAvion, int _nbPisteArr, int _nbPisteDep, int _nbPlace) {
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
}
