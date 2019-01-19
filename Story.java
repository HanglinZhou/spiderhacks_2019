enum Type {
    DEFAULT, FIXED, RANDOM, DAILY, RANDOMTRIGGERED, DAILYTRIGGERED;
}

public class Story {
    Decision decision;
    Type type;

    public Story(Decision decision, Type type) {
        this.decision = decision;
        this.type = type;
    }


    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
