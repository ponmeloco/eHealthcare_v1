import java.util.Date;

public class Patient extends User {

    private String birthday;
    private String insuranceType;
    private String insuranceName;
    private Symptom[] symptoms;
    private Medication[] medications;
    private int weight;

    Patient() {}
    Patient(String _emailAddress, String _firstName, String _lastName, String _city,
            String _street, String _houseNumber, String _postalCode, String _phoneNumber, String _title, String _password) {
        setEmailAddress(_emailAddress);
        setFirstName(_firstName);
        setLastName(_lastName);
        setCity(_city);
        setStreet(_street);
        setHouseNumber(_houseNumber);
        setPostalCode(_postalCode);
        setPhoneNUmber(_phoneNumber);
        setTitle(_title);
        setPasswordhash(_password);
    }
    Patient(String _emailAddress, String _firstName, String _lastName, String _city,
            String _street, String _houseNumber, String _postalCode, String _phoneNumber, String _title, String _password,
            String _birthday, String _insuranceName, Symptom[] _symptoms, Medication[] _medications, int _weight) {
        setEmailAddress(_emailAddress);
        setFirstName(_firstName);
        setLastName(_lastName);
        setCity(_city);
        setStreet(_street);
        setHouseNumber(_houseNumber);
        setPostalCode(_postalCode);
        setPhoneNUmber(_phoneNumber);
        setTitle(_title);
        setPasswordhash(_password);
        setBirthday(_birthday);
        setInsuranceName(_insuranceName);
        setSymptoms(_symptoms);
        setMedications(_medications);
        setWeight(_weight);
        setInsuranceType("public");
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public Symptom[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Symptom[] _symptoms) {
        this.symptoms = _symptoms;
    }

    public Medication[] getMedications() {
        return medications;
    }

    public void setMedications(Medication[] medications) {
        this.medications = medications;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}