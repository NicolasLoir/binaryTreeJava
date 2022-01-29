package TPprogDynamique;

public class DistanceEdition {
	
	public static void distance(String mot1, String mot2){
		char M [] = new char [mot1.length()];
		for (int i = 0; i<mot1.length(); i++) {
			M[i] = mot1.charAt(i);
			//System.out.println(m[i]);
		}
		char P [] = new char [mot2.length()];
		for (int i = 0; i<mot2.length(); i++) {
			P[i] = mot2.charAt(i);
			//System.out.println(p[i]);
		}
		
		int ligne = mot1.length();
		int colonne = mot2.length();
		int tab [][] = new int [ligne+1][colonne+1];
		
		
		iniMatZero(tab);
		//System.out.println("Initialisation:");
		//afficheMatrice(tab);
		
		int count;
		for (int li = 1; li<=ligne; li++) {
			for (int col = 1; col<=colonne; col++) {
				if ( M[li-1]==P[col-1] ) count = 0;
				else count = 1;
				tab[li][col] = min3(tab[li-1][col] + 1, tab[li][col-1] +1 , tab[li-1][col-1] + count);
			}
		}
		
		System.out.println("Apres calcul pour " + mot1 + " et " + mot2 + " :");
		afficheMatrice(tab, M, P);
		
	}
	
	
	
	public static void main(String[] args) {
		distance("chaine", "chien");
		
		
		distance("farmacie", "pharmacie");
		distance("farmacie", "pharmacien");
		distance("farmacie", "armurerie");
		distance("farmacie", "carnation");
		
		
		
		
	}
	
	private static void afficheMatrice(int matrice [][]) {
		for (int li= 0; li<matrice.length; li++) {
			for (int col=0; col<matrice[li].length; col++) {
				System.out.print(matrice[li][col] + " ");
			}
			System.out.print("\n");
		}
	}
	
	private static void afficheMatrice(int matrice [][], char [] mot1, char [] mot2) {
		System.out.print("   ");
		for (int col=0; col<mot2.length; col++) System.out.print(" " + mot2[col] );
		System.out.print("\n  ");
		
		
		for (int li= 0; li<matrice.length; li++) {
			for (int col=0; col<matrice[li].length; col++) {
				System.out.print(matrice[li][col] + " ");
			}
			System.out.print("\n");
			if (li < mot1.length) System.out.print(mot1[li] + " ");
			
		}
	}
	
	
	private static void iniMatZero(int matrice [][]) {
		for (int li= 0; li<matrice.length; li++) {
			for (int col=0; col<matrice[li].length; col++) {
				matrice[li][col] = 0;
			}
			
		}
		//on rempli la colonne 1 de de {1...n}
		for (int li = 1; li<matrice.length; li++ ) {
			matrice[li][0] = li;
		}
		//on rempli la ligne 1 de {1...n}
		for (int col = 1; col<matrice[0].length; col++ ) {
			matrice[0][col] = col;
		}
	}
	
	static int min2(int a, int b) {
		if (a < b) return a;
		else return b;
	}
	
	static int min3(int a, int b, int c) {
		if ( (a < b) && (a < c))  return a;
		else return min2(b,c);			
	}

}
