public class SelectionCriteria {
    int start = 0;
    int end = 0;
    String module = "";
    boolean neutral = true;
    boolean notYet = true;
    boolean learned = true;
    boolean frontside = true;
    boolean random = false;

    public SelectionCriteria() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isNeutral() {
        return neutral;
    }

    public void setNeutral(boolean neutral) {
        this.neutral = neutral;
    }

    public boolean isNotYet() {
        return notYet;
    }

    public void setNotYet(boolean notYet) {
        this.notYet = notYet;
    }

    public boolean isLearned() {
        return learned;
    }

    public void setLearned(boolean learned) {
        this.learned = learned;
    }

    public boolean isFrontside() {
        return frontside;
    }

    public void setFrontside(boolean frontside) {
        this.frontside = frontside;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }
}


