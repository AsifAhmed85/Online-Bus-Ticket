package ProjectRoot.Client.Networking;

import ProjectRoot.Client.Main;
import util.NetworkUtil;


public class Read{
    private static Main main;
    private static NetworkUtil nc;

    public Read(NetworkUtil nc, Main main) {
        this.nc = nc;
        this.main = main;
    }

    public static void startreading(String s) {
        try {
            if (s != null) {
                String st[] = s.split(":");
                switch (st[0])
                {
                    case "userLogin":
                        userlogin(st[1]);
                        break;
                    case "adminLogin":
                        adminlogin(st[1]);
                        break;
                    case "register":
                        register(st[1]);
                    case "book":
                        book(st[1]);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void book(String s) {
        try {
            main.showBookingPage(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adminlogin(String s) {
        if(s.equalsIgnoreCase("connect"))
        {
            try {
                main.showAdminHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            // failed login
            main.showAlert("Incorrect Credentials","The username and password you provided is not correct.");
        }
    }

    private static void userlogin(String s) {
        String[] st = s.split("_");
        if(st[0].equalsIgnoreCase("connect"))
            try {
                System.out.println(st[1]);
                main.usename = st[1];
                main.showUserHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        else
        {
            main.showAlert("Incorrect Credentials","The username and password you provided is not correct.");
        }
    }

    private static void register(String s) {
        if(s.equalsIgnoreCase("successful"))
            try {
                main.showSuccessfulRegistration();
            } catch (Exception e) {
                e.printStackTrace();
            }
        else
        {
            main.showAlert("Invalid username","Another ID is using this same username");
        }
    }
}



