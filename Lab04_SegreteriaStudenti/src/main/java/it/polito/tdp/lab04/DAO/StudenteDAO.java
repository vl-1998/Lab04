package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public List <Corso> getCorsiStudente (Integer matricola){
		
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd FROM corso as c, iscrizione i WHERE c.codins=i.codins AND i.matricola=?";
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso (rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));


				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	
	public Studente getStudente(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);			
			st.setInt(1, matricola);


			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				//Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				
				Studente s = new Studente (rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));

				return s;
			}

			conn.close();
	

		} catch (SQLException e) {

			throw new RuntimeException("Errore Db", e);
		}
		return null;
	}
	
	
}
