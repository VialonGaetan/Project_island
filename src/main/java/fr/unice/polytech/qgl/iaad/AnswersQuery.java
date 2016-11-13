package fr.unice.polytech.qgl.iaad;

import org.json.JSONObject;

/**
 * Created by Gaetan VIALON on 10/11/2016.
 */
public class AnswersQuery {

    public class Extras {

        private JSONObject extras;

        Extras(JSONObject extras) {
            this.extras = extras;
        }

        public int getRange() {
            return extras.getInt(ArgExtras.RANGE.getName());
        }

        public Found getFound() {
            return Found.valueOf(extras.getString(ArgExtras.FOUND.getName()));
        }
    }


    private JSONObject Answers = new JSONObject();

    public AnswersQuery(JSONObject Answers){
        this.Answers = Answers;
    }

    public int GetCost(){
        return Answers.getInt(ArgAnswer.COST.getName());
    }

    public Extras getExtras() {
        return new Extras(Answers.getJSONObject(ArgAnswer.EXTRAS.getName()));
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

enum ArgExtras {
    RANGE("range"),
    FOUND("found");

    private String name;

    public String getName() {
        return name;
    }

    ArgExtras(String name) {
        this.name = name;
    }
}

enum Found {
    OUT_OF_RANGE("OUT_OF_RANGE"),
    GROUND("GROUND");

    private String name;

    public String getName() {
        return name;
    }

    Found(String name) {
        this.name = name;
    }
}