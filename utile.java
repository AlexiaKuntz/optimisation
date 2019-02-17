import java.io.*;
import java.util.*;

public class utile{


public static String saisie_chaine() {
    try {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String chaine = buff.readLine();
        return chaine;
    } catch (IOException e) {
        System.out.println("impossible de travailler " + e);
        return null;
    }
}

public static int saisie_entier() {
    try {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String chaine = buff.readLine();
        int num = Integer.parseInt(chaine);
        return num;
    } catch (IOException e) {
        return 0;
    }
}

public static boolean includes(String[] s_array, String chaine){
	List<String> list = Arrays.asList(s_array);
    if(list.contains(chaine)){
        return true;
    }
    return false;
}

}
