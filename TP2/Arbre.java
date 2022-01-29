package TP2;

import TP1.Liste;

public class Arbre {
	protected int racine;
	protected Arbre fg;
	protected Arbre fd;
	protected boolean vide;
	
	
	public void setRacine(int racine) {
		this.racine = racine;
	}

	public void setFg(Arbre fg) {
		this.fg = fg;
	}

	public void setFd(Arbre fd) {
		this.fd = fd;
	}

	public void setVide(boolean vide) {
		this.vide = vide;
	}

	public int getRacine() {
		return racine;
	}

	public Arbre getFg() {
		return fg;
	}

	public Arbre getFd() {
		return fd;
	}

	public boolean isVide() {
		return vide;
	}

	//Constructeurs
	public Arbre(){
		vide = true;
	}
	
	public Arbre (int rac){
		vide = false;
		racine = rac;
		fg = new Arbre();
		fd = new Arbre();
	}
	
	public Arbre(int rac, Arbre fg, Arbre fd){
		racine = rac;
		vide = false;
		this.fg = fg;
		this.fd = fd;
	}
	
	//Accesseurs
	boolean vide(){
		return vide;
	}
	
	public int racine() {
		return racine;
	}
	
	Arbre filsGauche() {
		return fg;
	}
	
	Arbre filsDroit() {
		return fd;
	}
	
	//affichage
	
	void afficheSuffixe() {
		if (!vide() ){
			fd.afficheSuffixe();
			System.out.println(racine());
			fg.afficheSuffixe();
		}
	}
	
	private void afficheIntermediaire(int var) {
		if (!vide() ){
			var++;
			fd.afficheIntermediaire(var);
			for (int i=0; i<var; i++) {
				System.out.print("\t");
			}
			System.out.println(racine());
			fg.afficheIntermediaire(var);
		}
	}
	
	void affiche() {
		if (vide) System.out.print("L'abre est vide");
		afficheIntermediaire(-1);
	}
	
	//fonction
	int somme() {
		if (vide()) {
			return 0;
		}
		else {
			return racine + fg.somme() + fd.somme();
		}
	}
	
	static int max3(int a, int b, int c) {
		if (a >b) {
			if (a > c) {
				return a;				
			}
			else return c;		
		}
		else if (b > c) {
			return b;
		}
		else return c;
	}
	
	int maxi() {
		if (vide) return -99999999;
		else {
			return max3(racine, fg.maxi(), fd.maxi());
		}
	}
	
	int max2(int a, int b) {
		if (a>b) {
			return a;
		}
		else return b;
	}
	
	int profondeur() {
		if (vide()) return -1;
		else return 1 + max2(fg.profondeur(), fd.profondeur());
	}
	
	int nbFeuilles() {
		if (vide) return 0;
		else {
			if (fg.vide() && fd.vide()) return 1;
			else {
				return fg.nbFeuilles() + fd.nbFeuilles();
			}
		}
	}
	
	int nbSommetInterieur() {
		if (vide) return 0;
		else if(fg.vide() && fd.vide()) return 0;
		else return 1 + fg.nbSommetInterieur() + fd.nbSommetInterieur();
	}
	
	
	
	
	public static void main(String[] args) {
		Arbre a4 = new Arbre(4);
		Arbre a5 = new Arbre(5);
		Arbre a6 = new Arbre(6);
		Arbre a7 = new Arbre(7);
		
		Arbre gauche = new Arbre (2, a4, a5);
		Arbre droit = new Arbre (3, a6, a7);
		Arbre tot = new Arbre (1, gauche, droit);
		
		tot.affiche();
		System.out.println("\n" + "Somme des éléments de l'arbre: " + tot.somme());
		System.out.println("Maximim des éléments de l'arbre: " + tot.maxi());
		System.out.println("Profondeur de l'arbre: " + tot.profondeur());
		System.out.println("Nombre de feuilles de l'arbre: " + tot.nbFeuilles());
		System.out.println("Nombre sommet intérieur: " + tot.nbSommetInterieur());
		
		
		tot.affiche();
		
		Liste list = construire(4,1);
		list.afficherListe();
		System.out.println();
		
		System.out.println("Liste contenu dans arbre: " + contenue(list, tot));
		
	}
	
	public static boolean contenue(Liste l, Arbre a) {
		if (l.vide()) return true;
		else if (a.vide() ) return false;
		else if (! (a.racine() == l.tete() )){ //l.appartance(a.racine()) faux
			return contenue(l, a.filsDroit()) || contenue(l, a.filsGauche());
		}
		else {
			return contenue(l.reste(), a.filsGauche()) || contenue(l.reste(), a.filsDroit());
		}
	}
	/*
	public static boolean contenue(Liste l, Arbre a) {
		if (l.vide()) return true;
		else {
			if (a.vide() ) return false;
			else if (l.appartance(a.racine())) {
				return contenue(l.reste(), a.filsDroit());
			}
			else return contenue(l, a.filsGauche());
		}
	}
	*/
	
	public static Liste construire(int...nbVal){
		Liste retour = new Liste();
		Liste temp =  new Liste();
		for (int i=0; i<nbVal.length; i++) {
			temp = retour.prefixer(nbVal[i]);
			retour = temp;
		}
		
		return retour;
	}

	

	
}
