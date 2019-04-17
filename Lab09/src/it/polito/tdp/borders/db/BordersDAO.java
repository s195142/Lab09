package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		
		

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}

	public void popolaGrafo(SimpleGraph<Country, DefaultEdge> grafo) {
		
		String sql = "SELECT c1.CCode as code1, c1.StateAbb as a1, c1.StateNme as n1, c2.CCode as code2, c2.StateAbb as a2 , c2.StateNme as n2 " + 
					 "FROM country AS c1, country AS c2, contiguity co " + 
					 "WHERE c1.CCode = co.state1no AND c2.CCode = co.state2no AND conttype = '1' ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int code1 = rs.getInt("code1");
				String abb1 = rs.getString("a1");
				String nome1 = rs.getString("n1");
				int code2 = rs.getInt("code2");
				String abb2 = rs.getString("a2");
				String nome2 = rs.getString("n2");		
				
				//controllo se ci sono già i vertici - se esiste
				Country c1 = new Country(abb1, code1, nome1);
				Country c2 = new Country(abb2, code2, nome2);
				
				if(!grafo.containsVertex(c1)) {
					grafo.addVertex(c1);
				}
				
				if(!grafo.containsVertex(c2)) {
					grafo.addVertex(c2);
				}
				//in caso di orientamento fai 2 controlli - meglio farli sempre
				// controllo se va da A a B e da B ad A
				
				if(!grafo.containsEdge(c1, c2) && !grafo.containsEdge(c2,c1)) {
					grafo.addEdge(c1, c2);
				}
			}
			
			conn.close();
			
		}catch (SQLException e){
			throw new RuntimeException("Errore DB");
		}
		
	}

	public List<Country> getTuttiGliStati() {
		final String sql = "SELECT * FROM country ";
		
		List<Country> countries = new LinkedList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String abb = rs.getString("StateAbb");
				int cod = rs.getInt("CCode");
				String nome = rs.getString("StateNme");

				Country cou = new Country(abb, cod, nome);
				countries.add(cou);
			}

			return countries;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}
