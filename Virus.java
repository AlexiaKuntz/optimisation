import java.io.*;
import java.util.*;


public class Virus extends Individu {

    protected int vie;
    public static int cpt_virus = 0;

    public Virus() {
        super();
        cpt_virus++;
        vie=1;
        immunite=0;
        id=String.format("v%02d", cpt_virus);
    }
    
    public void affiche() {
    	if(vivant){
    		System.out.println(id+" a encore "+vie+" vies.");
    	}
    	else{
    		System.out.println(id+" est mort.");
    	}
    }
    
    public void simple_deplacement(String choix){
    	super.deplacement(choix);
    }
    
    public void set_vie(int nb) {
        vie=nb;
    }

    public void perte_vie() {
        vie=vie-1;
        System.out.println("Il reste "+vie+" vies à "+id);
    }

    public void gain_vie() {
        vie=vie+1;
        System.out.println("Il reste "+vie+" vies à "+id);
    }

    public int get_cpt() {
        return cpt_virus;
    }

    public void die() {
    	super.die();
        cpt_virus-=1;
        Plateau.grille[x][y]=new Empty(x,y);
        affiche();
    }

    public void proliferation() {
        for (int i = 0; i < Plateau.directions.length; ++i) {
            String s = Plateau.directions[i];
            Individu rep=case_suivante(s);
            if (rep.getClass().getSimpleName().equals("Empty")) {
                if (!(rep.get_x()==0 || rep.get_x() >= Plateau.grille.length-1 || rep.get_y()==0 || rep.get_y()>= Plateau.grille.length-1)) {
                    Virus vrs=new Virus();
                    vrs.set_x(rep.get_x());
                    vrs.set_y(rep.get_y());
                    vrs.set_vie(4);
                    System.out.println("Le nouveau virus a "+vrs.vie);
                    Plateau.grille[rep.get_x()][rep.get_y()]=vrs;
                    break;
                }
            }
        }
    }

    public void set_id(String _id) {
        id=_id;
    }

    public void effet() {
        if (vie==10) {
            System.out.println("le virus "+id+" prolifere.");
            proliferation();
            set_vie(4);
            System.out.println("L'ancien virus se retrouve avec "+vie);
        }
        else if (vie==0) {
            die();
        }
    }

    public void deplacement(String move_choice) {
    	try{
	        Individu next_case=case_suivante(move_choice);
	        if(next_case.immunite>0) {
	            Cell cellule=(Cell)next_case;
	            cellule.effet(move_choice,this);
	        }
	        else {
	            simple_deplacement(move_choice);
	        }
	        effet();
	    }
    	catch(NullPointerException e){
        	System.out.println("Erreur de saisie. Reessayez. (virus)");
        	String deuxieme_choix=utile.saisie_chaine();
        	deplacement(deuxieme_choix);
    	}
    	catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("Ce deplacement sort du plateau. Essayez une nouvelle direction");
    		deplacement(Plateau.choix_deplacement());
    	}

    }
    
}
