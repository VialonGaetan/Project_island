package fr.unice.polytech.si3.qgl.iaad;

/**
 * @author Alexandre Clement
 *         Created the 12/11/2016.
 */
public enum Action {
    FLY("fly"), // fait avancer le drône d'une case dans la direction de son cap
    ECHO("echo"), //Utilise le radar pour savoir dans combien de cases on sort de la map ou on découvre la terre (île)
    HEADING("heading"), //fait avancer le drône d'une case tout en l'orientant vers la position en argument
    SCAN("scan"),//prend une photo de la case sur laquelle est actuellement le drône, il en donne aussi le nom (généré aléatoirement).
    STOP("stop"); //arrête la partie


    private String name;

    public String getName() {
            return name;
        } //retourne le nom de l'action

    Action(String name) {
            this.name = name;
        }
}
