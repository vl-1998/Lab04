package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				Corso c = new Corso (rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*/*
	 * Dato un codice insegnamento, ottengo il corso
	 
	public void getCorso(Corso corso) {
		// TODO
	}*/

	
	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS FROM studente as s, iscrizione i WHERE s.matricola=i.matricola AND i.codins=?";
		List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				
				Studente s = new Studente (matricola, cognome, nome, CDS);


				studenti.add(s);
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	
	public boolean isIscritto (Integer matricola, Corso corso) {
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS FROM studente as s, corso as c, iscrizione i WHERE s.matricola=i.matricola AND c.codins=i.codins AND c.codins=? AND s.matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				
				Studente s = new Studente (matricola, cognome, nome, CDS);

				return true;
			}

			conn.close();
			

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}
		return false;
		
	}
	
	
	
	
	
	
	

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
	

}
