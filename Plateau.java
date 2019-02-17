import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;


public class Plateau {

    public static String[] directions={"q","w","s","d","a","z"};
    public static int[] mode_facile={10,20,30,40}, mode_normal={10,33,33,34}, mode_difficile={10,40,30,20};
    public static Individu[][] grille=game();
    public static int turn = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu\n Tapez 0 pour quitter l'application\n Taper 1 pour creer des individus\n Taper 2 pour se deplacer");
            int choix = utile.saisie_entier();
            switch (choix) {
                case 0: System.exit(0);
                case 1: menu_difficulte();break;
                case 2: player_turn(grille);break;
            }
        }
    }


    public static Individu[][] game() {
        Individu plateau [][] = new Individu [20][20];
        for (int i = 0; i<20; i++ ) {
            for (int j = 0; j<20; j++ ) {
                Empty item=new Empty(i,j);
                plateau[i][j]=item;
            }
        }
        return plateau;
    }


    public static void affiche_grille(Individu[][] grille) {
        for (int i = 0; i<20; i++ ) {
            for (int j = 0; j<20; j++ ) {
                System.out.print("|"+grille[i][j].get_id()+"|");
            }
            System.out.println();
        }
    }

    public static void aleatoire(String mode, int[] mode_de_jeu) {
        CVVector vct= new CVVector();
        if(mode.equals("personnalise")){
            vct.add_idv_personnalise();
        }
        else {
        	vct.add_idv_fixe(mode_de_jeu);
        }
        Vector<Integer> tab = new Vector<>();
        for ( int i=0;i<400;i++) {
            tab.add(i);
        }
        for (Enumeration e = vct.elements(); e.hasMoreElements();) {
            Random rand = new Random();
            int indice= rand.nextInt(tab.size());
            int new_indice_line=tab.elementAt(indice)%20;
            int new_indice_column=tab.elementAt(indice)/20;
            Individu item = (Individu)e.nextElement();
            item.set_x(new_indice_line);
            item.set_y(new_indice_column);
            grille[new_indice_line][new_indice_column]=item;
            tab.remove(indice);
        }
        affiche_grille(grille);
    }

    public static void menu_difficulte(){
    	System.out.println("Choisissez la difficulte du jeu.");
    	System.out.println("1. Facile\n2. Normal\n3. Difficile\n4. Personnalise\n");
    	int choix=utile.saisie_entier();
    		switch(choix){
    		case 1 : aleatoire("non personnalise",mode_facile);break;
    		case 2 : aleatoire("non personnalise",mode_normal);break;
    		case 3 : aleatoire("non personnalise",mode_difficile);break;
    		case 4 : aleatoire("personnalise",null);break;
    		}
    	
    }
    
    
    public static void deplacement_plateau(int cpt, String type) {

    	while(true){
	        Individu item=find_grille(Plateau.choix_individu());
	        if((item instanceof Cell && type=="Virus") || (item instanceof Virus && type=="Cell")){
		        	System.out.println("Vous avez selectionne une entite appartenant a l'adversaire");
		        	deplacement_plateau(cpt,type);
		        	return;
	        }
	        item.affiche();
	        if(cpt==0){
	        	item.reset_move();
	        }
		    if(!(item.did_moved())) {
		    	String move_choice=choix_deplacement();
		        if (item instanceof Cell) {
		        	Cell cellule=(Cell)item;
		            cellule.deplacement(move_choice);
		            break;
		        }
		        else{
		        	Virus vrs=(Virus)item;
		            vrs.deplacement(move_choice);
		            break;
		        }
		    }
		    else{
		        	System.out.println("Cet individu est infecte ou a deja ete deplace.");
		    }  
	   }

   }
    



    public static void player_turn(Individu[][] grille) {
        while (true) {
            affiche_grille(grille);
            
            gestion_infectes();
            System.out.println("~~~~ C'est au tour des virus ~~~~");
            System.out.println("Combien voulez-vous déplacer de virus ? Vous en avez "+Virus.cpt_virus);
            int choix=utile.saisie_entier();
            int cpt=0;
            turn+=1;
            System.out.println(turn);
            while (cpt<choix) {
                affiche_grille(grille);
                deplacement_plateau(cpt,"Virus");
                cpt+=1;
            }
            System.out.println("~~~~ C'est au tour des cellules ~~~~");
            System.out.println("Vous pouvez déplacer un maximum de 4 cellules.");
            cpt=0;
            while (cpt<4) {
                affiche_grille(grille);
                deplacement_plateau(cpt,"Cell");
                cpt+=1;
            }
        }
    }




    public static String choix_individu() {
        System.out.println("Donnez l'id de l'individu que vous voulez deplacer");
        String choix=utile.saisie_chaine();
        return choix;
    }
    
    public static String choix_deplacement() {
        System.out.println("Dans quelle direction ? q/a : gauche   s : bas    d : droite    z/w : haut");
        String move_choice=utile.saisie_chaine();
        return move_choice;
    }



    public static Individu find_grille(String id) {
        for (int i = 0; i<20; i++ ) {
            for (int j = 0; j<20; j++ ) {
                Individu item = grille[i][j];
                if (item.get_id().equals(id)) {
                    return item;
                }
            }
        }
        System.out.println("Objet introuvable");
        return null;
    }
    
    public static void gestion_infectes(){
        for (int i = 0; i<20; i++ ) {
            for (int j = 0; j<20; j++ ) {
                Individu item = grille[i][j];
                if(item instanceof Y_Cell) {
                	((Y_Cell) item).y_turn();
                }
            }
        }
    }
}
