package Utils.Data;

public class loginCredentials {
    private  String email;
    private  String password;
    private  String wrongEmail;
    private  String emailWithMistakes;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String registeredEmail) {
        this.email = registeredEmail;
    }

    public String getEmailWithMistakes() {
        return emailWithMistakes;
    }

    public void setEmailWithMistakes(String emailWithMistakes) {
        this.emailWithMistakes = emailWithMistakes;
    }

    public String getWrongEmail() {
        return wrongEmail;
    }

    public void setWrongEmail(String wrongEmail) {
        this.wrongEmail = wrongEmail;
    }
}
