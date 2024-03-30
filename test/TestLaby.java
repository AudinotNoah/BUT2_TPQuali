import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;


public class TestLaby {
    
    @Test
    public void getCharOK() throws Exception{
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby0.txt");
        assertEquals('X', l.getChar(0, 0));
        assertEquals('S', l.getChar(1, 1));
        assertEquals('.', l.getChar(1, 2));
        assertEquals('P', l.getChar(2, 3));   
    }

    @Test
    public void getCharHorsTab() throws Exception{
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby0.txt");
        assertEquals('X', l.getChar(-1, 0));
        assertEquals('X', l.getChar(0, -1));
        assertEquals('X', l.getChar(100, 0));
        assertEquals('X', l.getChar(0, 100));
    }

    @Test
    public void getSuivantOK() throws Exception{
        assertArrayEquals(new int[]{-6,3}, Labyrinthe.getSuivant(-5, 3, "haut"));
        assertArrayEquals(new int[]{1,2}, Labyrinthe.getSuivant(0, 2, "bas"));
        assertArrayEquals(new int[]{2,4}, Labyrinthe.getSuivant(2, 5, "gauche"));
        assertArrayEquals(new int[]{1,4}, Labyrinthe.getSuivant(1, 3, "droite"));
    }

    @Test
    public void getSuivantActionInconnu() throws Exception{
        ActionInconnueException exception = assertThrows(ActionInconnueException.class, () -> {
            Labyrinthe.getSuivant(-5, 3, "test"); 
        });

        assertEquals("test",exception.getMessage());
    }


    @Test
    public void deplacerPersoOK() throws Exception{
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby1.txt");

        assertEquals(5, l.getPersonnage().getX());
        assertEquals(5, l.getPersonnage().getY());

        l.deplacerPerso("haut");
        assertEquals(1, l.getPersonnage().getX());
        assertEquals(5, l.getPersonnage().getY());

        l.deplacerPerso("bas");
        assertEquals(5, l.getPersonnage().getX());
        assertEquals(5, l.getPersonnage().getY());

        l.deplacerPerso("droite");
        assertEquals(5, l.getPersonnage().getX());
        assertEquals(8, l.getPersonnage().getY());

        l.deplacerPerso("gauche");
        assertEquals(5, l.getPersonnage().getX());
        assertEquals(5, l.getPersonnage().getY());
    }

    @Test
    public void deplacerPersoActionInconnu() throws Exception{
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby1.txt");
        ActionInconnueException exception = assertThrows(ActionInconnueException.class, () -> {
            l.deplacerPerso("test"); 
        });

        assertEquals("test",exception.getMessage());
    }


    @Test
    public void toStringOK() throws Exception{
        String laby1 =  "XXXXXXXXXX\n" +
                        "X.....XX.X\n" +
                        "X.XS.....X\n" +
                        "X......X.X\n" +
                        "X........X\n" +
                        "X..XXP...X\n" +
                        "XXXXXXXXXX";


        String laby1haut =  "XXXXXXXXXX\n" +
                            "X....PXX.X\n" +
                            "X.XS.....X\n" +
                            "X......X.X\n" +
                            "X........X\n" +
                            "X..XX....X\n" +
                            "XXXXXXXXXX";


        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby1.txt");
        assertEquals(laby1, l.toString());
        l.deplacerPerso("haut");
        assertEquals(laby1haut, l.toString());
    }


    @Test
    public void etreFiniOK() throws Exception{
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby0.txt");
        assertEquals(false,l.etreFini());
        l.deplacerPerso("haut");
        l.deplacerPerso("gauche");
        assertEquals(true,l.etreFini());

    }

    @Test
    public void chargerLabyrintheOK() throws Exception{
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/laby0.txt");
        boolean[][] murs = {
            {true, true, true, true, true, true, true},
            {true, false, false, false, false, false, true},
            {true, false, false, false, false, false, true},
            {true, false, false, false, false, false, true},
            {true, true, true, true, true, true, true}
        };
        assertArrayEquals(murs,l.getMurs());

        assertEquals(1, l.getSortie().getX());
        assertEquals(1, l.getSortie().getY());

        assertEquals(2, l.getPersonnage().getX());
        assertEquals(2, l.getPersonnage().getX());
    }   


    @Test
    public void chargerLabyrintheFichierInconnu() throws Exception{
        assertThrows(FileNotFoundException.class, () -> {
            Labyrinthe.chargerLabyrinthe("laby/testtt.txt");
        });
    }

    @Test
    public void chargerLabyrinthePbLigneColonne() throws Exception{
        FichierIncorrectException exception = assertThrows(FichierIncorrectException.class, () -> {
            Labyrinthe.chargerLabyrinthe("laby/laby_colonneInvalide.txt");; 
        });
        assertEquals("pb numligne ou colonne",exception.getMessage());


        FichierIncorrectException exception2 = assertThrows(FichierIncorrectException.class, () -> {
            Labyrinthe.chargerLabyrinthe("laby/laby_ligneInvalide.txt");; 
        });
        assertEquals("pb numligne ou colonne",exception2.getMessage());

    }


    @Test
    public void chargerLabyrintheNombreIncorrectLigneColonne() throws Exception{
        FichierIncorrectException exception = assertThrows(FichierIncorrectException.class, () -> {
            Labyrinthe.chargerLabyrinthe("laby/laby_NombrecolonneInvalideInferieur.txt");; 
        });
        assertEquals("nbColonnes ne correspond pas",exception.getMessage());

        FichierIncorrectException exception2 = assertThrows(FichierIncorrectException.class, () -> {
            Labyrinthe.chargerLabyrinthe("laby/laby_NombrecolonneInvalideSuperieur.txt");; 
        });
        assertEquals("nbColonnes ne correspond pas",exception2.getMessage());
        

        FichierIncorrectException exception3 = assertThrows(FichierIncorrectException.class, () -> {
            Labyrinthe.chargerLabyrinthe("laby/laby_NombreligneInvalideInferieur.txt");; 
        });
        assertEquals("nbLignes ne correspond pas",exception3.getMessage());

        FichierIncorrectException exception4 = assertThrows(FichierIncorrectException.class, () -> {
            Labyrinthe.chargerLabyrinthe("laby/laby_NombreligneInvalideSuperieur.txt");; 
        });
        assertEquals("nbLignes ne correspond pas",exception4.getMessage());


    }





    // à supprimer
    @Test
    public void testest() throws Exception{
        Labyrinthe l = Labyrinthe.chargerLabyrinthe("laby/test.txt");
    }
}
