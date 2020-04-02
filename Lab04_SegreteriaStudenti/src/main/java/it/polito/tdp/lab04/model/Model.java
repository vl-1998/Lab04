package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO dao;
	private StudenteDAO daos;

	public Model() {
		dao = new CorsoDAO();
		daos = new StudenteDAO();
	}
	
	public List <Corso> getTuttiICorsi (){
				
		return dao.getTuttiICorsi();
	}
	
	public Studente getStudente(int matricola) {
		return daos.getStudente(matricola);
	}
	
	public String getStudentiIscrittiAlCorso (Corso corso){
		String sTemp= "";
		List <Studente> studenti = dao.getStudentiIscrittiAlCorso(corso);
		
		for (Studente s : studenti) {
				if (sTemp=="") {
					sTemp= s.toString();
				}
				else {
					sTemp+= "\n"+s.toString();
				}
			}
		
		return sTemp;
				
	}
	
	public String getCorsiStudente (Integer matricola){
		String cTemp= "";
		List <Corso> corsi = daos.getCorsiStudente(matricola);
		
		for (Corso c : corsi) {
				if (cTemp=="") {
					cTemp= String.format("%-20s %-20s %-20s %-20s", c.getCodins(),c.getNome(),c.getCrediti(),c.getPd());
				}
				else {
					cTemp+= "\n"+String.format("%-20s %-20s %-20s %-20s", c.getCodins(),c.getNome(),c.getCrediti(),c.getPd());
				}
			}
		
		return cTemp;
		
	}
	
	public boolean isIscritto (Integer matricola, Corso corso) {
		return dao.isIscritto(matricola, corso);
	}	

}
