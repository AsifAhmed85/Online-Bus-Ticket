package ProjectRoot.Server.FileOperation;

import ProjectRoot.Server.BusInformation.Journey;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

public class GetBusInfo {
    private BufferedReader br;
    private BufferedWriter bw;
    private List<Journey> busList = new ArrayList<>();
    private String s[];

    public GetBusInfo()
    {
        try {
            br = new BufferedReader(new FileReader("src/ProjectRoot/TextContent/BusInfo.txt"));
            String st;
            Journey bus;
            while (true)
            {
                st = br.readLine();
                if(st == null) break;
                s = st.split("-");
                bus = new Journey(s[0], s[1], s[2], s[3], s[4], s[5]);
                busList.add(bus);
            }
            check();
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void check() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        Calendar cal = Calendar.getInstance();
        String toDay = dateFormat.format(cal.getTime());
        String[] data1 = toDay.split("/");
        for(int i=0; i<busList.size(); i++)
        {
            String startDateString = busList.get(i).getDate();
            String[] data2 = (startDateString.split("/"));
            while (true) {
                if (parseInt(data1[2]) > parseInt(data2[2])) {
                    busList.remove(i);
                    break;
                }
                else if (parseInt(data1[2]) < parseInt(data2[2])) {
                    break;
                }
                if (parseInt(data1[0]) > parseInt(data2[0])) {
                    busList.remove(i);
                    break;
                }
                else if (parseInt(data1[0]) < parseInt(data2[0])) {
                    break;
                }
                if (parseInt(data1[1]) > parseInt(data2[1])) {
                    busList.remove(i);
                    break;
                }
                else if (parseInt(data1[1]) < parseInt(data2[1])) {
                    break;
                }
            }
        }
    }

    public String add(String s, String s1, String s2, String s3, String s4, String s5) {
        if(!isExist(s, s1, s2, s3, s4)) {
            busList.add(new Journey(s, s1, s2, s3, s4, s5));
            return "added";
        }
        return "already exist";
    }

    public void save()
    {

        try {
            bw = new BufferedWriter(new FileWriter("src/ProjectRoot/TextContent/BusInfo.txt"));
            for(int i=0; i<busList.size(); i++)
            {
                String s = busList.get(i).getBusname() +
                        "-" + busList.get(i).getStart() +
                        "-" + busList.get(i).getDestination() +
                        "-" + busList.get(i).getDate() +
                        "-" + busList.get(i).getTime() +
                        "-" + busList.get(i).getBookedSit().getBooked();
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUnique(List<String> name) {
        String st = "";
        for(int i=0; i<name.size(); i++)
        {
            int j=0;
            while ( j<i)
            {
                if(name.get(i).equalsIgnoreCase(name.get(j))) {j--; break;}
                j++;
            }
            if(j==i) st += name.get(i) + ",";
        }
        return st;
    }

    public String startingPlace() {
        List<String> places = new ArrayList<>();
        for(int i=0; i<busList.size(); i++)
        {
            places.add(busList.get(i).getStart());
        }
        return getUnique(places);
    }

    public String endingPlace(String s) {
        List<String> places = new ArrayList<>();
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getStart()).equalsIgnoreCase(s))
                places.add(busList.get(i).getDestination());
        }
        return getUnique(places);
    }

    public String getdate(String s,String s1) {
        List<String> dates = new ArrayList<>();
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getStart()).equalsIgnoreCase(s) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s1))
                dates.add(busList.get(i).getDate());
        }
        return getUnique(dates);
    }

    public String gettime(String s,String s1,String s2) {
        List<String> times = new ArrayList<>();
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getStart()).equalsIgnoreCase(s) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s1)&&
                    (busList.get(i).getDate()).equalsIgnoreCase(s2))
                times.add(busList.get(i).getTime());
        }
        return getUnique(times);
    }

    public String getbus(String s,String s1,String s2,String s3) {
        List<String> buses = new ArrayList<>();
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getStart()).equalsIgnoreCase(s) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s1)&&
                    (busList.get(i).getDate()).equalsIgnoreCase(s2)&&
                    (busList.get(i).getTime()).equalsIgnoreCase(s3))
                buses.add(busList.get(i).getBusname());
        }
        return getUnique(buses);
    }

    public boolean isExist(String s, String s1, String s2, String s3, String s4) {
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getBusname()).equalsIgnoreCase(s)&&
                    (busList.get(i).getStart()).equalsIgnoreCase(s1) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s2)&&
                    (busList.get(i).getDate()).equalsIgnoreCase(s3)&&
                    (busList.get(i).getTime()).equalsIgnoreCase(s4))
                return true;
        }
        return false;
    }

    public void delete(String s, String s1, String s2, String s3, String s4) {
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getStart()).equalsIgnoreCase(s) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s1)&&
                    (busList.get(i).getDate()).equalsIgnoreCase(s2)&&
                    (busList.get(i).getTime()).equalsIgnoreCase(s3) &&
                    (busList.get(i).getBusname()).equalsIgnoreCase(s4))
                busList.remove(i);
        }
    }

    public String search(String s, String s1, String s2, String s3, String s4) {
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getStart()).equalsIgnoreCase(s) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s1)&&
                    (busList.get(i).getDate()).equalsIgnoreCase(s2)&&
                    (busList.get(i).getTime()).equalsIgnoreCase(s3)&&
                    (busList.get(i).getBusname()).equalsIgnoreCase(s4))
                return busList.get(i).getStart() + "-" +
                        busList.get(i).getDestination()+ "-" +
                        busList.get(i).getDate()+ "-" +
                        busList.get(i).getTime()+ "-" +
                        busList.get(i).getBusname()+ "-" +
                        busList.get(i).getBookedSit().getBookedSeat();
        }
        return "no journey";
    }

    public void book(String s, String s1, String s2, String s3, String s4, String s5, String s6) {
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getBusname()).equalsIgnoreCase(s1)&&
                    (busList.get(i).getStart()).equalsIgnoreCase(s2) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s3)&&
                    (busList.get(i).getDate()).equalsIgnoreCase(s4)&&
                    (busList.get(i).getTime()).equalsIgnoreCase(s5))
                busList.get(i).getBookedSit().add(s,s6);
        }
    }

    public String getBookedTickets(String s) {
        String st = "";
        for (int i = 0; i < busList.size(); i++) {
            String seat = busList.get(i).getBookedSit().sendTickets(s);
            if (!seat.equals("")) {
                st += busList.get(i).getBusname() + "-" +
                        busList.get(i).getStart() + "-" +
                        busList.get(i).getDestination() + "-" +
                        busList.get(i).getDate() + "-" +
                        busList.get(i).getTime() + "-" +
                        seat + "-";
            }
        }
        return st;
    }

    public void deleteJourney(String s, String s1, String s2, String s3, String s4, String s5) {
        for(int i=0; i<busList.size(); i++)
        {
            if((busList.get(i).getBusname()).equalsIgnoreCase(s)&&
                    (busList.get(i).getStart()).equalsIgnoreCase(s1) &&
                    (busList.get(i).getDestination()).equalsIgnoreCase(s2)&&
                    (busList.get(i).getDate()).equalsIgnoreCase(s3)&&
                    (busList.get(i).getTime()).equalsIgnoreCase(s4))
                busList.get(i).getBookedSit().delete(s5);
        }
    }

    public void deleteUser(String s) {
        for(int i=0; i<busList.size(); i++)
        {
            busList.get(i).getBookedSit().deleteUser(s);
        }
    }
}