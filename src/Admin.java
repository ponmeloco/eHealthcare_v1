public class Admin extends User {

    private String AdminName;
    Admin(String _email, String _first, String _last){
        setUserType("Admin");
        setFirstName(_first);
        setEmailAddress(_email);
        setLastName(_last);
    }
    Admin(String _emailAddress, String _firstName, String _lastName, String _city,
          String _street, String _houseNumber, String _postalCode, String _phoneNumber, String _title, String _password){
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
        setUserType("Admin");


    }

    public void createPatientAccount(){}

    public void createPhysicianAccount(){}

    public void createAdminAccount(){}

    public void deletePhysicianAccount(){}

    public void deletePatientAccount(){}

    public void setPatientFirstName(){}

    public void setPatientLastName(){}

    public void setPatientAddressInformation(){}

    public void setPatientDateOfBirth(){}

    public void setPatientInsuranceType(){}

    public void setPatientInsuranceName(){}

    public void setPatientEmailAddress(){}

    public void getPatientFirstName(){}

    public void getPatientLastName(){}

    public void getPatientAddressInformation(){}

    public void getPatientDateOfBirth(){}

    public void getPatientInsuranceType(){}

    public void getPatientInsuranceName(){}

    public void getPatientEmailAddress(){}




}
