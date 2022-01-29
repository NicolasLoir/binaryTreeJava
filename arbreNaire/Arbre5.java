package arbreNaire;

import java.util.ListIterator;
import java.util.Vector;
import java.util.*;

public class Arbre5 {
	private int value;
	private String value_noeud;

	private Vector<Arbre5> fils;

	private Vector<Arbre5> pere;

	public Arbre5(int n)// n est le nbre des fils de chaque noeuds
	{
		this.value = n;
		fils = new Vector<Arbre5>();
		pere = new Vector<Arbre5>();
	}

	public String getValue()// le nom du noeud
	{
		return this.value_noeud;
	}

	public void ajouteFils(Arbre5 Ar) {
		if (!(this.fils.contains(Ar)))// on vérifie tt d'abord que le nouveau noeud à ajouter n'est pas déja existant
		{
			this.fils.add(Ar);
		}
	}

	public void ajoutePere(Arbre5 Ar) {
		if (!(this.pere.contains(Ar))) {
			this.pere.add(Ar);
		}
	}

	public void setValeur(String ch)// pour le nom du noeud
	{
		this.value_noeud = ch;
	}

	public Arbre5 donneFils(int n)// retourne le noeud à l'indice n du vecteur des fils d'un noeud parent qq
	{
		try {
			return this.fils.get(n);// retourner le fils à l'indice n
		} catch (ArrayIndexOutOfBoundsException e) {
			e.toString();
			return null;
		}
	}

	public int getFilsSize()// retourne le nombre de fils d'un noeud
	{
		return this.fils.size();
	}

	public int getPereSize()// retourne le nombre de pere d'un noeud
	{
		return this.pere.size();
	}

	public ListIterator<Arbre5> elements() {
		return fils.listIterator();// une listeIterator sur chaque fils
	}

	public ListIterator<Arbre5> elements1() {
		return pere.listIterator();// une listeIterator sur chaque pere
	}

	public static void parcours(Arbre5 arbre) {
		ListIterator<Arbre5> list = arbre.elements();
		ListIterator<Arbre5> list1 = arbre.elements1();
		if (list.hasNext()) {
			cherche_suivants(arbre);
			parcours(list.next());
			// System.out.println("next"+arbre.getValue());
		}
		// else System.out.print("\t"+" next"+arbre.donneFils(0).getValue()+"\t");
		if (list1.hasPrevious()) {
			cherche_précédents(arbre);

			parcours(list1.previous());
			// System.out.println("previous "+arbre.getValue());

		}
	}

	public static void cherche_suivants(Arbre5 arbre) {
		ListIterator<Arbre5> list = arbre.elements();
		if (list.hasNext()) {
			System.out.println("suivant " + arbre.getValue());
			cherche_suivants(list.next());
		} else// cas de noeud n'ayant aucun fils
			System.out.println("suivant" + arbre.getValue());
	}

	public static void cherche_précédents(Arbre5 arbre) {
		System.out.println("précédent du " + arbre.getValue());

		ListIterator<Arbre5> list1 = arbre.elements1();// liste pour les pères
		if/* while */ (list1.hasNext()) {// System.out.println("précédent "+arbre.getValue());

			cherche_précédents(list1.next());

		} else
			System.out.println("pas de precedents");
	}

	public static void parcours_profondeur(Arbre5 arbre) {

		System.out.println(arbre.getValue());
		ListIterator<Arbre5> list = arbre.elements();
		while (list.hasNext()) {
			parcours_profondeur(list.next());
		}
	}

	public static void affiche_fils(Arbre5 Arb) {
		System.out.println("fils de " + Arb.getValue());

		if (Arb.getFilsSize() != 0)
			for (int ss = 0; ss < Arb.getFilsSize(); ss++) {
				System.out.print(Arb.donneFils(ss).getValue() + "\t");
			}
		else
			System.out.println("aucun fils");

		System.out.print("\n");
	}

	public static void main(String[] args) {

		Arbre5 SW1 = new Arbre5(2);// 2 est le nbre de files et arbre c'est le noeud racine/père

		Arbre5 SW2 = new Arbre5(2);
		Arbre5 HW2 = new Arbre5(2);

		Arbre5 SW3 = new Arbre5(1);
		Arbre5 HW3 = new Arbre5(1);
		Arbre5 SW4 = new Arbre5(1);
		Arbre5 SW5 = new Arbre5(0);
		SW1.setValeur("SW1");
		SW2.setValeur("SW2");
		SW3.setValeur("SW3");
		SW4.setValeur("SW4");
		SW5.setValeur("SW5");
		HW2.setValeur("HW2");
		HW3.setValeur("HW3");

		// AJOUT DES NOEUDS
		SW1.ajouteFils(SW2);
		SW1.ajouteFils(HW2);

		SW2.ajouteFils(SW3);
		SW2.ajouteFils(HW3);

		HW2.ajouteFils(SW3);
		HW2.ajouteFils(HW3);

		SW3.ajouteFils(SW4);
		HW3.ajouteFils(SW4);

		SW4.ajouteFils(SW5);
		/******* ajout pere *******/
		SW2.ajoutePere(SW1);
		HW2.ajoutePere(SW1);
		SW3.ajoutePere(SW2);
		HW3.ajoutePere(SW2);
		SW3.ajoutePere(HW2);
		HW3.ajoutePere(HW2);
		SW4.ajoutePere(SW3);
		SW4.ajoutePere(HW3);
		SW5.ajoutePere(SW4);
		SW5.ajoutePere(SW4);
		/******* fin ajout pere *******/

		affiche_fils(SW1);
		affiche_fils(SW2);
		affiche_fils(HW2);
		affiche_fils(SW3);
		affiche_fils(HW3);
		affiche_fils(SW4);
		affiche_fils(SW5);

		System.out.println("début parcours profondeur");
		parcours_profondeur(SW1);
		System.out.println("fin parcours profndeur \n");

		parcours(SW1);
		// cherche_suivants(SW1);
		// cherche_précédents(SW1);
		// System.out.println("noeud suivant");
		// cherche_suivants(SW2);
		// cherche_précédents(SW2);
		
		recursivite(" ",SW1);

	}
	
	public static void recursivite(String encours,Arbre5 arbre) {
		ListIterator<Arbre5> list = arbre.elements();
	 
		if (list != null && list.hasNext() ) {// Si on a des fils
			while ( list.hasNext() ) 
			{
				recursivite(encours + " " + arbre.getValue(),list.next());// On peut descendre dans l'arbre alors on descend!
	 
			}
	 
		} else // Sinon on est une feuille de l'arbre
			System.out.println(encours + " " + "SW5");// Séparation entre chaque branche
	 
	}
}