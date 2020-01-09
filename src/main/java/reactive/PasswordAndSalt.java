package reactive;

public class PasswordAndSalt {
    private String PASSWORD;
    private String SALT;

    public PasswordAndSalt(String PASSWORD, String SALT) {
        this.PASSWORD = PASSWORD;
        this.SALT = SALT;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getSALT() {
        return SALT;
    }

    public void setSALT(String SALT) {
        this.SALT = SALT;
    }
}
