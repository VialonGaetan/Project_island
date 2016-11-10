package fr.unice.polytech.qgl.iaad;

import org.json.JSONObject;

/**
 * Created by Gaetan VIALON on 10/11/2016.
 */
public class AnswersQuery {


    private JSONObject Answers = new JSONObject();

    public AnswersQuery(JSONObject Answers){
        this.Answers = Answers;
    }

    public int GetCost(){
        return Answers.getInt(ArgAnswer.COST.getName());
    }
    public String GetStatus(){
        return Answers.getString(ArgAnswer.STATUS.getName());
    }
}

enum ArgAnswer {
    COST("cost"),
    EXTRAS("extras"),
    STATUS("status");

    private String name;

    public String getName() {
        return name;
    }

    ArgAnswer(String name) {
        this.name = name;
    }
}