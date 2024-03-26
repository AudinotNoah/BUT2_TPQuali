import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class MainLaby {
    
    public static void main(String[] args){
        Labyrinthe lab = null;
        try {
            // lab = Labyrinthe.chargerLabyrinthe(args[0]);
            lab = Labyrinthe.chargerLabyrinthe("laby/test.txt"); // test
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier absent");
        }
        catch (IOException e2){
            System.out.println("Erreur : Ficher mal formé");
        }
        catch(FichierIncorrectException e3){
            System.out.println("Erreur : " + e3.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        String action = "";

        while (lab != null && !lab.etreFini()){

            System.out.println(lab.toString());
            System.out.print("Action (haut, bas, gauche, droite, exit) : ");
            action = sc.nextLine();
            if (action.equals("exit")){
                sc.close();
                break;
            }
            try {
                lab.deplacerPerso(action);
                // TEST
                // System.out.println(lab.getPersonnage().getX());
                // System.out.println(lab.getPersonnage().getY());
               
            } catch (ActionInconnueException e) {
                System.out.println("Action inconnu : "+e.getMessage());
            }
        }
        sc.close();

        if (lab != null && !action.equals("exit")){
            System.out.println("Victoire");
        }

    }
}
