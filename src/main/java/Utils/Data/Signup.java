package Utils.Data;

import io.qameta.allure.Step;

public class Signup {
    private String name;
    private String email;
    private  String wrongEmail;
    private  String emailWithMistakes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWrongEmail() {
        return wrongEmail;
    }

    public void setWrongEmail(String wrongEmail) {
        this.wrongEmail = wrongEmail;
    }

    public String getEmailWithMistakes() {
        return emailWithMistakes;
    }

    public void setEmailWithMistakes(String emailWithMistakes) {
        this.emailWithMistakes = emailWithMistakes;
    }
}
