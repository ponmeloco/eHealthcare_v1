public class Patient extends User {

    Patient() {}
    Patient(String _userName, String _emailAddress, String _firstName, String _lastName, String _city,
            String _street, String _houseNumber, String _postalCode, String _phoneNumber, String _title) {
        setUserName(_userName);
        setEmailAddress(_emailAddress);
        setFirstName(_firstName);
        setLastName(_lastName);
        setCity(_city);
        setStreet(_street);
        setHouseNumber(_houseNumber);
        setPostalCode(_postalCode);
        setPhoneNUmber(_phoneNumber);
        setTitle(_title);
    }

    Patient(int _id, String _userName, String _emailAddress, String _firstName, String _lastName, String _city,
            String _street, String _houseNumber, String _postalCode, String _phoneNumber, String _title) {
        setId(_id);
        setUserName(_userName);
        setEmailAddress(_emailAddress);
        setFirstName(_firstName);
        setLastName(_lastName);
        setCity(_city);
        setStreet(_street);
        setHouseNumber(_houseNumber);
        setPostalCode(_postalCode);
        setPhoneNUmber(_phoneNumber);
        setTitle(_title);
    }


}