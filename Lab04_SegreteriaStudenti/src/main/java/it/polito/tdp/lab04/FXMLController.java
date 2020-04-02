package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> bxChoice;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCheck;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void cercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
    	Corso corso = bxChoice.getValue();
    	
    	//Corso vuoto
    	if (corso.getCodins().compareTo("")==0) {
    		txtRisultato.setText("Scegliere un corso!");
    		return;
    	}
    	
    	
    	txtRisultato.setText(this.model.getStudentiIscrittiAlCorso(corso));
    
    }
    
    @FXML 
    void cercaCorsi (ActionEvent event) {
    	txtRisultato.clear();

    	String matricola = txtMatricola.getText();
    	
    	//Matricola non numerica
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero!\n");
    		return;
    	}
    	
    	//Matricola non presente nel database
    	if (this.model.getStudente(Integer.parseInt(matricola)) == null) {
    		txtRisultato.appendText("Inserire matricola valida!");
    		return;
    	}
    	
    	txtRisultato.setText(this.model.getCorsiStudente(Integer.parseInt(matricola)));
    	
    }

    @FXML
    void completaInfo(ActionEvent event) {
    	txtRisultato.clear();

    	String matricola = txtMatricola.getText();
    	
    	//Matricola non numerica
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero!\n");
    		return;
    	}
    	
    	//Matricola non presenta nel databasee
    	if (this.model.getStudente(Integer.parseInt(matricola)) == null) {
    		txtRisultato.appendText("Inserire matricola valida!");
    		return;
    	}
    	
    	txtNome.setText(model.getStudente(Integer.parseInt(matricola)).getNome());
    	txtCognome.setText(model.getStudente(Integer.parseInt(matricola)).getCognome());

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();

    }

    @FXML
    void iscriviStudente(ActionEvent event) {
    	txtRisultato.clear();

    	String matricola = txtMatricola.getText();
    	Corso corso = bxChoice.getValue();
    	
    	//Matricola non numerica
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero nella matricola!");
    		return;
    	}
    	
    	//Matricola non presente nel database
    	if (this.model.getStudente(Integer.parseInt(matricola)) == null) {
    		txtRisultato.appendText("Inserire matricola valida!");
    		return;
    	}
    	
    	//Non ho scelto il corso, ho messo il caso vuoto 
    	if (corso.getCodins().compareTo("")==0) {
    		txtRisultato.setText("Scegliere un corso!");
    		return;
    	}
    	
    
    	if (this.model.isIscritto(Integer.parseInt(matricola), corso)==true) {
    		txtRisultato.setText("Lo studente e' iscritto al corso!");
    	}
    	else {
    		txtRisultato.setText("Lo studente non e' iscritto al corso!");
    	}
    }

    @FXML
    void initialize() {
        assert bxChoice != null : "fx:id=\"bxChoice\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

        
      
    }

	public void setModel(Model model) {
		this.model=model;
		
		bxChoice.getItems().add(new Corso ("", null, "", null));
		bxChoice.getItems().addAll(model.getTuttiICorsi());
	}
}










