public class Patient extends User {

    Patient() {}
    Patient(String _emailAddress, String _firstName, String _lastName, String _city,
            String _street, String _houseNumber, String _postalCode, String _phoneNumber, String _title) {
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