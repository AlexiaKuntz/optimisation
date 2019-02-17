import java.io.*;
import java.util.*;


public abstract class Cell extends Individu {

    protected int num;
    public static int cpt_cell = 0;
    public abstract void infected();
    public abstract void effet(String choix,Virus vrs);

    public Cell() {
      super();
      cpt_cell++;
      num=cpt_cell;
    }

    public void simple_deplacement(String choix){
    	super.deplacement(choix);
    }
    
    public void fusion(Individu item, String move_choice) {
        Individu source=(Individu)this;
        if(this.immunite>item.immunite) {
        	Plateau.grille[source.get_x()][source.get_y()]= new Empty();
        }
        else {
        	super.deplacement(move_choice);
        }
        System.out.println(this.id+" et "+item.id+" ont fusionné.");
    }

    public int get_cpt() {
        return cpt_cell;
    }

    public void deplacement(String move_choice) {
    	try{
	        Individu next_case=case_suivante(move_choice);
	        if(next_case.immunite>0) {
	            fusion(next_case,move_choice);
	        }
	        else {
	            simple_deplacement(move_choice);
	        }
	    }
    	catch(NullPointerException e){
        	System.out.println("Erreur de saisie. Reessayez en utilisant les touches ZQSD ou WASD.");
        	String deuxieme_choix=utile.saisie_chaine();
        	deplacement(deuxieme_choix);
    	}
    	catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Ce deplacement sort du plateau. Essayez une nouvelle direction");
    		deplacement(Plateau.choix_deplacement());
    	}
    }


}
