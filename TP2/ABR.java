package TP2;

//import TP2.Arbre;

public class ABR extends Arbre{

	ABR(){
		super();
	}
	
	void creation(int val){
		racine = val;
		vide = false;
		//super.setVide(false);
		fg = new ABR();
		fd = new ABR();
	}
	
	void ajout(int val) {
		if (vide()) {
			creation(val);
		}
		else if (val <= racine()) {
			((ABR) filsGauche()).ajout(val);
		}
		else {
			((ABR) filsDroit()).ajout(val);
		}
		
	}
	
	public static ABR construireABR(int...nbVal) {
		ABR retour = new ABR();
		for (int i=0; i<nbVal.length; i++) {
			retour.ajout(nbVal[i]);
		}
		return retour;
	}
	
	public static void main(String[] args) {
		ABR a = new ABR();
		a = construireABR(12, 6, 3, 1, 2, 4, 15, 14, 20, 8);
		
		a.affiche();
		System.out.println("Minimum de l'arbe: "+ a.mini());
		System.out.println("Maximum de l'arbe: "+ a.maxi());
		System.out.println("Nombre d'occurence d'une val dans l'arbre: " + a.occurence(1));
		System.out.println("Predeceseur d'un nombre dans l'abre: " + a.predecesseur(4));
		System.out.println("Sucesseur d'un nombre dans l'abre: " + a.sucesseur(8));
	}
	
	
	int mini() {
		if (vide) return 99999999;
		else {
			if (filsGauche().vide()) return racine;
			else return ((ABR) filsGauche()).mini();
		}
	}
	
	int maxi() {
		if (vide) return -99999999;
		else {
			if (filsDroit().vide()) return racine;
			else return ((ABR) filsDroit()).maxi();
		}
	}
	
	int occurence(int n) {
		//retourne le nombre d'occurence de l'entier n
		if (vide) return 0;
		else {
			if (n==racine) return 1 + ((ABR) fg).occurence(n);
			else {
				if (n<racine) return ((ABR) fg).occurence(n);
				else return ((ABR) fd).occurence(n);
			}
		}
	}
	
	int predecesseur(int val) {
		//retourne la valeur plus grande mais plus petite que val dans l'abre
		return pred(val, 9999);
	}
	
	
	int pred(int val, int souvenir) {
		if (vide) return 9999;
		if (val == racine) {
			if (!fg.vide) return fg.maxi();
			else return souvenir;
		}
		else {
			if (racine > val) return ((ABR) fg).pred(val, souvenir);
			else return ((ABR) fd).pred(val, racine);
		}
	}
	
	int sucesseur(int val) {
		//retourne la valeur juste au dessus de val dans l'abre
		return suc(val, 9999);
	}
	
	int suc(int val, int souvenir) {
		if (vide) return -9999;
		if (val == racine) {
			if (!fd.vide) return ((ABR) fd).mini();
			else return souvenir;
		}
		else {
			if (racine < val) return ((ABR) fd).suc(val, souvenir);
			else return ((ABR) fg).suc(val, racine);
		}
	}
	
	
	
	
	
}
