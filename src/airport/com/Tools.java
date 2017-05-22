/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 28 mai 2017
 ******************************************************************/

package airport.com;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tools {

    static int nbAvion = 0; // nombre d'avion
    static int nbPisteArr = 0;// pistes d'atterrisage
    static int nbPisteDep = 0;// "" de depart
    static int nbPlace = 0; // parking

    public static ImageIcon scaleImage(ImageIcon icon, int w, int h) {
	int nw = icon.getIconWidth();
	int nh = icon.getIconHeight();
	if (icon.getIconWidth() > w) {
	    nw = w;
	    nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
	}
	if (nh > h) {
	    nh = h;
	    nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
	}
<<<<<<< HEAD
	
	public static final int TEST_TIME = 1000;
=======
	return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }

    public static void simulateTime(int ms) {
	try {
	    Thread.sleep(ms);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public static final int TEST_TIME = 2000;
>>>>>>> f1ecaa19c4b69d3b972a5dd2db2b5bd5d8a5716a

}
