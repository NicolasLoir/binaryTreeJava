package TP3Glouton;

import TP3Glouton.AbreHoffman;

public class ListeHoffman {
	private AbreHoffman tete;
	private ListeHoffman reste;
	private boolean vide;
	
	
	public ListeHoffman() {
		vide = true;
	}
	
	public ListeHoffman(AbreHoffman i, ListeHoffman suivant){
		tete = i;
		reste = suivant;
		vide = false;
	}
	
	public ListeHoffman reste() {
		return reste;
	}
	
	public boolean vide() {
		return vide;
	}
	
	public AbreHoffman tete() {
		return tete;
	}
	
	public ListeHoffman prefixer(AbreHoffman a) {
		return new ListeHoffman(a, this);	
	}
	
	public ListeHoffman insererOrd(AbreHoffman a) {
		if (vide()) {
			return prefixer(a);
		}
		else {
			if (tete.getFreq() < a.getFreq()) {
				return reste.insererOrd(a).prefixer(tete);
			}
			else {
				return prefixer(a);
			}
		}
	}
	
	
	public void afficherListeHoff() {
		if ( !vide() ) {
			System.out.print("("+tete().getFreq() + "," + tete().getC() + ") ");
			reste.afficherListeHoff();
		}
		else System.out.println("");
 	}
	
	public static ListeHoffman construirePrefixer(int [] frequence, char [] c) {
	// pour faciliter la construction
	// prend en parametre des entier, correspondant aux valeurs des racines d'un arbre
		ListeHoffman retour = new ListeHoffman();
		if (frequence.length != c.length) {
			System.out.println("Entrez deux listes de meme taille");
			return retour;
		}
		else {
			
			ListeHoffman temp =  new ListeHoffman();
			for (int i=0; i<frequence.length; i++) {
				AbreHoffman arbreHoffTemp = new AbreHoffman(frequence[i], c[i]);
				temp = retour.prefixer(arbreHoffTemp);
				retour = temp;
			}
			return retour;
			}
	}
	
	public static ListeHoffman construireInsererOrd(int [] frequence, char [] c) {
		// pour faciliter la construction
		// prend en parametre des entier, correspondant aux valeurs des racines d'un arbre
		ListeHoffman retour = new ListeHoffman();
		if (frequence.length != c.length) {
			System.out.println("Entrez deux listes de meme taille");
			return retour;
		}
		else {
			
			ListeHoffman temp =  new ListeHoffman();
			for (int i=0; i<frequence.length; i++) {
				AbreHoffman arbreHoffTemp = new AbreHoffman(frequence[i], c[i]);
				temp = retour.insererOrd(arbreHoffTemp);
				retour = temp;
			}
			return retour;
			}
		}
	
	public static void main(String[] args) {

		int [] freq = {3, 5, 1, 4, 2};
		char [] c = {'a', 'b', 'c', 'd', 'e'};
		
		ListeHoffman test = construirePrefixer(freq, c);
		ListeHoffman test2 = construireInsererOrd(freq, c);
		
		test.afficherListeHoff();
		test2.afficherListeHoff();
		
		
		
	}
	
}
