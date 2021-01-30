import java.util.Date;

public class Physician extends User {
    private String [] specialization;
    private Rating rating;

    Physician(String _emailAddress, String _firstName, String _lastName, String _city,
              String _street, String _houseNumber, String _postalCode, String _phoneNumber, String _title, String _password, String[] _specialization) {
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
        setSpecialization(_specialization);
        setUserType("Physician");
    }

    public String[] getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String[] _specialization) {
        this.specialization = _specialization;
    }
}





