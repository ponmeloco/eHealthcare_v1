public abstract class user {

    private int id;
    private String userName;
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String phoneNUmber;
    private String title;// = {"Prof.","Dr.","Dipl-Ing","B.Sc.","B.A.","B.Eng.","B.F.A.","B.Mus.","M.Sc.","M.A.","M.Eng.","Magister"};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public void setPhoneNUmber(String phoneNumber) {
        this.phoneNUmber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void printAttr(){

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%5s %10s %30s %10s %10s %10s", "ID","USERNAME", "EMAIL", "TITLE", "NAME", "LASTNAME");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.format("%5s %10s %30s %10s %10s %10s",this.getId(), this.getUserName(), this.getEmailAddress(),
                            this.getTitle(), this.getFirstName(), this.getLastName());
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%10s %20s %15s %15s %16s","CITY", "STREET", "HOUSENUMBER", "POSTAL", "PHONENUMBER");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.format("%10s %20s %15s %15s %16s",this.getCity(), this.getStreet(),
                            this.getHouseNumber(), this.getPostalCode(), this.getPhoneNUmber());
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }


}
