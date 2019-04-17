/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader
	
	@FXML // fx:id="comboStato"
	private ComboBox<Country> comboStato; // Value injected by FXMLLoader

	@FXML // fx:id="btnVicini"
	private Button btnVicini; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		String anno = txtAnno.getText();
		
		if(anno!=null && !anno.isEmpty()) {
    		if(model.isDigit(anno)) {		// metodo che mi dice se è un intero di 4 cifre.
    								       // posso anche scriverlo qui
    			if(model.annoValido(anno)) {
    				String risultato = model.calcolaConfini(anno);
        			txtResult.setText(risultato);
    			}else {
    				showMessage("Errore: inserire un anno dal 1816 al 2016");
    			}    			
    		}else {
    			showMessage("Errore: anno non valido");
    		}
    	}else {
    		showMessage("Errore: Inserire un numero");
    	}
	}
	
	
    @FXML
    void doTrovaVicini(ActionEvent event) {
    	String anno = txtAnno.getText();
    	Country c = comboStato.getValue();
		
    if(c!=null) {
		if(anno!=null && !anno.isEmpty()) {
    		if(model.isDigit(anno)) {		// metodo che mi dice se è un intero di 4 cifre.
    								       // posso anche scriverlo qui
    			if(model.annoValido(anno)) {
    				String risultato = model.trovaVicini(anno, c);
        			txtResult.setText(risultato);
    			}else {
    				showMessage("Errore: inserire un anno dal 1816 al 2016");
    			}    			
    		}else {
    			showMessage("Errore: anno non valido");
    		}
    	}else {
    		showMessage("Errore: Inserire un numero");
    	}
    }else {
    	showMessage("Errore: selezionare uno Stato");
    }

    }


	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert comboStato != null : "fx:id=\"comboStato\" was not injected: check your FXML file 'Borders.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		
		comboStato.getItems().addAll(Model.getAllCountries());
	}

	public void setModel(Model model) {
		this.model=model;		
	}
	
	private void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.show();		
	}
	
}
