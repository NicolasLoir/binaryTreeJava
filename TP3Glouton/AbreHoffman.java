package TP3Glouton;

import TP3Glouton.ListeHoffman;

import java.util.ArrayList;

public class AbreHoffman {

	private AbreHoffman fg;
	private AbreHoffman fd;
	private boolean vide;
	private char c; //nom de la lettre
	private int freq; 
	
	AbreHoffman(){
		vide = true;
	}
	
	AbreHoffman(AbreHoffman filsDroit, AbreHoffman filsGauche){
		fg = filsDroit;
		fd = filsGauche;
		vide = false;
		freq = filsDroit.getFreq() + filsGauche.getFreq();
	}
	
	public AbreHoffman(int freq, char c2) {
		vide = false;
		fg = new AbreHoffman();
		fd = new AbreHoffman();
		this.c = c2;
		this.freq = freq;
	}

	
	static AbreHoffman creation(ListeHoffman l) { //correspond a Hoffman
		if (! l.reste().vide()) {
			AbreHoffman z = new AbreHoffman(l.tete(), l.reste().tete());
			return creation(l.reste().reste().insererOrd(z));			}
		else return (l.tete());
	}

	public static void main(String[] args) {
		
		int [] freq = {5, 7, 8, 10, 15, 30};
		char [] c = {'c', 'b', 'd', 'f', 'a', 'e'};
		
		ListeHoffman liste = TP3Glouton.ListeHoffman.construireInsererOrd(freq, c);
		liste.afficherListeHoff(); System.out.println();
		
		AbreHoffman a = creation(liste);
		a.affiche();
	
		ArrayList<String> lettre = new ArrayList<String>();
		ArrayList<String> code = new ArrayList<String>();
		String chemin = "";
		
		a.codeLettreArbre(lettre, code, chemin);
		afficherLettreCodeArbre(lettre, code);
		
		System.out.println(a.codage("faded"));		
		System.out.println(a.decodage("1011111000100"));
		
		System.out.println(a.codage("abed"));		
		System.out.println(a.decodage("111110"));
		
		
		
	}
	
	private void codeLettreArbre(ArrayList<String> lettre, ArrayList<String> code, String chemin) {
		if (!vide) {
			if (fg.vide && fd.vide) { //on est sur une feuille donc il y a un mot
				lettre.add(Character.toString(c));
				code.add(chemin);
			}
			else {
				fd.codeLettreArbre(lettre, code, chemin + "1");
				fg.codeLettreArbre(lettre, code, chemin + "0");
			}
		}
	}
	
	public String codage(String motAcoder) {
		ArrayList<String> lettre = new ArrayList<String>();
		ArrayList<String> code = new ArrayList<String>();
		codeLettreArbre(lettre, code, ""); //permet d'initialiser le tableau de lettre et code associé
		String retour = "";
		for (int i = 0; i< motAcoder.length(); i++) { //pour chaque lettre du mot a coder
			int numLettre = lettre.indexOf(motAcoder.substring(i, i+1)); //on cherche dans notre tableau de lettre l'indice 
			//correspondant à la lettre en question
			retour += code.get(numLettre); //on stock dans notre retour le code correspondant a la lettre
		}
		
		return retour;
	}
	
	public String decodage(String motAdecoder) {
		String retour ="";
		ArrayList<String> lettre = new ArrayList<String>();
		ArrayList<String> code = new ArrayList<String>();
		codeLettreArbre(lettre, code, ""); //permet d'initialiser le tableau de lettre et code associé
		for (int i = 0; i< motAdecoder.length(); i++) { //pour chaque lettre du mot a coder
			
			String codeLettre = motAdecoder.substring(i, i+1); //on prends les lettres du motAdecoder
			
			while(code.indexOf(codeLettre) == -1 && i<motAdecoder.length()-1) { //tant que la suite de chiffre (de type string)
																		// ne correspond pas a un code du tableau "code" ... 
				i++;
				codeLettre += motAdecoder.substring(i, i+1); //  ... on ajoute un nouveau chiffre a codeLettre
			}
			
			int numeroLettre = code.indexOf(codeLettre); // lorsqu'une séquence correspond a une lettre ...
			
			if (numeroLettre != - 1) retour += lettre.get(numeroLettre); // ... on ajoute cette lettre
			else return ("Erreur: Votre code ne correspond pas au dictionnaire"); //si la derniere séquence de nombre ne correspond
																				// pas a une lettre

		}
		return retour;
	}
	

	private void afficheIntermediaire(int var) {
		if (!vide){
			var++;
			fd.afficheIntermediaire(var);
			for (int i=0; i<var; i++) {
				System.out.print("\t");
			}
			System.out.println("(" + freq + "," + c + ") ");
			fg.afficheIntermediaire(var);
		}
	}
	
	public void affiche() {
		if (vide) System.out.print("L'abre est vide");
		afficheIntermediaire(-1);
	}
	

	public static void afficherListe(int [] l) {
		for (int truc : l) {
			System.out.print(truc + " ");
		}
		System.out.println();
	}
	
	public static void afficherLettreCodeArbre(ArrayList<String> lettre, ArrayList<String> code) {
		if (lettre.size() != code.size()) System.out.println("Erreur, entrez deux tableau de meme taille");
		else {
			for (int i = 0; i < lettre.size(); i++) {
				System.out.print("(" + lettre.get(i) + ", " + code.get(i) + ") " );
			}
		}
		System.out.println();
	}
	
	public static void afficherArrayString(ArrayList<String> liste) {
		for (String truc : liste) {
			System.out.println(truc);
		}
	}
	
	public char getC() {
		return c;
	}

	public int getFreq() {
		return freq;
	}

}
