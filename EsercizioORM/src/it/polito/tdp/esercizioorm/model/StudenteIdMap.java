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
		//matricola è la chiave della map
	}
	public Studente get(Studente studente) {
		/*
		 *Passo tutto un corso e non solo l'ID perchè se non esiste lo devo creare e ho bisogno di tutte
		 *le informazioni di un corso 
		 */
		
		Studente old = map.get(studente.getMatricola());    //Cerca se esiste o men oquesto studente già
		if (old == null) {
			// nella mappa non c'è questo corso! --> Lo dovrò aggiungere
			map.put(studente.getMatricola(),studente);
			return studente;
		}
		return old; //Se old non è null non voglio creare un oggetto duplicato, esiste già, mi ritorna old
		//Se esiste corso con quel codId lo ritorno sennò lo creo
	}
	
	public void put(Integer matricola, Studente studente) {
		//è l'identity map che viene popolata nel DAO però
		map.put(matricola, studente);
	}
}
