import java.io.*;
import java.util.*;

public class Empty extends Individu {

    public Empty() {
        super();
        id="___";
        immunite=-1;
    }

    public Empty(int _x,int _y) {
        super();
        id="___";
        x=_x;
        y=_y;
    }

    public boolean is_empty() {
        return true;
    }

}
