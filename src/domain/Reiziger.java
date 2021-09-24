package domain;

import domain.Adres;

import java.sql.Date;
import java.util.ArrayList;

public class Reiziger {

    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;
    private ArrayList<OVChipkaart> OVChipkaarten = new ArrayList<>();


    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam,Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getVoorletters() {
        return this.voorletters;
    }

    public void setVoorletters(String voorletters){
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return this.tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return this.achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return this.geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getNaam(){
        return  this.voorletters + this.tussenvoegsel + this.achternaam;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setOVChipkaarten(ArrayList<OVChipkaart> OVChipkaarten) {
        this.OVChipkaarten = OVChipkaarten;
    }

    public ArrayList<OVChipkaart> getOVChipkaarten() {
        return this.OVChipkaarten;
    }

    public void addOVChipkaart(OVChipkaart OVChipkaart) {
        if(!OVChipkaarten.contains(OVChipkaart)) {
            OVChipkaarten.add(OVChipkaart);
        }
    }
    public void removeOVChipkaart(OVChipkaart OVChipkaart) {

        OVChipkaarten.remove(OVChipkaart);
    }


    @Override
    public String toString() {
        String result =  "Reiziger{" +
                "id=" + id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                '}';
        String OVChipkaartString = "";
        if (!this.OVChipkaarten.isEmpty()) {
            OVChipkaartString += "OvChipkaarten[";
            for (OVChipkaart OVKaart : OVChipkaarten) {
                OVChipkaartString += " OvChipkaart" + OVKaart;
            }
            OVChipkaartString += " ]";
        } else {
            OVChipkaartString = "null";
        }



        return result ;


    }
}
