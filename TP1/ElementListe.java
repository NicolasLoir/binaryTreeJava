package TP1;

public class ElementListe {
	private int valeur;
	private ElementListe suivant;
	private boolean vide;
	
	
	ElementListe(int valeur, ElementListe prochain){
		this.valeur = valeur;
		this.suivant = prochain;
	}
	
	ElementListe(){
		suivant = null;
	}
	
	ElementListe(int i){
		valeur = i;
	}
	
	//retourne vrai si la liste suivante est vide, faux sinon
		public boolean vide() {
			if (suivant == null) {
				return true;
			}
			else {
				return false;
			}
		}
		
		//retour la valeur int
		public int tete() {
			return valeur;
		}
		
		//retourne le reste de la liste
		public ElementListe reste() {
			return suivant;
		}
		
		//retourne la liste avec la valeur val devant
		public ElementListe prefixerVal(int i) {
			ElementListe retour = new ElementListe(i, suivant);
			//retour.suivant = suivant;
			//System.out.println(retour.reste().tete());
			return retour;
		}
		
		
		public void afficherListe() {
			if ( !vide() ) {
				System.out.println(this.tete());
				this.reste().afficherListe();
			}
	 	}

		public int getValeur() {
			return valeur;
		}

		public void setValeur(int valeur) {
			this.valeur = valeur;
		}

		public ElementListe getSuivant() {
			return suivant;
		}

		public void setSuivant(ElementListe suivant) {
			this.suivant = suivant;
		}
		
		public static void main(String[] args) {
			ElementListe l1 = new ElementListe(1);
			ElementListe l2 = l1.prefixerVal(2);
			
			System.out.println(l1.vide());
			System.out.println(l2.vide());

			System.out.println(l1.tete());
			System.out.println(l2.tete());

			
		}
}
