package it.polito.tdp.borders.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private SimpleGraph<Country, DefaultEdge> grafo;

	public Model() {
	
	}

	public boolean isDigit(String anno) {
		return anno.matches("\\d{4}");
	}

	public String calcolaConfini(String anno) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		String res = "";
		BordersDAO bdao = new BordersDAO();
		bdao.popolaGrafo(grafo); // 
		for(Country c : grafo.vertexSet()) {
			res+=c.toString()+" "+/*c) numero di stati confinanti (quanti archi partono dal v)*/grafo.degreeOf(c)+"\n";
		}
		
		ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(grafo);
		res+=inspector.connectedSets().size();
		//setOf 2 punto
		
		return res;
	}

	public boolean annoValido(String anno) {
		boolean valido = false;
		if(Integer.parseInt(anno)>=1816 && Integer.parseInt(anno)<=2016) {
			valido = true;
		}
		return valido;
	}

	public static List<Country> getAllCountries() {
		BordersDAO bdao = new BordersDAO();
		List<Country> countries = new LinkedList<Country>(bdao.getTuttiGliStati());
		return countries;
	}

	public String trovaVicini(String anno, Country c) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		String res = "";
		BordersDAO bdao = new BordersDAO();
		bdao.popolaGrafo(grafo);
				
		ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(grafo);
		res+=inspector.connectedSetOf(c);
		
		return res;
	}

}
