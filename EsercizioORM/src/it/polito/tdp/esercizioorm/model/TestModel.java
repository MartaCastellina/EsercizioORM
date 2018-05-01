package it.polito.tdp.esercizioorm.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model m = new Model();
		
//		int matricola = 0000;
//		int result = m.getTotCreditiFromStudente(matricola);
//		System.out.println("Tot crediti: " + result);
		List <Studente> result=m.getStudentiFromCorso("01NBAPG");
		for (Studente s:result) {
			System.out.println(s);
		}
	}

}
