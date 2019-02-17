import java.io.*;
import java.util.*;

abstract public class Individu {

	protected boolean vivant;
    protected boolean already_moved=false;
    protected int x,y;
    protected String id;
    protected int immunite;

    public Individu(){
    	vivant=true;
    }
    
    public void affiche() {
        System.out.println("|"+id+"|");
    }

    public void moved() {
        already_moved=true;
    }

    public void reset_move() {
        already_moved=false;
    }

    public boolean did_moved() {
        return already_moved;
    }

    public Individu case_suivante(String choix) {
        switch (choix) {
            case "w" :
            case "z" :
                return Plateau.grille[x-1][y];
            case "q" :
            case "a" :
                return Plateau.grille[x][y-1];
            case "s" :
                return Plateau.grille[x+1][y];
            case "d" :
                return Plateau.grille[x][y+1];
        }
        return null;
    }

    public void deplacement(String choix) {
    	try{
	        Individu next_case=case_suivante(choix);
	        	if (!(next_case.getClass().getSimpleName().equals("Virus"))){
		            switch (choix) {
		                case "w" :
		                case "z" :
		                    set_x(x-1);
		                    Plateau.grille[x][y]=this;
		                    Plateau.grille[x+1][y]=new Empty(x,y);break;
		                case "q" :
		                case "a" :
		                    set_y(y-1);
		                    Plateau.grille[x][y]=this;
		                    Plateau.grille[x][y+1]=new Empty(x,y);break;
		                case "s" :
		                    set_x(x+1);
		                    Plateau.grille[x][y]=this;
		                    Plateau.grille[x-1][y]=new Empty(x,y);break;
		
		                case "d" :
		                    set_y(y+1);
		                    Plateau.grille[x][y]=this;
		                    Plateau.grille[x][y-1]=new Empty(x,y);break;
		            }
		            moved();
	            }
	            else{
	            	System.out.println("Un virus vous d'acceder a cette case. Choisissez une nouvelle direction.");
	            	deplacement(Plateau.choix_deplacement());
	            
	            }
    	}
    	catch(NullPointerException e){
        	System.out.println("Erreur de saisie.");
        	deplacement(Plateau.choix_deplacement());
    	}
    	catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Ce deplacement sort du plateau. Essayez une nouvelle direction");
    		deplacement(Plateau.choix_deplacement());
    	}
    }
    	

    public void set_x(int _x) {
        x = _x;
    }

    public int get_x() {
        return x;
    }


    public void set_y(int _y) {
        y = _y;
    }

    public int get_y() {
        return y;
    }

    public void set_xy(int _x,int _y) {
        x=_x;
        y=_y;
    }

    public String get_id() {
        return id;
    }

    public void die(){
    	vivant=false;
    }
}
