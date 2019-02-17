import java.io.*;
import java.util.*;

public class Y_Cell extends Cell {

    private boolean infection;
    private int heal_turn;

    public Y_Cell() {
        super();
        infection=false;
        id=String.format("y%02d",num);
        immunite=2;
        heal_turn=0;
    }

    public void affiche() {
        if(infection) {
            System.out.println(id+" est infectee. Elle sera guerie dans "+heal_turn+" tours.");
        }
        else {
            System.out.println(id+" n'est pas infectee et est donc sensible aux virus.");
        }
    }

    public void infected() {
        infection=true;
        heal_turn=3;
        affiche();
    }

    public boolean get_infection() {
        return infection;
    }

    public void healed() {
        heal_turn=heal_turn-1;
    }
    
    public void y_turn() {
    	if(infection) {
    		healed();
    		if(heal_turn==0){
    			infection=false;
    			System.out.println(id+" n'est plus infecté.");
    			immunisation();
    		}
    		else{
    			moved();
    			System.out.println(id+" est encore infecté pour "+heal_turn+" tours.");
    		}
    	}
    }

    public void immunisation() {
        Random r = new Random();
        int n = r.nextInt(2);
        if(n==0){
        	Plateau.grille[this.x][this.y]=new X_Cell(this.x,this.y);
        	System.out.println(this.id+" est maintenant immunisee au virus. Elle est devenue "+Plateau.grille[this.x][this.y].id);
        }
    }

    public void effet(String choix, Virus vrs) {
        if (infection) {
            System.out.println(id+" deja infectee");
            vrs.perte_vie();
        }
        else {
            vrs.gain_vie();
            infected();
            moved();
        }
    }

}
