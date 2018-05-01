package it.polito.tdp.esercizioorm.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.esercizioorm.dao.CorsoDAO;
import it.polito.tdp.esercizioorm.dao.StudenteDAO;

public class Model {

	private CorsoDAO cdao;
	private StudenteDAO sdao;
	
	private List<Corso> corsi;
	private List<Studente> studenti;
	
	private CorsoIdMap corsomap;
	private StudenteIdMap studentemap;
	//Bisogna inserire un'identity map per ogni JavaBean
	
	public Model() {
		
		cdao = new CorsoDAO();
		sdao = new StudenteDAO();
		
		corsomap = new CorsoIdMap();
		studentemap=new StudenteIdMap();
				
				
				
				
		corsi = cdao.getTuttiCorsi(corsomap); //Quando creo i corsi li vado già a inserire nella mappa 
		//è metodo che mi restituisce tutti i corsi, ma li crea se è necessario
		//Dovrei fare lo stesso con studenti, ma non chiamerò mai più di una volta la lista degli studenti, sì per i corsi
		//perchè nostro programma è strutturato così
		System.out.println(corsi.size());
		
		studenti = sdao.getTuttiStudenti(studentemap);
		System.out.println(studenti.size());
		
		for (Studente s : studenti) {
			cdao.getCorsiFromStudente(s, corsomap);
		}
	
	for (Corso c:corsi) {
		sdao.getStudentiFromCorso(c,studentemap);
	
		}
	}
	public List<Studente>getStudentiFromCorso(String codins){
		//sono sicuro che tutti i corsi abbiano riferimento a identitymap
		Corso c=corsomap.get(codins);
		if(c==null) {
			return new ArrayList<Studente>();
			//così non va in crash, itera su lista vuota
		}
		return c.getStudenti();
	}
	public List <Corso> getCorsiFromStudente(int matricola){
		Studente s=studentemap.get(matricola);
		if(s==null) {
			return new ArrayList<Corso>();
			//NB posso anche ritronare null ma poi
			//mi devo ricordare di controllare
		}
		return s.getCorsi();
	}
	
	
	public int getTotCreditiFromStudente(int matricola) {
		int sum=0;
		for(Studente s:studenti) {
		
		if (s.getMatricola()==matricola) {
			for (Corso c:s.getCorsi()) {
				sum+=c.getCrediti();
			}
			return sum;
		}
		}
		return -1; //controllo nel testmodel
		//se ritorna -1 vuol dire che non ha trovato nessuno studente
	}
public boolean iscriviStudenteACorso(int matricola, String codins) {
	Corso corso=corsomap.get(codins);
	Studente studente=studentemap.get(matricola);
	
	if (studente==null||corso==null) {
		//non posso iscrivere uno studente a un corso
		return false;
	}
	
	//Aggiorno il DB
	boolean result=sdao.iscriviStudenteACorso(studente,corso);
	//Il model dovrebbe sempre passare gli oggetti al DAO
	//Il model non ha la visione di come vengano effettivamente salvati
	//i dati, è meglio se passiamo gli oggetti
	if (result) {
		//Aggiornamento del DB è andato a buon fine
		//Aggiorno i riferimenti in memoria
		if(studente.getCorsi().contains(corso)) {
			studente.getCorsi().add(corso);
		}
		if(!corso.getStudenti().contains(studente)) {
			corso.getStudenti().add(studente);
		}
		return true;
		
	}
	return false;
}
	
}
