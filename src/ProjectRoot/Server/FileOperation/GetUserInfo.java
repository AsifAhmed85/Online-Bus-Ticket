package ProjectRoot.Server.FileOperation;

import ProjectRoot.Server.UserInformation.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GetUserInfo {
    private BufferedReader br;
    private BufferedWriter bw;
    private List<User> userList = new ArrayList<>();
    private String s[];

    public GetUserInfo() {
        try {
            br = new BufferedReader(new FileReader("src/ProjectRoot/TextContent/UserInfo.txt"));
            String st;
            User user;
            while (true) {
                st = br.readLine();
                if (st == null) break;
                s = st.split(" ");
                user = new User(s[0], s[1], s[2], s[3], s[4], s[5]);
                userList.add(user);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User isExist(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username) && userList.get(i).getPassworsd().equals(password))
                return userList.get(i);
        }
        return null;
    }

    public boolean add(String s, String s1, String s2, String s3, String s4, String s5) {
        if (!isExist(s)) {
            userList.add(new User(s, s1, s2, s3, s4, s5));
            return true;
        }
        return false;
    }

    public boolean isExist(String s) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(s))
                return true;
        }
        return false;
    }

    public void save() {

        try {
            bw = new BufferedWriter(new FileWriter("src/ProjectRoot/TextContent/UserInfo.txt"));
            for (int i = 0; i < userList.size(); i++) {
                String s = userList.get(i).getUsername() +
                        " " + userList.get(i).getPassworsd() +
                        " " + userList.get(i).getBirthDate() +
                        " " + userList.get(i).getGender() +
                        " " + userList.get(i).getEmailId() +
                        " " + userList.get(i).getMobileNo();
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(String str) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(str)) {
                userList.remove(userList.get(i));
                return true;
            }
        }
        return false;
    }
}
