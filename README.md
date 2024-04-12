# Rendu du TP - Labyrinthe Ricochet


- Audinot Noah
- Temirov Abdoul-Raouf
- Groupe B


## Difficultés rencontrées
    Il n'y a pas eu de grosses difficultés rencontrées mais certains points ont été plus compliqués.
    - Lecture et compréhension du sujet :
        Certains aspects du sujet étaient un peu complexes à saisir au début, ce qui nous a demandé plus de temps et d'efforts pour tout comprendre correctement. Nous avons dû relire certaines parties du sujet plusieurs fois et poser des questions pour clarifier les points qui n'étaient pas clairs du premier coup.
    - Répartition des tâches :
        Nous avons rencontré des difficultés pour nous répartir les tâches de manière équilibrée. Au début, nous avons eu du mal à décider qui ferait quoi et comment organiser le travail.
    - Difficultés dans la mise en place des tests :
        La création de tests a été compliquée, car ils avaient beaucoup de scénarios possibles, et cela a pris pas mal de temps à être codé.


## Choix de programmation
    - Nous avons tous les deux utilisé IntelliJ et notamment la fonctionnalité "Code With Me" de l'application pour travailler simultanément sur le même code.
    - En dehors des cours, nous avons maintenu un dépôt GitHub commun pour partager notre code.


## Lancement de l'application
    1. Assurez-vous d'avoir Java installé.
    2. Téléchargez et extrayez l'archive zip du TP sur votre ordinateur.
    3. Ouvrez un terminal et accédez au répertoire extrait du TP puis dans le dossier src avec :
        cd src.
    4. Compilez les fichiers sources en exécutant la commande suivante :
        javac *.java
    5. Pour le lancement du jeu
        java MainLaby "chemin du labyrinthe" (exemple : java MainLaby ../laby/laby1.txt)


## Résultats des tests
    - Tous les tests fonctionnent avec succès, aucun échec


## Couverture de test
    - Nous avons utilisé JUnit pour mettre en place nos tests unitaires.
    - Les tests couvrent toutes les méthodes principales de la classe Labyrinthe, notamment celles liées au chargement du labyrinthe, au déplacement du personnage, à la vérification de la fin du jeu etc.
    - Nous avons également testé différents scénarios possibles, tels que le chargement d'un labyrinthe à partir d'un fichier valide et invalide, la gestion des déplacements du personnage, et la vérification de la condition de fin du jeu.
    - Enfin, nous avons vérifié que les exceptions étaient correctement lancées dans les situations attendues, comme le chargement d'un fichier inexistant ou la tentative de déplacement dans une direction invalide.