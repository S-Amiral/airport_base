/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 28 mai 2017
 ******************************************************************/

package airport.com.version2;

import airport.com.JFrameConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UseBlockingQueue {

    static String[] codePlane = { "3B147", "B3291", "6B239", "B1086", "780B4", "32A64", "17A69", "2A431", "647B8",
	    "349A8", "536B8", "9103A", "9B210", "139A4", "96B01", "207B9", "830B6", "8435A", "7301B", "1076B", "5281B",
	    "8A521", "3B806", "B6842", "B6238", "7B816", "A9437", "849A3", "60B18", "094B6", "4709B", "36A84", "085A3",
	    "0718B", "80B21", "0A369", "5290A", "370B4", "021A3", "84A02", "052A6", "B6350", "630B5", "8B903", "1398B",
	    "2693A", "902A6", "51A20", "971A5", "A7891" };

    public static void main(String[] args) {

	JFrameConfig jFrameConfig = new JFrameConfig();
	jFrameConfig.getOkButton().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ConfigBlockingQueue.nbAvion = jFrameConfig.getAvionSpinner(); // nomvre d'avion
		ConfigBlockingQueue.nbPisteArr = jFrameConfig.getPisteArrSpinner();// pistes d'atterrisage
		ConfigBlockingQueue.nbPisteDep = jFrameConfig.getPisteDepSpinner();// "" de depart
		ConfigBlockingQueue.nbPlace = jFrameConfig.getPlaceSpinner(); // parking

		AirportFrameBlockingQueue airportFrame = new AirportFrameBlockingQueue(ConfigBlockingQueue.nbPisteArr,
			ConfigBlockingQueue.nbPisteDep, ConfigBlockingQueue.nbPlace, ConfigBlockingQueue.nbAvion);
		jFrameConfig.setVisible(false);

		/**
		 * Version Blocking Queue
		 */
		BlockingQueue<AvionBlockingQueue> airArr = new ArrayBlockingQueue<AvionBlockingQueue>(
			ConfigBlockingQueue.nbAvion);
		BlockingQueue<AvionBlockingQueue> tarmacLand = new ArrayBlockingQueue<AvionBlockingQueue>(
			ConfigBlockingQueue.nbPisteArr);
		BlockingQueue<AvionBlockingQueue> tarmacTakeOff = new ArrayBlockingQueue<AvionBlockingQueue>(
			ConfigBlockingQueue.nbPisteDep);
		BlockingQueue<AvionBlockingQueue> terminal = new ArrayBlockingQueue<AvionBlockingQueue>(
			ConfigBlockingQueue.nbPlace);
		BlockingQueue<AvionBlockingQueue> airDep = new ArrayBlockingQueue<AvionBlockingQueue>(
			ConfigBlockingQueue.nbAvion);

		for (int i = 0; i < ConfigBlockingQueue.nbAvion; i++) {
		    AvionBlockingQueue avion = new AvionBlockingQueue(airportFrame, codePlane[i], airArr, tarmacLand,
			    tarmacTakeOff, terminal, airDep, ConfigBlockingQueue.nbAvion,
			    ConfigBlockingQueue.nbPisteArr, ConfigBlockingQueue.nbPisteDep,
			    ConfigBlockingQueue.nbPlace);
		    new Thread(avion).start();
		}

		airportFrame.setVisible(true);
		airportFrame.pack();
	    }
	});

    }

}
