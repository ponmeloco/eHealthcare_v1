public class Drug {

    private DosageMgperKg maxDosageFemaleChild;
    private DosageMgperKg maxDosageMaleChild;
    private DosageMgperKg maxDosageFemale;
    private DosageMgperKg maxDosageMale;
    private String chemicalComponents;
    private Symptom[] treatedSymptoms;
    private Float LD50;

    public DosageMgperKg getMaxDosageFemaleChild() {
        return maxDosageFemaleChild;
    }

    public void setMaxDosageFemaleChild(DosageMgperKg maxDosageFemaleChild) {
        this.maxDosageFemaleChild = maxDosageFemaleChild;
    }

    public DosageMgperKg getMaxDosageMaleChild() {
        return maxDosageMaleChild;
    }

    public void setMaxDosageMaleChild(DosageMgperKg maxDosageMaleChild) {
        this.maxDosageMaleChild = maxDosageMaleChild;
    }

    public DosageMgperKg getMaxDosageFemale() {
        return maxDosageFemale;
    }

    public void setMaxDosageFemale(DosageMgperKg maxDosageFemale) {
        this.maxDosageFemale = maxDosageFemale;
    }

    public DosageMgperKg getMaxDosageMale() {
        return maxDosageMale;
    }

    public void setMaxDosageMale(DosageMgperKg maxDosageMale) {
        this.maxDosageMale = maxDosageMale;
    }

    public String getChemicalComponents() {
        return chemicalComponents;
    }

    public void setChemicalComponents(String chemicalComponents) {
        this.chemicalComponents = chemicalComponents;
    }

    public Symptom[] getTreatedSymptoms() {
        return treatedSymptoms;
    }

    public void addTreatedSymptoms(Symptom[] treatedSymptoms) {
    }

    public Float getLD50() {
        return LD50;
    }

    public void setLD50(Float LD50) {
        this.LD50 = LD50;
    }
}
