package fr.unice.polytech.si3.qgl.iaad;
import fr.unice.polytech.si3.qgl.iaad.Exception.InvalidMapException;
import fr.unice.polytech.si3.qgl.iaad.actions.*;
import fr.unice.polytech.si3.qgl.iaad.init.Context;
import fr.unice.polytech.si3.qgl.iaad.init.Contract;
import fr.unice.polytech.si3.qgl.iaad.islandMap.IslandMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Théo on 20/12/2016.
 */

public class NaiveLanding {
    public Context context;
    public Contract contract;
    private static HashMap<String,Integer> resource = new HashMap<>();//map qui recense les ressources et la quantité associée qu'il faut ramener
    public IslandMap map;
    public static int totalCost; //Le budget dépensé exclusivement sur Terre
    private static List<String[]> previousActions; //Une liste "memoire" qui resence toutes les actions faites dans la passée
    /*previousAction est une liste de tableau de 5 cases, la 0 : l'action prise juste avant, 1 : La direction passée en paramètre, si elle existe,
    typiquement pour Scout ou MoveTo, la 2 : le nb de ressources trouvées précédemment, la 3, les ressrouces trouvées, et la 4 : la ressrouce exploitée précédemment (uniquement si on a return un Exploit(ressource))     */

    /**
     * Construteur d'une stratégie naive d'exploration
     * @param context
     * @param contract
     * @param map
     */
    public NaiveLanding(Context context, Contract contract, IslandMap map)
    {
        previousActions = new ArrayList<>();
        totalCost=0;
        this.context=context;
        this.contract=contract;
        this.map=map;
        this.map.zoom(); //Adaptation de l'ancienne map à la configuration terrestre
    }

    /**
     * Créer une hashmap qui recense les ressources et le nombre qu'il en faut = TO DO LIST
     * <Ressources,Quantité>
     */
    public void initRessources()
    {
        for (int i=0; i < context.numberOfContrats(); i++)
        {
            resource.put(context.getContract(i).getResource(),context.getContract(i).getAmount());
        }
    }

    /**
     * En fonction du résultat de l'action précédente, return une action en conséquence
     * @param result
     * @return Action
     */
    public Action nextAction(Ground result) throws InvalidMapException {
        totalCost += result.getCost();
        int nbResource = result.nbResources();
        List<String> tmpResource = new ArrayList<>(); //Ceci permet de recenser les ressources trouvées (si il yen a) par exemple grâce a Scout
        for (int i = 0; i < nbResource; i++) {
            tmpResource.add(result.getResource(i));
        }
        if (context.getBudget() - totalCost < 50) //Si le budget est trop faible, on stoppe l'exploration
        {
            String[] tmp = new String[5];
            tmp[0] = "STOP";
            tmp[1] = "";
            tmp[2] = "";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            return new Stop();
        }
        if (previousActions.size() == 0) { //arbitrairement, si c'est la première action du protocol, on retourne un scout dans une direction où il n'y a pas d'océan
            scoutAround();
        }
        if (previousActions.get(previousActions.size() - 1)[0].equals("EXPLOIT")) { //Si l'action précédente est un exploit
            int exploited = result.getAmountExploit(); //on récupère le nb de ressources exploitées
            int newNb = resource.get(previousActions.get(previousActions.size() - 1)[4]) - exploited;
            if (newNb <= 0) //Si la différence entre ce qu'il faut et ce qu'on a récolté est négative
                resource.remove(previousActions.get(previousActions.size() - 1)[4]); //on supprime cette ressource de notre "To Do List"
            else
                resource.put(previousActions.get(previousActions.size() - 1)[4], newNb);//Sinon, on met à jour le nb de ressrouces requises
        }
        if (previousActions.size() > 1)//Si on a au moins fait deux action avant
            switch (previousActions.get(previousActions.size() - 1)[0]) { //On regarde quelle action on a fait précédemment
                case "MOVETO":
                    if (previousActions.get(previousActions.size() - 2)[0].equals("SCOUT")) { //Si on a fait un SCOUT
                        String[] res = previousActions.get(previousActions.size() - 1)[3].split(" ");//On récupère un tableau qui contient toutes es ressources trouvées par le scout
                        if (previousActions.get(previousActions.size() - 2)[1].equals(previousActions.get(previousActions.size() - 1)[1]))//Si le MOVETO et le SCOUT on était fait dans la meme direction (ça veut dire qu'on se situe bien sur la case sur laquelle il y a les ressources)
                            for (String s : resource.keySet()) //Pour toutes les ressrouces dans la "To Do List"
                                for (int i = 0; i < res.length; i++)
                                    if (s.equals(res[i]) && resource.get(s) > 0) { //Si il y a une ressource trouvée qui correspond à ce qui est demandé par le client
                                        String[] tmp = new String[4];
                                        tmp[0] = "EXPLOIT"; //On l'exploite
                                        tmp[1] = previousActions.get(previousActions.size() - 2)[1];
                                        tmp[2] = previousActions.get(previousActions.size() - 2)[2];
                                        tmp[3] = previousActions.get(previousActions.size() - 2)[3];
                                        tmp[4] = s;
                                        previousActions.add(tmp);
                                        return new Exploit(Resource.valueOf(s));//On l'exploite
                                    }
                    }
                    if (previousActions.get(previousActions.size() - 2)[0].equals("GLIMPSE")) {
                        break; //Pas encore traité (d'où le naiveLanding)
                    }
                    if (previousActions.get(previousActions.size() - 2)[0].equals("EXPLORE")) {
                        break; //Pas encore traité (d'où le naiveLanding)
                    }
                    if (previousActions.get(previousActions.size() - 2)[0].equals("EXPLOIT")) {
                        break; //regarder le résultat précédent pour savoir si on peutencore exploit
                    }
                    if (previousActions.get(previousActions.size() - 2)[0].equals("MOVETO")) {
                        break;
                    }
                    if ((previousActions.get(previousActions.size() - 2)[0].equals("TRANSFORM"))) {
                        break;
                    }
                case "SCOUT":
                    if (nbResource > 0) { //Si on a trouvé au moins une ressource dans le scout précédent
                        for (String s : resource.keySet()) //on parcourt les ressources qui nous sont demandées
                            for (String str : tmpResource)
                                if (s.equals(str)) {//Si il y en a une qui correspond
                                    String[] tmp = new String[5];
                                    tmp[0] = "MOVETO"; // On se déplace sur cette case
                                    tmp[1] = previousActions.get(previousActions.size() - 2)[1];//Dans la même direction
                                    tmp[2] = "" + nbResource;
                                    for (int i = 0; i < nbResource; i++)
                                        tmp[3] += tmpResource.get(i) + " ";//on récupère toutes les ressources trouvées par le scout, avec un " " pour pouvoir split plus tard et créer me tableau de ressource vu plus haut
                                    tmp[4] = "";
                                    previousActions.add(tmp);
                                    map.moveLocation(Direction.valueOf(tmp[1]));//On met à jour la map
                                    return new Move_to(Direction.valueOf(tmp[1]));//On bouge dans la direction souhaitée
                                }
                    }
                    if (previousActions.size() >= 4 && previousActions.get(previousActions.size() - 1)[0].equals((previousActions.get(previousActions.size() - 2)[0])) && previousActions.get(previousActions.size() - 2)[0].equals(previousActions.get(previousActions.size() - 3)[0]) && previousActions.get(previousActions.size() - 3)[0].equals(previousActions.get(previousActions.size() - 4)[0])) {//Dans le cas ou on a fait des SCOUT dans toutes les cases adjacentes, et qui n'ont rien donné
                        moveAround();
                    } else {//Sinon (le nombre de ressource trouvé par le précédent SCOUT est nul, mais on a pas exploité toutes les cases autour de nous)
                        scoutAround();
                    }
                    break;
                case "GLIMPSE":
                    break;//Pas encore traité (d'où le naiveLanding)
                case "EXPLORE":
                    break;//Pas encore traité (d'où le naiveLanding)
                case "TRANSFORM":
                    break;//Pas encore traité (d'où le naiveLanding)
                case "EXPLOIT":
                    String[] res = previousActions.get(previousActions.size() - 1)[3].split(" ");
                    if (res.length >0) {
                        for (String s : resource.keySet()){
                            for (int i=0; i<res.length; i++){
                                if (res[i].equals(s)){
                                    String[] tmp = new String[4];
                                    tmp[0] = "EXPLOIT";
                                    tmp[1] = previousActions.get(previousActions.size() - 2)[1];
                                    tmp[2] = previousActions.get(previousActions.size() - 2)[2];
                                    tmp[3] = previousActions.get(previousActions.size() - 2)[3];
                                    tmp[4] = s;
                                    previousActions.add(tmp);
                                    return new Exploit(Resource.valueOf(s));
                                }
                            }
                        }
                        scoutAround(); //Si le nombre de ressource dispo est non nul, mais qu'aucune nous interesse : on scout sur une case adjacente
                    }
                    else{
                        scoutAround(); //Si le nombre de ressource dispo est nul : idem
                    }
                    return null;
            }
        return null;
    }


    /**
     * Permet de scout scout autour de nous, si la case concernée n'est pas de l'océan
     * @return Scout dans une direction appropriée
     */
    public Action scoutAround()
    {
        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getRight())) {
            String[] tmp = new String[5];
            tmp[0] = "SCOUT";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getRight();
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            return new Scout(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getRight());
        }
        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getBack())) {
            String[] tmp = new String[5];
            tmp[0] = "SCOUT";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getBack();
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            return new Scout(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getBack());
        }
        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getLeft())) {
            String[] tmp = new String[5];
            tmp[0] = "SCOUT";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getLeft();
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            return new Scout(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getLeft());
        }

        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]))) {
            String[] tmp = new String[5];
            tmp[0] = "SCOUT";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]);
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            return new Scout(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]));
        }
        return null;
    }

    /**
     * Permet de se déplacer sur une case adjacente, si celle-ci n'est pas de l'océan
     * @return Un Move_to sur une case appropriée
     */
    public Action moveAround() throws InvalidMapException {
        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getRight())) {//On se déplace dans une case dans laquelle il n'y a pas d'océan
            String[] tmp = new String[5];
            tmp[0] = "MOVETO";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getRight();
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            map.moveLocation(Direction.valueOf(tmp[1]));//On met à jour la map
            return new Move_to(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getRight());
        }
        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getBack())) {
            String[] tmp = new String[5];
            tmp[0] = "MOVETO";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getBack(); //A DET
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            map.moveLocation(Direction.valueOf(tmp[1]));//On met à jour la map
            return new Move_to(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getBack());
        }
        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getLeft())) {
            String[] tmp = new String[5];
            tmp[0] = "MOVETO";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getLeft(); //A DET
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            map.moveLocation(Direction.valueOf(tmp[1]));//On met à jour la map
            return new Move_to(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]).getLeft());
        }
        if (!map.isOcean(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]))) {
            String[] tmp = new String[5];
            tmp[0] = "MOVETO";
            tmp[1] = "" + Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]);
            tmp[2] = "0";
            tmp[3] = "";
            tmp[4] = "";
            previousActions.add(tmp);
            map.moveLocation(Direction.valueOf(tmp[1]));//On met à jour la map
            return new Move_to(Direction.valueOf(previousActions.get(previousActions.size() - 1)[1]));
        }
        return null;
    }
}
