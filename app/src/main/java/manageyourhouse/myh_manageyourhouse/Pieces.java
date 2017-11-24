package manageyourhouse.myh_manageyourhouse;

public class Pieces {

    private String Name;
    private boolean Etat;
    private boolean Notification;
    private int Minutes;

    public Pieces(String n, boolean e, boolean no, int m) {
        Name = n;
        Etat = e;
        Notification = no;
        Minutes = m;
    }

    public String getName(){
        return Name;
    }
    public boolean getEtat(){
        return Etat;
    }
    public boolean getNotifiation(){
        return Notification;
    }
    public int getMinutes(){
        return Minutes;
    }
    public void setEtat(boolean e){
        Etat = e;
    }
    public void setNotification(boolean n){
        Notification = n;
    }
    public void setMinutes(int m){
        Minutes = m;
    }

}
