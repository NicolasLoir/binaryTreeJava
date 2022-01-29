package arbreNaire;

import java.util.ArrayList;

public class ArbreNaire {
	 char lettre;
	 ArbreNaire filsAine;
	 ArrayList<ArbreNaire> frere;
	 boolean vide;
	
	//Constructeurs
	public ArbreNaire(){
		vide = true;
	}
	
	public ArbreNaire (char rac){
		vide = false;
		lettre = rac;
		filsAine = new ArbreNaire();
		frere = new ArrayList<ArbreNaire>();
	}
	
	public void setFilsAine(ArbreNaire a) {
		this.filsAine = a;
		a.frere.add(this);
	}
	
	public void setFrere(ArbreNaire a) {
		frere.add(a);
		a.setFilsAine(this);
	}
	
	private void afficheIntermediaire(int var) {
		if (!vide){
			var++;
			for (int i=0; i<var; i++) {
				System.out.print("\t");
			}
			System.out.println(lettre);
			for (ArbreNaire a : frere) {
				a.afficheIntermediaire(var);
			}
		}
	}
	
	public void affiche() {
		if (vide) System.out.print("L'abre est vide");
		afficheIntermediaire(-1);
	}
	
	public static void main(String[] args) {
		ArbreNaire q = new ArbreNaire('q');
		ArbreNaire r = new ArbreNaire('r');
		ArbreNaire s = new ArbreNaire('s');
		ArbreNaire t = new ArbreNaire('t');
		
		s.setFilsAine(r);
		t.setFilsAine(r);		
		//r.setFrere(s);
		//r.setFrere(t);
		r.setFilsAine(q);		
		//q.setFrere(r);
		q.affiche();
		
		ArbreNaire a = new ArbreNaire('a');
		ArbreNaire b = new ArbreNaire('b');
		ArbreNaire c = new ArbreNaire('c');
		ArbreNaire d = new ArbreNaire('d');
		ArbreNaire e = new ArbreNaire('e');
		ArbreNaire f = new ArbreNaire('f');
		ArbreNaire g = new ArbreNaire('g');
		ArbreNaire h = new ArbreNaire('h');
		ArbreNaire i = new ArbreNaire('i');
		ArbreNaire j = new ArbreNaire('j');
		ArbreNaire k = new ArbreNaire('k');
		ArbreNaire l = new ArbreNaire('l');
		ArbreNaire m = new ArbreNaire('m');
		
		m.setFilsAine(g);
		l.setFilsAine(g);
		k.setFilsAine(g);
		
		f.setFilsAine(c);
		g.setFilsAine(c);
		h.setFilsAine(c);
		j.setFilsAine(c);
		
		e.setFilsAine(b);
		
		b.setFilsAine(a);
		c.setFilsAine(a);
		d.setFilsAine(a);
		
		a.affiche();
		
		
	}
	
	/*
	private boolean chercher(char l) {
		if (vide) return false;
		if (lettre == l) return true;
		else {
			for (ArbreNaire a : frere) {
				boolean temp = a.chercher(l);
				
			}
		}
	}
	*/
	
	
}
