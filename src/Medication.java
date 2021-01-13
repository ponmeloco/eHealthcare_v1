public class Medication {

    private float totalAmountofDruginmg;
    private float weightPatientInKg;
    private DosageMgperKg dosage;
    private Drug drug;

    public float getTotalAmountofDruginmg() {
        return totalAmountofDruginmg;
    }

    public void setTotalAmountofDruginmg(float totalAmountofDruginmg) {
        this.totalAmountofDruginmg = totalAmountofDruginmg;
    }

    public float getWeightPatientInKg() {
        return weightPatientInKg;
    }

    public void setWeightPatientInKg(float weightPatientInKg) {
        this.weightPatientInKg = weightPatientInKg;
    }

    public DosageMgperKg getDosageMgperKg() {
        return dosage;
    }

    public void setDosageMgperKg(DosageMgperKg dosage) {
        this.dosage = dosage;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public float getTotalAmountOfDrug(){
        return 1.0f;
    }

}
