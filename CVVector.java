import java.io.*;
import java.util.*;

public class CVVector extends Vector {

    public void add_idv_personnalise() {
        int reponse=1;
        while(reponse!=0) {
            System.out.println("\n1. Virus\n2. Cellule X\n3. Cellule Y\n4. Cellule Z\n0. Fin\n5. Pour quitter");
            reponse = utile.saisie_entier();
            if(reponse==0){return;} //on le met ici pour éviter que la question suivante soit posée
            System.out.println("Combien voulez-vous en ajouter ?");
            int valeur=utile.saisie_entier();
            for (int i=0;i<valeur;i++) {
                Individu item=null;
                switch (reponse) {
                    case 0 : return;
                    case 1 : item = new Virus();break;
                    case 2 : item = new X_Cell();break;
                    case 3 : item = new Y_Cell();break;
                    case 4 : item = new Z_Cell();break;
                    case 5 : System.exit(1);
                    default: System.out.println("Mauvais numero");break;
                }
                if(item!=null) {
                    addElement(item);
                }
            }
        }
    }
    
    public void add_idv_fixe(int[] mode_de_jeu) {
    	for (int i=0; i<mode_de_jeu.length; i++) {
    	  int cpt=mode_de_jeu[i];
    	  for(int j=0;j<cpt;j++){
    		  Individu item=null;
    		  switch(i){
    	  		case 0 : item = new Virus();break;
    	  		case 1 : item = new X_Cell();break;
    	  		case 2 : item = new Y_Cell();break;
    	  		case 3 : item = new Z_Cell();break;
    		  }
    		  addElement(item);
    	  }
    	}
    }
}
