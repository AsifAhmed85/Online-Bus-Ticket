package ProjectRoot.Server.Networking;

import ProjectRoot.Server.FileOperation.GetBusInfo;
import ProjectRoot.Server.FileOperation.GetUserInfo;
import ProjectRoot.Server.UserInformation.User;
import util.NetworkUtil;


public class ClientThread implements Runnable {
    private GetUserInfo info;
    private Thread thr;
    private NetworkUtil nc;
    private User user;
    private GetBusInfo busInfo;


    public ClientThread(NetworkUtil nc) {
        this.nc = nc;
        info = new GetUserInfo();
        busInfo = new GetBusInfo();
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                String s = (String) nc.read();
                if (s != null) {
                    System.out.println(s);
                    String st[] = s.split(":");

                    switch (st[0])
                    {
                        case "LoginAsUser":
                            userlogin(st[1]);
                            break;
                        case "LoginAsAdmin":
                            adminlogin(st[1]);
                            break;
                        case "Register":
                            register(st[1]);
                            break;
                        case "Add":
                            addJourney(st[1]);
                            break;
                        case "getStart":
                            getStart();
                            break;
                        case "getDestination":
                            getDestination(st[1]);
                            break;
                        case "getDate":
                            getDate(st[1]);
                            break;
                        case "getTime":
                            getTime(st[1]);
                            break;
                        case "getBus":
                            getBus(st[1]);
                            break;
                        case "deleteJourney":
                            deleteJourney(st[1]);
                            break;
                        case "SearchJourney":
                            searchJourney(st[1]);
                            break;
                        case "Bookall":
                            bookAll(st[1]);
                            break;
                        case "getBookedTicket":
                            getBookedTicket(st[1]);
                            break;
                        case "deleteUserJourney":
                            deleteUserJourney(st[1]);
                            break;
                        case "deleteUser":
                            deleteUser(st[1]);
                            break;
                    }
                    if(st[1].equalsIgnoreCase("exit")) {
                        info.save();
                        busInfo.save();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            nc.closeConnection();
        }
    }

    private void deleteUser(String s) {
        if(info.delete(s))
        {
            busInfo.deleteUser(s);
            nc.write("successful");
        }
        else {
            nc.write("unsuccessful");
        }
    }

    private void deleteUserJourney(String s) {
        String st[] = s.split(",");
        busInfo.deleteJourney(st[0],st[1],st[2],st[3],st[4],st[5]);
    }

    private void getBookedTicket(String s) {
        nc.write(busInfo.getBookedTickets(s));
    }

    private void bookAll(String s) {
        String st[] = s.split("-");
        busInfo.book(st[0],st[1],st[2],st[3],st[4],st[5],st[6]);
    }

    private void searchJourney(String s) {
        String st[] = s.split(",");
        nc.write("book:"+busInfo.search(st[0],st[1],st[2],st[3],st[4]));
    }

    private void deleteJourney(String s) {
        String st[] = s.split(",");
        busInfo.delete(st[0],st[1],st[2],st[3],st[4]);
    }

    private void getStart() {
        nc.write(busInfo.startingPlace());
    }

    private void getDestination(String s) {
        nc.write(busInfo.endingPlace(s));
    }

    private void getDate(String s) {
        String st[] = s.split(",");
        nc.write(busInfo.getdate(st[0],st[1]));
    }

    private void getTime(String s) {
        String st[] = s.split(",");
        nc.write(busInfo.gettime(st[0],st[1],st[2]));
    }

    private void getBus(String s) {
        String st[] = s.split(",");
        System.out.println(st[0]+st[1]+st[2]+st[3]);
        nc.write(busInfo.getbus(st[0],st[1],st[2],st[3]));
    }

    private void addJourney(String s) {
        String st[] = s.split(",");
        nc.write(busInfo.add(st[0],st[1],st[2],st[3],st[4],"no"));
    }

    private void adminlogin(String s) {
        String st[] = s.split(",");
        String validUserName = "admin";
        String validPassword = "123";
        if (!st[0].equals(null) && !st[1].equals(null) && st[0].equals(validUserName) && st[1].equals(validPassword))
            nc.write("adminLogin:connect");
        else
            nc.write("adminLogin:wrong input");
    }

    private void userlogin(String s) {
        String st[] = s.split(",");
        user = info.isExist(st[0],st[1]);
        if(user != null)
        {
            System.out.println(user);
            nc.write("userLogin:connect");
        }
        else
        {
            nc.write("userLogin:wrong input");
        }
    }

    private void register(String s)
    {
        String st[] = s.split(",");
        if(info.add(st[0],st[1],st[2],st[3],st[4],st[5]))
            nc.write("register:successful");
        else
            nc.write("register:unsuccessful");
    }
}
