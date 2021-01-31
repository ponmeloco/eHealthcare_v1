public class Admin extends User {


    Admin(String _email, String _first, String _last){
        setFirstName(_first);
        setEmailAddress(_email);
        setLastName(_last);
        setUserType("Admin");
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

    public void createPatientAccount(String _emailAddress, String _firstName, String _lastName, String _city,
                                     String _street, String _houseNumber, String _postalCode, String _phoneNumber,
                                     String _title, String _password){}

    public void createPhysicianAccount(String _emailAddress, String _firstName, String _lastName, String _city,
                                       String _street, String _houseNumber, String _postalCode, String _phoneNumber,
                                       String _title, String _password, String[] _specialization){}

    public void createAdminAccount(String _emailAddress, String _firstName, String _lastName, String _city,
                                   String _street, String _houseNumber, String _postalCode, String _phoneNumber,
                                   String _title, String _password){}

    public void deletePhysicianAccount(Physician _physician){}

    public void deletePatientAccount(Patient _patient){}

}
