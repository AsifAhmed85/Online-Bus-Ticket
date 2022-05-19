package ProjectRoot.Server.BusInformation;

import java.util.ArrayList;
import java.util.List;

public class BookedTickets {
    private List<String> bookedSeat = new ArrayList<>();
    private List<String> user = new ArrayList<>();

    BookedTickets(String s)
    {
        if(!s.equalsIgnoreCase("no"))
        {
            String st[] = s.split(",");
            for (int i =0; i<st.length; i++)
            {
                String[] a = st[i].split("_");
                bookedSeat.add(a[0]);
                user.add(a[1]);
            }
        }
        else {
            bookedSeat.add(s);
        }
    }

    public String getBookedSeat()
    {
        String s = "";
        for(int i=0; i<bookedSeat.size(); i++)
        {
            s+=bookedSeat.get(i)+",";
        }
        return s;
    }

    public String getBooked() {
        String s= "";
        if (user.size()>0)
        {
            for (int i = 0; i < bookedSeat.size(); i++) {
                s += bookedSeat.get(i) + "_" + user.get(i) + ",";
            }
            return s;
        }
        return "no";
    }

    public void add(String s, String s6) {
        String st[] = s6.split(",");
        for (int i =0; i<st.length; i++)
        {
            bookedSeat.add(st[i]);
            user.add(s);
        }
    }

    public String sendTickets(String s) {
        String st = "";
        for (int i=0; i<bookedSeat.size(); i++)
        {
            if(!bookedSeat.get(i).equals("no"))
            {
                if ((user.get(i)).equals(s)) {
                    st += bookedSeat.get(i) + ",";
                }
            }
        }
        return st;
    }

    public void delete(String s6) {
        for (int i =0; i<bookedSeat.size(); i++)
        {
            if((bookedSeat.get(i)).equals(s6))
            {
                bookedSeat.remove(i);
                user.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "BookedTickets{" +
                ", bookedSeat=" + bookedSeat +
                ", user=" + user +
                '}';
    }

    public void deleteUser(String s) {
        for (int i =0; i<user.size(); i++)
        {
            if((user.get(i)).equals(s))
            {
                bookedSeat.remove(i);
                user.remove(i);
            }
        }
    }
}
