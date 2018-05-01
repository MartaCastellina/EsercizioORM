package it.polito.tdp.esercizioorm.model;

import java.util.HashMap;
import java.util.Map;

public class StudenteIdMap {

	private Map<Integer, Studente> map;
	
	public StudenteIdMap() {
		map = new HashMap<>();
	}
	public Studente get(int matricola) {
		return map.get(matricola);
		//matricola � la chiave della map
	}
	public Studente get(Studente studente) {
		/*
		 *Passo tutto un corso e non solo l'ID perch� se non esiste lo devo creare e ho bisogno di tutte
		 *le informazioni di un corso 
		 */
		
		Studente old = map.get(studente.getMatricola());    //Cerca se esiste o men oquesto studente gi�
		if (old == null) {
			// nella mappa non c'� questo corso! --> Lo dovr� aggiungere
			map.put(studente.getMatricola(),studente);
			return studente;
		}
		return old; //Se old non � null non voglio creare un oggetto duplicato, esiste gi�, mi ritorna old
		//Se esiste corso con quel codId lo ritorno senn� lo creo
	}
	
	public void put(Integer matricola, Studente studente) {
		//� l'identity map che viene popolata nel DAO per�
		map.put(matricola, studente);
	}
}
