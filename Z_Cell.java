import java.io.*;
import java.util.*;

public class Z_Cell extends Cell {

    public Z_Cell() {
        super();
        id=String.format("z%02d",num);
        immunite=1;
    }

    public void affiche() {
    	if(vivant){
    		System.out.println(id+" est sensible aux virus.");
    	}
    	else{
    		System.out.println(id+" est morte.");
    	}
    }

    public void infected() {
    	die();
        affiche();
    }


    public void effet(String choix, Virus vrs) {
        vrs.simple_deplacement(choix);
        vrs.gain_vie();
        infected();
        }
    }
