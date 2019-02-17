import java.io.*;
import java.util.*;

public class X_Cell extends Cell {



    public X_Cell() {
        super();
        id=String.format("x%02d",num);
        immunite=3;
    }
    
    public X_Cell(int _x,int _y) {
        super();
        id=String.format("x%02d",num);
        immunite=3;
        x=_x;
        y=_y;
    }
    

    public void affiche() {
        System.out.println(id+" est immunisée.");
    }

    public void infected() {
        affiche();
    }

    public void effet(String choix, Virus vrs) {
        infected();
        vrs.perte_vie();
    }

}
