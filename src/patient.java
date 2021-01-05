public class patient extends user {

    patient(){}

    patient(int _id, String _userName,String _emailAddress, String _firstName, String _lastName, String _city,
            String _street, String _houseNumber, String _postalNumber, String _phoneNumber, String _title){
        setId(_id);
        setUserName(_userName);
        setEmailAddress(_emailAddress);
        setFirstName(_firstName);
        setLastName(_lastName);
        setCity(_city);
        setStreet(_street);
        setHouseNumber(_houseNumber);
        setPostalNumber(_postalNumber);
        setPhoneNUmber(_phoneNumber);
        setTitle(_title);

    }
}
