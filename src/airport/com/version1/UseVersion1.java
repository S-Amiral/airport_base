/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 29 mai 2017
 ******************************************************************/

package airport.com.version1;

public class UseVersion1 {

	static String[] codePlane = { "3B147", "B3291", "6B239", "B1086", "780B4", "32A64", "17A69", "2A431", "647B8",
			"349A8", "536B8", "9103A", "9B210", "139A4", "96B01", "207B9", "830B6", "8435A", "7301B", "1076B", "5281B",
			"8A521", "3B806", "B6842", "B6238", "7B816", "A9437", "849A3", "60B18", "094B6", "4709B", "36A84", "085A3",
			"0718B", "80B21", "0A369", "5290A", "370B4", "021A3", "84A02", "052A6", "B6350", "630B5", "8B903", "1398B",
			"2693A", "902A6", "51A20", "971A5", "A7891" };


	public static void main(String[] args) {
		int nbAvion = 20; // nombre d'avion
		int nbPisteArr = 5;// pistes d'atterrisage
		int nbPisteDep = 2;// "" de depart
		int nbPlace = 4; // parking

		AirportFramePersonnal airportFrame = new AirportFramePersonnal(nbPisteArr, nbPisteDep, nbPlace, nbAvion);

		/**
		 * Tampon personnel
		 */
        CircularBuffer_I<AvionPersonnal> airArr = new CircularBuffer(nbAvion);
        CircularBuffer_I<AvionPersonnal> tarmacLand = new CircularBuffer(nbPisteArr);
        CircularBuffer_I<AvionPersonnal> tarmacTakeOff = new CircularBuffer(nbPisteDep);
        CircularBuffer_I<AvionPersonnal> terminal = new CircularBuffer(nbPlace);
        CircularBuffer_I<AvionPersonnal> airDep = new CircularBuffer(nbAvion);


		for (int i = 0; i < nbAvion; i++) {
			AvionPersonnal avion = new AvionPersonnal(airportFrame, codePlane[i], airArr, tarmacLand, tarmacTakeOff, terminal, airDep,
					nbAvion, nbPisteArr, nbPisteDep, nbPlace);
			new Thread(avion).start();
		}



		airportFrame.setVisible(true);
		airportFrame.pack();
	}

}
