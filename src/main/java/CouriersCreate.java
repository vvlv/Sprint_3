public class CouriersCreate {


    private String login;
    private String password;
    private String firstName;

    public void setLogin (String login) {
        this.login = login;
    }
    public void setPassword (String password) {
        this.password = password;
    }
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
    public String getLogin () {
        return login;
    }
    public String getPassword () {
        return password;
    }
    public String getFirstName () {
        return  firstName;
    }
    public CouriersCreate () {

    }
    public CouriersCreate (String login,String password, String firstName) {
        setFirstName(firstName);
        setLogin(login);
        setPassword(password);
    }

}
