/******************************************************************
 * Axel Rieben & Johnny Da Costa
 * Programmation concurrente : laboratoire 3
 * 28 mai 2017
 ******************************************************************/

package airport.com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class JFrameConfig extends JFrame {

    public JFrameConfig() {
	geometry();
	control();
	appearance();
    }

<<<<<<< HEAD
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

    private void geometry()
    {
        // JComponent : Instanciation
        avionLabel = new JLabel("Nombre d'avion : ");
        pisteArrLabel = new JLabel("Nombre de piste d'attérissage");
        pisteDepLabel = new JLabel("Nombre de piste de départ");
        placeLabel = new JLabel("Nombre de place de parking");

        avionSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 50, 1));
        pisteArrSpinner =  new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
        pisteDepSpinner =  new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
        placeSpinner =  new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));

        checkFormatSpinner();

        okButton = new JButton("Valider les configurations");

        // Layout : Specification
        {
            GridLayout gridLayout = new GridLayout(5, 2);
            setLayout(gridLayout);
        }

        // JComponent : add
        add(avionLabel); add(avionSpinner);
        add(pisteArrLabel); add(pisteArrSpinner);
        add(pisteDepLabel); add(pisteDepSpinner);
        add(placeLabel); add(placeSpinner);
        add(okButton);
=======
    private void geometry() {
	// JComponent : Instanciation
	avionLabel = new JLabel("Nombre d'avion : ");
	pisteArrLabel = new JLabel("Nombre de piste d'attérissage");
	pisteDepLabel = new JLabel("Nombre de piste de départ");
	placeLabel = new JLabel("Nombre de place de parking");

	avionSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
	pisteArrSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
	pisteDepSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));
	placeSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 100, 1));

	checkFormatSpinner();

	okButton = new JButton("Valider les configurations");

	// Layout : Specification
	{
	    GridLayout gridLayout = new GridLayout(5, 2);
	    setLayout(gridLayout);
	}

	// JComponent : add
	add(avionLabel);
	add(avionSpinner);
	add(pisteArrLabel);
	add(pisteArrSpinner);
	add(pisteDepLabel);
	add(pisteDepSpinner);
	add(placeLabel);
	add(placeSpinner);
	add(okButton);
>>>>>>> f1ecaa19c4b69d3b972a5dd2db2b5bd5d8a5716a
    }

    private void checkFormatSpinner() {
	JFormattedTextField avionFormat = ((JSpinner.NumberEditor) avionSpinner.getEditor()).getTextField();
	((NumberFormatter) avionFormat.getFormatter()).setAllowsInvalid(false);

	JFormattedTextField pisteArrFormat = ((JSpinner.NumberEditor) pisteArrSpinner.getEditor()).getTextField();
	((NumberFormatter) pisteArrFormat.getFormatter()).setAllowsInvalid(false);

	JFormattedTextField pisteDepFormat = ((JSpinner.NumberEditor) pisteDepSpinner.getEditor()).getTextField();
	((NumberFormatter) pisteDepFormat.getFormatter()).setAllowsInvalid(false);

	JFormattedTextField placeFormat = ((JSpinner.NumberEditor) placeSpinner.getEditor()).getTextField();
	((NumberFormatter) placeFormat.getFormatter()).setAllowsInvalid(false);
    }

    private void control() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void appearance() {
	setSize(600, 400);
	setLocationRelativeTo(null); // frame centrer
	setVisible(true); // last!
    }

    public JButton getOkButton() {
	return okButton;
    }

    // Tools
    JLabel avionLabel;
    JLabel pisteArrLabel;

    public int getAvionSpinner() {
	return (int) avionSpinner.getValue();
    }

    public int getPisteArrSpinner() {
	return (int) pisteArrSpinner.getValue();
    }

    public int getPisteDepSpinner() {
	return (int) pisteDepSpinner.getValue();
    }

    public int getPlaceSpinner() {
	return (int) placeSpinner.getValue();
    }

    JLabel pisteDepLabel;
    JLabel placeLabel;

    JSpinner avionSpinner;
    JSpinner pisteArrSpinner;
    JSpinner pisteDepSpinner;
    JSpinner placeSpinner;

    JButton okButton;

}
