package ProjectRoot.Server.UserInformation;

public class User {
    private String username;
    private String passworsd;
    private String emailId;
    private String birthDate;
    private String gender;
    private String mobileNo;

    public User(String username, String passworsd, String emailId, String birthDate, String gender, String mobileNo) {
        this.username = username;
        this.passworsd = passworsd;
        this.emailId = emailId;
        this.birthDate = birthDate;
        this.gender = gender;
        this.mobileNo = mobileNo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassworsd() {
        return passworsd;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", passworsd='" + passworsd + '\'' +
                ", emailId='" + emailId + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}
