package it.polito.tdp.esercizioorm.model;

import java.util.HashMap;
import java.util.Map;

public class CorsoIdMap {

	private Map<String, Corso> map;
	
	public CorsoIdMap() {
		map = new HashMap<>();
	}
	public Corso get (String codins) {
		return map.get(codins);
	}
	public Corso get(Corso corso) {
		/*
		 *Passo tutto un corso e non solo l'ID perch� se non esiste lo devo creare e ho bisogno di tutte
		 *le informazioni di un corso 
		 */
		
		Corso old = map.get(corso.getCodIns());    //Corso old � quello che devo prendere dalla mappa
		if (old == null) {
			// nella mappa non c'� questo corso! --> Lo dovr� aggiungere
			map.put(corso.getCodIns(), corso);
			return corso;
		}
		return old; //Se old non � null non voglio creare un oggetto duplicato, esiste gi�, mi ritorna old
		//Se esiste corso con quel codId lo ritorno senn� lo creo
	}
	
	public void put(String codins, Corso corso) {
		//� l'identity map che viene popolata nel DAO per�
		map.put(codins, corso);
	}
}
