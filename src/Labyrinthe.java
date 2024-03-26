import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * La classe Labyrinthe représente un labyrinthe avec un personnage, une sortie et des murs.
 * Elle permet de charger un labyrinthe à partir d'un fichier, de déplacer le personnage dans le labyrinthe
 * et de vérifier si le personnage a atteint la sortie.
 */

public class Labyrinthe {
    //  Liste des constantes utilisées dans la représentation du labyrinthe et les actions du joueur.
    //  Les constantes pour les éléments du labyrinthe représentent les symboles utilisés pour les représenter,
    //  tandis que les constantes pour les actions du joueur représentent les différentes directions de déplacement.
    
    //liste des constantes pour les éléments du labyrinthe
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char SORTIE = 'S';
    public static final char VIDE = '.';
    
    //liste des constantes pour les actions du joueur
    public static final String HAUT = "haut";
    public static final String BAS = "bas";
    public static final String DROITE = "droite";
    public static final String GAUCHE = "gauche";

    /**
     * Tableau bidimensionnel représentant la présence des murs dans le labyrinthe.
     * Chaque cellule du tableau correspond à une case du labyrinthe. 
     * La valeur true indique la présence d'un mur, tandis que false indique l'absence de mur.
     */
    private boolean[][] murs;

    /** Objet représentant le personnage dans le labyrinthe.*/
    private Personnage personnage;

    /**Objet représentant la sortie du labyrinthe.*/
    private Sortie sortie;

    /**
     * Constructeur par défaut de la classe Labyrinthe.
     * Initialise le personnage et la sortie du labyrinthe.
     */
    public Labyrinthe(){
        personnage = new Personnage();
        sortie = new Sortie();
    }

    /**
     * Getter des murs du labyrinthe.
     * @return Le tableau bidimensionnel de booléens représentant les murs du labyrinthe.
     */
    public boolean[][] getMurs() {
        return murs;
    }

    /**
     * Getter de la sortie du labyrinthe.
     * @return La sortie du labyrinthe.
     */
    public Sortie getSortie() {
        return sortie;
    }

    /**
     * Getter du personnage du labyrinthe.
     * @return Le personnage du labyrinthe.
     */
    public Personnage getPersonnage() {
        return personnage;
    }

    /**
     * Obtient le caractère à la position spécifiée dans le labyrinthe.
     * @param x Coordonnée x de la position.
     * @param y Coordonnée y de la position.
     * @return Le caractère représentant le contenu de la position spécifiée dans le labyrinthe.
     */
    public char getChar(int x, int y) {
        // si on est hors du labyrinthe ou si les coordonnées sont égales à celle d'un mur 
        if (x < 0 || y < 0 || murs.length <= x || murs[x].length <= y || murs[x][y]) {
            return MUR;
        }
        // si les coordonnées sont égales à celles du personnage 
        else if (personnage.getX() == x && personnage.getY() == y) {
            return PJ;
        }
        // si les coordonnées sont égales à celles de la sortie 
        else if (sortie.getX() == x && sortie.getY() == y) {
            return SORTIE;
        }
        return VIDE;
    }

    /**
     * Obtient les coordonnées suivantes en fonction de l'action spécifiée.
     * @param x Coordonnée x actuelle.
     * @param y Coordonnée y actuelle.
     * @param action Action à effectuer.
     * @return Un tableau d'entiers contenant les coordonnées suivantes.
     * @throws ActionInconnueException Si l'action spécifiée n'est pas reconnue.
     */
    public static int[] getSuivant(int x, int y, String action) throws ActionInconnueException {
        switch (action) {
            case HAUT:
                return new int[] { x - 1, y };
            case BAS:
                return new int[] { x + 1, y };
            case DROITE:
                return new int[] { x, y + 1 };
            case GAUCHE:
                return new int[] { x, y - 1 };
            // si l'action ne fait pas partie des 4 cas au dessus alors on renvoie ActionInconnueException avec en message le nom de l'action
            default:
                throw new ActionInconnueException(action);
        }
    }

    /**
     * Déplace le personnage dans le labyrinthe en fonction de l'action spécifiée.
     * @param action Action à effectuer.
     * @throws ActionInconnueException Si l'action spécifiée n'est pas reconnue.
     */
    public void deplacerPerso(String action) throws ActionInconnueException {
        try {
            // on utilise getsuivant() pour connaitre la prochaine position du personnage selon l'action
            int[] posSuivante = getSuivant(personnage.getX(), personnage.getY(), action);
            // tant que les coordonnées de la prochaine position ne sont pas celles d'un mur 
            while (getChar(posSuivante[0], posSuivante[1]) != MUR) {
                // on change les coordonnées du personnage pour celles de la nouvelle position 
                personnage.setAll(posSuivante[0], posSuivante[1]);
                // on passe à la prochaine position
                posSuivante = getSuivant(personnage.getX(), personnage.getY(), action);
            }

        }
        //si getsuivant() n'a pas reconnu la position alors on refait remonter l'erreur ActionInconnueException avec en message le nom de l'action
        catch (ActionInconnueException e) {
            throw new ActionInconnueException(e.getMessage());
        }
    }

    /**
     * Représente graphiquement le labyrinthe.
     * @return Une chaîne de caractères représentant le labyrinthe.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // on parcours le labyrinthe et ajoute le char correspondant au StringBuilder grâce à getChar()
        for (int i = 0; i < murs.length; i++) {
            for (int j = 0; j < murs[i].length; j++) {
                sb.append(getChar(i, j));
            }
            // on ajoute \n pour sauter une ligne sauf si on est arrivé à la dernière ligne 
            if (i < murs.length -1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Vérifie si le personnage a atteint la sortie.
     * @return true si le personnage a atteint la sortie, sinon false.
     */
    public boolean etreFini() {
        //si le personnage et la sortie on est les mêmes coordonnées
        return personnage.getX() == sortie.getX() && personnage.getY() == sortie.getY();
    }

    /**
     * Charge un labyrinthe à partir d'un fichier.
     * @param nom Nom du fichier contenant la description du labyrinthe.
     * @return Le labyrinthe chargé à partir du fichier.
     * @throws FileNotFoundException Si le fichier spécifié n'est pas trouvé.
     * @throws IOException Si une erreur d'entrée/sortie survient lors de la lecture du fichier.
     * @throws FichierIncorrectException Si le format du fichier est incorrect.
     */
    public static Labyrinthe chargerLabyrinthe(String nom) throws FileNotFoundException, IOException, FichierIncorrectException {
        // on cree le Labyrinthe et le constructer va initialiser la sortie et le personnage
        Labyrinthe l = new Labyrinthe();
        BufferedReader fichier = new BufferedReader(new FileReader(nom));

        int n_lignes = 0;
        int n_colonnes = 0;

        // on essaye de creer notre tableau bidimensionnel de murs avec en taille les deux valeurs 
        // récupéres au dessus qu'on essaye de convertir en nombre
        // si parseint renvoie une exception ça veut dire que les valeurs de lignes ou colonnes 
        // ne sont pas des nombres valides, on renvoie donc l'exception FichierIncorrectException
        try {
            n_lignes  = Integer.parseInt(fichier.readLine());
            n_colonnes = Integer.parseInt(fichier.readLine());
            l.murs = new boolean[n_lignes][n_colonnes];
        } catch (NumberFormatException e) {
            fichier.close();
            throw new FichierIncorrectException("pb numligne ou colonne");
        }
        catch (NegativeArraySizeException e2){
            fichier.close();
            throw new FichierIncorrectException("pb numligne ou colonne");
        }
        

        int i = 0;
        int j = 0;
        boolean persoSet = false;
        boolean sortieSet = false;
    
        String ligne = fichier.readLine();
        while (ligne != null) {
            j = 0;
            // on boucle sur chaque char de la ligne
            for (char c : ligne.toCharArray()) {
                if (i >= n_lignes){
                    fichier.close();
                    throw new FichierIncorrectException("nbLignes ne correspond pas");
                }
                else if (j >= n_colonnes){
                    fichier.close();
                    throw new FichierIncorrectException("nbColonnes ne correspond pas");
                }

    
                switch (c) {
                    case 'X':
                        l.murs[i][j] = true;
                        break;
                    case 'S':
                        if (!sortieSet) {
                            l.sortie.setAll(i, j);
                            sortieSet = true;
                        } else {
                            fichier.close();
                            throw new FichierIncorrectException("plusieurs sorties");
                        }
                        break;
                    case 'P':
                        if (!persoSet) {
                            l.personnage.setAll(i, j);
                            persoSet = true;
                        } else {
                            fichier.close();
                            throw new FichierIncorrectException("plusieurs personnages");
                        }
                        break;
                    case '.':
                        // dans le cas d'une case vide on ne fait rien car c'est le cas de base 
                        // de getchar() par contre on break quand meme pour eviter d'activer le cas default
                        break;
                    default:
                        fichier.close();
                        throw new FichierIncorrectException("caractere inconnu <" + c + ">");
                }
                j++;
            }
            if (j != n_colonnes){
                fichier.close();
                throw new FichierIncorrectException("nbColonnes ne correspond pas");
            }
            i++;
            ligne = fichier.readLine();
        }

        fichier.close();

        if (i != n_lignes){
            throw new FichierIncorrectException("nbLignes ne correspond pas");
        }


        if (!persoSet) {
            throw new FichierIncorrectException("personnage inconnu");
        } else if (!sortieSet) {
            throw new FichierIncorrectException("sortie inconnue");
        }
        return l;
    
    }
}        
