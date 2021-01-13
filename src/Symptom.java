public abstract class Symptom {

    private enumSevereness severeness;
    private String description;

    public enumSevereness getSevereness() {
        return severeness;
    }

    public void setSevereness(enumSevereness severeness) {
        this.severeness = severeness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
