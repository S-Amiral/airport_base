/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 23 mai 2017
 ******************************************************************/

package airport.com.version1;

import static airport.com.version1.ConfigPersonnal.nbPlace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import airport.com.JFrameConfig;

public class UsePersonnal {

    static String[] codePlane = { "3B147", "B3291", "6B239", "B1086", "780B4", "32A64", "17A69", "2A431", "647B8",
	    "349A8", "536B8", "9103A", "9B210", "139A4", "96B01", "207B9", "830B6", "8435A", "7301B", "1076B", "5281B",
	    "8A521", "3B806", "B6842", "B6238", "7B816", "A9437", "849A3", "60B18", "094B6", "4709B", "36A84", "085A3",
	    "0718B", "80B21", "0A369", "5290A", "370B4", "021A3", "84A02", "052A6", "B6350", "630B5", "8B903", "1398B",
	    "2693A", "902A6", "51A20", "971A5", "A7891" };

    public static void main(String[] args) {
	// argument par d�fault

	JFrameConfig jFrameConfig = new JFrameConfig();

	jFrameConfig.getOkButton().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ConfigPersonnal.nbAvion = jFrameConfig.getAvionSpinner(); // nomvre d'avion
		ConfigPersonnal.nbPisteArr = jFrameConfig.getPisteArrSpinner();// pistes d'atterrisage
		ConfigPersonnal.nbPisteDep = jFrameConfig.getPisteDepSpinner();// "" de depart
		nbPlace = jFrameConfig.getPlaceSpinner(); // parking

		jFrameConfig.setVisible(false);
		
		AirportFramePersonnal airportFrame = new AirportFramePersonnal(ConfigPersonnal.nbPisteArr,
			ConfigPersonnal.nbPisteDep, nbPlace, ConfigPersonnal.nbAvion);

		/**
		 * Tampon personnel
		 */
		CircularBuffer_I<AvionPersonnal> airArr = new CircularBuffer(ConfigPersonnal.nbAvion);
		CircularBuffer_I<AvionPersonnal> tarmacLand = new CircularBuffer(ConfigPersonnal.nbPisteArr);
		CircularBuffer_I<AvionPersonnal> tarmacTakeOff = new CircularBuffer(ConfigPersonnal.nbPisteDep);
		CircularBuffer_I<AvionPersonnal> terminal = new CircularBuffer(nbPlace);
		CircularBuffer_I<AvionPersonnal> airDep = new CircularBuffer(ConfigPersonnal.nbAvion);

		for (int i = 0; i < ConfigPersonnal.nbAvion; i++) {
		    AvionPersonnal avion = new AvionPersonnal(airportFrame, codePlane[i], airArr, tarmacLand,
			    tarmacTakeOff, terminal, airDep, ConfigPersonnal.nbAvion, ConfigPersonnal.nbPisteArr,
			    ConfigPersonnal.nbPisteDep, nbPlace);
		    new Thread(avion).start();
		}

		airportFrame.setVisible(true);
		airportFrame.pack();
	    }
	});
    }
}
