package TP1;

public class Liste {
	private int tete;
	private Liste reste;
	private boolean vide;
	
	
	public Liste(){
		vide = true;
	}
	
	public Liste(int i, Liste suivant){
		tete = i;
		reste = suivant;
		vide = false;
	}

	public boolean vide() {
		return vide;
	}
	
	public int tete() {
		return tete;
	}
	
	public Liste reste() {
		return reste;
	}
	
	public Liste prefixer(int i) {
		Liste retour = new Liste(i, this);
		return retour;
	}
	
	public void afficherListe() {
		if ( !vide() ) {
			System.out.print(this.tete() + " ");
			this.reste().afficherListe();
		}
 	}
	
	public static Liste construire(int...nbVal){
		Liste retour = new Liste();
		Liste temp =  new Liste();
		for (int i=0; i<nbVal.length; i++) {
			temp = retour.prefixer(nbVal[i]);
			retour = temp;
		}
		
		return retour;
	}
	
	public static void main(String[] args) {
		Liste l1 = new Liste();
		Liste l2 = l1.prefixer(2);
		Liste l3 = l2.prefixer(3);
		//l3.afficherListe();
		//System.out.println();
		
		Liste list = construire(1, 2, 3, 4, 5);
		list.afficherListe();
		System.out.println();
		
		System.out.print("Somme de la liste : ");
		System.out.println(list.somme());
		
		System.out.print("Nombre d'élements dans la liste : ");
		System.out.println(list.nbElmt());
		
		System.out.print("Somme alterne de la liste : ");
		System.out.println(list.sommeAlt()); //5-4+3-2+1
		
		System.out.print("Recherche du nombre 4 dans la liste : ");
		System.out.println(list.appartance(4));
		
		System.out.print("Recherche du nombre 6 dans la liste : ");
		System.out.println(list.appartance(6));
		
		System.out.print("Recherche d'une sous liste contenant le nombre 3 dans la liste : ");
		list.rechercher(3).afficherListe(); System.out.println();
		
		System.out.print("Recherche d'une sous liste contenant le nombre 6 dans la liste : ");
		list.rechercher(6).afficherListe(); System.out.println();
		
		System.out.print("Inserer 7 a la fin de la liste : ");
		list.inserer(7).afficherListe(); System.out.println(); System.out.println();
		
		System.out.print("Affichage d'une liste croissante : ");
		Liste croissante = construire(10, 8, 6, 4, 2);
		croissante.afficherListe(); System.out.println();
		
		System.out.print("Inserer 5 dans cette liste : ");
		croissante.insererOrd(5).afficherListe(); System.out.println();
		
		System.out.print("Supprimer 8 dans cette liste : ");
		croissante.supprimerOrd(8).afficherListe(); System.out.println(); System.out.println();
		
		System.out.print("Test des listes : "); 
		list.afficherListe();
		System.out.print(" et " );
		croissante.afficherListe();
		System.out.print(" identiques : "); System.out.print(croissante.identique(list));
		System.out.println();
		
		System.out.print("Test des listes : "); 
		list.afficherListe();
		System.out.print(" et " );
		list.afficherListe();
		System.out.print(" identiques : "); System.out.print(list.identique(list));
		System.out.println(); System.out.println();
		
		System.out.print("Test des listes : "); 
		list.afficherListe();
		System.out.print(" et " );
		croissante.afficherListe();
		System.out.print(" contenu : "); System.out.print(croissante.contenue(list));
		System.out.println();
		
		System.out.print("Test des listes : "); 
		list.afficherListe();
		System.out.print(" et " );
		list.afficherListe();
		System.out.print(" contenu : "); System.out.print(list.contenue(list));
		System.out.println();
		
		Liste croissante2 = construire(10,8,6);
		
		System.out.print("Test des listes : "); 
		croissante2.afficherListe();
		System.out.print(" et " );
		croissante.afficherListe();
		System.out.print(" contenu : "); System.out.print(croissante.contenue(croissante2));
		System.out.println();
		
		System.out.print("Test des listes : "); 
		croissante.afficherListe();
		System.out.print(" et " );
		croissante2.afficherListe();
		System.out.print(" contenu : "); System.out.print(croissante2.contenue(croissante));
		System.out.println();
		
		
		Liste liste2 = construire(8,5,4,2);
		Liste liste3 = construire(8,5,4,2);
		System.out.println(liste2.contenue(liste3));
		
	}
	
	public int somme() {
		if (vide()) {
			return 0;
		}
		else {
			return tete() + reste().somme();
		}
	}
	
	public int nbElmt() {
		if (vide()) {
			return 0;
		}
		else {
			return 1 + reste().nbElmt();
		}
	}
	
	public int sommeAlt() {
		if (vide()) {
			return 0;
		}
		else {
			return tete() - reste().sommeAlt();
		}
	}
	
	public boolean appartance(int val) {
		if (vide()) {
			return false;
		}
		else {
			if (tete()==val) {
				return true;
			}
			else {
				return reste().appartance(val);
			}
		}
	}
	
	public Liste rechercher(int val) {
		if (vide()) {
			return this;
		}
		else {
			if (tete()==val) {
				return this;
			}
			else {
				return reste.rechercher(val);
			}
		}
	}
	
	public Liste inserer(int val) {
		if (vide()) {
			return prefixer(val);
		}
		else {
			return reste.inserer(val).prefixer(tete());
		}
	}
	
	public Liste insererOrd(int val) {
		if (vide()) {
			return prefixer(val);
		}
		else {
			if (tete() < val) {
				return reste.insererOrd(val).prefixer(tete());
			}
			else {
				return prefixer(val);
			}
		}
	}
	
	public Liste supprimerOrd(int val) {
		if (vide()) {
			return this;
		}
		else {
			if (val == tete()) {
				return reste();
			}
			else {
				if (val < tete()) { //inférieur ou egal plus optimal ?
					return this;
				}
				else {
					return reste().supprimerOrd(val).prefixer(tete());
				}
			}
		}
	}
	
	public boolean identique(Liste autreListe) {
		if (nbElmt() != autreListe.nbElmt()) {
			return false;
		}
		else {
			if (vide()) {
				return true;
			}
			else {
				if (tete() != autreListe.tete()) {
					return false;
				}
				else {
					return reste().identique(autreListe.reste);
				}
			}
		}
	}
	
	public boolean contenueMiguel(Liste autreListe) {
		if (autreListe.vide()) {
			return true;
		}
		else if (vide()) {
			return false;
		}
		else if (tete() == autreListe.tete() || reste.contenueMiguel(autreListe)) {
			return contenueMiguel(autreListe.reste());
		}
		else {
			return false;
		}
	}
	
	public boolean contenue(Liste autreListe) {
		if (autreListe.vide()) {
			return true;
		}
		else {
			if (vide()) {
				return true;
			}
			else {
				if (autreListe.appartance(tete())) {
					return reste.contenue(autreListe);
				}
				else {
					return false;
				}
			}
		}
		
	}

}
