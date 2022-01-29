package arbreNaire;

import java.util.ArrayList;

public class AbrN {

	char lettre;
	AbrN fils;
	AbrN frere;
	boolean vide;

	public AbrN(){
		vide = true;
	}
	
	public AbrN(char rac){
			vide = false;
			lettre = rac;
			fils = new AbrN();
			frere = new AbrN();
		}

	public void setFrere(AbrN frere) {
		this.frere = frere;
	}
	
	public void setFils(AbrN fils) {
		this.fils = fils;
	}
	
	private void afficheIntermediaire(int var) {
		if (!vide){
			var++;
			fils.afficheIntermediaire(var);
			for (int i=0; i<var; i++) {
				System.out.print("\t");
			}
			System.out.println(lettre);
			frere.afficheIntermediaire(var);
		}
	}
	
	void affiche() {
		if (vide) System.out.print("L'abre est vide");
		System.out.println("\n*********************************** Affichage de l'arbre ***********************************");
		afficheIntermediaire(-1);
		System.out.println("********************************************************************************************\n");

	}

	public static void main(String[] args) {		
		//branche de droite
		AbrN a = new AbrN('a');
		AbrN c = new AbrN('c');
		AbrN j = new AbrN('j');
		AbrN jo = new AbrN('o');
		AbrN jou = new AbrN('u');
		AbrN joue = new AbrN('e');
		AbrN jouet = new AbrN('t');
		AbrN joueu = new AbrN('u');
		AbrN joueur = new AbrN('r');
		a.setFrere(c);
		c.setFrere(j);
		j.setFils(jo);
		jo.setFils(jou);
		jou.setFils(joue);
		joue.setFils(jouet);
		jouet.setFrere(joueu);
		joueu.setFils(joueur);
		
		//branche du centre
		AbrN co = new AbrN('o');
		AbrN com = new AbrN('m');
		AbrN come = new AbrN('e');
		AbrN comed = new AbrN('d');
		AbrN comedi = new AbrN('i');
		AbrN comedie = new AbrN('e');
		AbrN comedien = new AbrN('n');
		c.setFils(co);
		co.setFils(com);
		com.setFils(come);
		come.setFils(comed);
		comed.setFils(comedi);
		comedi.setFils(comedie);
		comedie.setFils(comedien);
		
		//branche de gauche
		AbrN ac = new AbrN('c');
		AbrN act = new AbrN('t');
		AbrN acti = new AbrN('i');
		AbrN actio = new AbrN('o');
		AbrN action  = new AbrN('n');
		a.setFils(ac);
		ac.setFils(act);
		act.setFils(acti);
		acti.setFils(actio);
		actio.setFils(action);
		AbrN acte = new AbrN('e');
		AbrN acteu = new AbrN('u');
		AbrN acteur = new AbrN('r');
		acti.setFrere(acte);
		acte.setFils(acteu);
		acteu.setFils(acteur);
		AbrN activ = new AbrN('v');
		AbrN active = new AbrN('e');
		AbrN activer = new AbrN('r');
		actio.setFrere(activ);
		activ.setFils(active);
		active.setFils(activer);
			
		
		
		char lettre = 'p';
		System.out.println("Algo du cours: L'arbre contient la lettre " + lettre + " : "  +  a.chercher(lettre) );
		String mot = "joue";
		System.out.println("Algo arbre contient mot: L'arbre contient le mot " + mot + " : " +  a.contient(mot));
		
		ArrayList<String> dictionnaire = new ArrayList<String>();
		a.majDictionnaire(dictionnaire);
		
		afficherDico(dictionnaire);
		
		

		a.affiche();
		System.out.println("--------- Ensemble des combinaisons de lettres ----------"); 
		a.afficherTout(""); 
		System.out.println("---------------------------------------------------------\n");
		
		String motAjouter = "acte";
		rechercheChemin(motAjouter, dictionnaire);
		
		//a.ajouterMotTemp(motAjouter, "", dictionnaire);
		
		//a.affiche();
		//a.majDictionnaire(dictionnaire);
		//afficherDico(dictionnaire);
		
	}
	
	public static String rechercheChemin(String motAjouter, ArrayList<String> dictionnaire) {
		System.out.println("Recherche du chemin pour le mot " + motAjouter);
		int tailleMot = motAjouter.length();
		boolean trouve = false;
		while (tailleMot > 0 && !trouve) {
			for (String motDico : dictionnaire) {
				System.out.println("nbLettrres: " + tailleMot + " " + motAjouter.substring(0, tailleMot) + " " + motDico.substring(0, tailleMot));
				trouve = motAjouter.substring(0, tailleMot).equalsIgnoreCase(motDico.substring(0, tailleMot));
				if (trouve) {
					System.out.println("Chemin trouve le plus proche: " + motAjouter.substring(0, tailleMot));
					break;
				}
			}
			tailleMot -= 1;
		}
		return motAjouter.substring(0, tailleMot);
		
	}
	
	private void afficherTout(String blank) { //affiche toutes les combinaisons de lettres possibles 
		if (frere != null) {
			frere.afficherTout(blank);		
		}
		if (fils != null) {
			fils.afficherTout(blank + lettre);
			if (fils.vide) System.out.print("\n");
		}
		else {
			System.out.print((blank + lettre) + " ");
		}	
	}
	
	private void ajouterMotTemp(String motAjouter, String blank, ArrayList<String> dictionnaire) {
		String cheminCherche = rechercheChemin(motAjouter, dictionnaire);
		if (frere != null) {
			frere.ajouterMotTemp(motAjouter, blank, dictionnaire);		
		}
		if (fils != null) {
			fils.ajouterMotTemp(motAjouter, blank, dictionnaire);
			if (fils.vide) System.out.print("\n");
		}
		else {
			//si on trouve le mot
			if ( (blank + lettre)== cheminCherche ) { //on ajoute les dernieres lettre correspondantes
				int tailleMot = motAjouter.length();
				int tailleMotChemin = cheminCherche.length();
				int nbLettreA_inserer = tailleMot - tailleMotChemin;
				if (nbLettreA_inserer > 0) { //sinon le mot est deja present
					//String lTemp = motAjouter.substring(tailleMotChemin, tailleMotChemin+1);
					char l = motAjouter.charAt(tailleMotChemin); //la premiere lettre a ajouter
					AbrN aTemp1 = new AbrN(l);
					this.setFils(aTemp1);
					for (int i = 1; i < nbLettreA_inserer ; i++) { //on boucle poour ajouter les autres
						l = motAjouter.charAt(i);
						AbrN aTemp2 = new AbrN(l);
						aTemp1.setFils(aTemp2);
						aTemp2 = aTemp1;
					}
				}
				else {
					System.out.println("Le mot est deja dans l'arbre");
				}
				
			}
		}
	}
	
	public boolean chercher(char l) {
		if (vide) return false;
		if (lettre == l) return true;
		boolean temp = frere.chercher(l);
		if (temp) return true;
		return fils.chercher(l);
	}
	
	private boolean contientTemp(String mot, int cpt) {
		if (cpt == mot.length()) return true;
		if (mot.charAt(cpt) == lettre) { //si on trouve on passe au fils
			//chaine += mot.substring(cpt, cpt +1);
			cpt++;
			return fils.contientTemp(mot, cpt);
		}
		if (frere != null) { //si le frere n'est pas nul on effectue la recherche a partir de lui
			return frere.contientTemp(mot, cpt);
		}
		else { //si le frere est null
			return false;
		}
		
	}
	
	public boolean contient(String mot) {
		return contientTemp(mot, 0);
	}
	
	private void majDictionnaireTemp(ArrayList<String> liste, String mot) {
		if (frere != null) {
			frere.majDictionnaireTemp(liste, mot);		
		}
		if (fils != null) {
			fils.majDictionnaireTemp(liste, mot + lettre);
			if (fils.vide) liste.add(mot+lettre);
			//System.out.print(mot + lettre + " ");
			
		}
		
	}
	
	
	
	public void majDictionnaire(ArrayList<String> dico) {
		majDictionnaireTemp(dico, "");
	}
	
	public static void afficherDico(ArrayList<String> dico) {
		System.out.print("Liste des mots du dictionnaire: ");
		for (String s : dico) {
			System.out.print(s + " ");
		}
		System.out.print("\n");
	}

}
