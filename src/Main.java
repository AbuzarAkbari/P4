import DOA.*;
import domain.Adres;
import domain.OVChipkaart;
import domain.Reiziger;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Main {

    static Connection connection;

    public static void main(String[] args) throws SQLException {

        ReizigerDAOPsql rdao = new ReizigerDAOPsql(getConnection());

        AdresDAOPsql adao = new AdresDAOPsql(getConnection(),rdao);

        OVChipkaartDAOPsql odao = new OVChipkaartDAOPsql(getConnection(),rdao);

        testReizigerDAO(rdao);

        testAdresDAO(adao);

        testOVChipkaartDAO(odao,rdao);

        closeConnection();

    }

    private static Connection getConnection(){
        try {
            String url = "jdbc:postgresql://localhost/ovchip";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","VoetBal1223");
            connection = DriverManager.getConnection(url, props);

        } catch(Exception e ){
            System.out.println("connection failed");
        }
        return connection;
    }

    private static void closeConnection(){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * P2. domain.Reiziger DAO: persistentie van een klasse
     *
     * Deze methode test de CRUD-functionaliteit van de domain.Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
//        System.out.println("\n---------- Test DOA.ReizigerDAO -------------");
//
//        // Haal alle reizigers op uit de database
//        List<domain.Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] DOA.ReizigerDAO.findAll() geeft de volgende reizigers:");
//        for (domain.Reiziger r : reizigers) {
//            System.out.println(r);
//        }
//        System.out.println();
//
//        // Maak een nieuwe reiziger aan en persisteer deze in de database
//        String gbdatum = "1981-03-14";
//        domain.Reiziger sietske = new domain.Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
//        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na DOA.ReizigerDAO.save() ");
//        rdao.save(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers\n");
//
//        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
//
//        sietske.setVoorletters("A");
//        sietske.setTussenvoegsel(null);
//        sietske.setAchternaam("Akbari");
//        sietske.setGeboortedatum(java.sql.Date.valueOf("1999-08-01"));
//        rdao.update(sietske);

//        rdao.delete(sietske);

    }

    private static void testAdresDAO(AdresDAO adao) throws SQLException {
//        ReizigerDAO rdao = new ReizigerDAOPsql(getConnection());
//
//        System.out.println("\n---------- Test DOA.AdresDAO -------------");
//
//        // Haal alle adressen op uit de database
//        System.out.println("[Test] DOA.AdresDAO.findAll() geeft de volgende adressen:");
//        List<Adres> adressen = adao.findAll();
//        for (Adres a : adressen) {
//            System.out.println(a);
//        }
//        System.out.println();
//
//        // Maak een nieuwe adres aan en persisteer deze in de database
//        String gbdatum = "1999-08-01";
//        Reiziger abuzar = new Reiziger(69, "A", "", "Akbari", java.sql.Date.valueOf(gbdatum));
//        rdao.save(abuzar);
//        Adres abuzarAdres = new Adres(69, "3721jl", "397", "Kometenlaan", "Utrecht", abuzar);
//        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na DOA.AdresDAO.save() ");
//        adao.save(abuzarAdres);
//        adressen = adao.findAll();
//        System.out.println(adressen.size() + " adressen\n");
//
//
//        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
//
//        System.out.println(adao.findById(69) + "\n");
//
//        abuzarAdres.setHuisnummer("69");
//        abuzarAdres.setPostcode("1234AB");
//        adao.update(abuzarAdres);
//        System.out.println(adao.findById(69) + "\n");
//
//
//        adao.delete(abuzarAdres);
//        adressen = adao.findAll();
//        System.out.println(adressen.size() + " adressen\n");

    }
    private static void testOVChipkaartDAO(OVChipkaartDAO odao, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- TestOVChipkaartDAO -------------");

        AdresDAOPsql adao = new AdresDAOPsql(getConnection(),rdao);


        // Haal alle OVChipkaarten op uit de database
        List<OVChipkaart> ovChipkaarten = odao.findAll();
//        System.out.println("[Test] OVChipkaartDAO.findAll() geeft de volgende OVChipkaarten:");
//        for (OVChipkaart ovChipkaart : ovChipkaarten) {
//            System.out.println(ovChipkaart);
//        }
//        System.out.println();

        // Maak een nieuw ovchipkaart aan en persisteer deze in de database
        Reiziger sarata = new Reiziger(101, "S", "", "Gota", java.sql.Date.valueOf("2000-05-04"));
        rdao.save(sarata);
        OVChipkaart ovChipkaart = new OVChipkaart(12321, java.sql.Date.valueOf("2019-1-1"), 1, 69.0, sarata);
//        System.out.print("[Test] Eerst " + ovChipkaarten.size() + " OVKaarten, na OVChipkaartDAO.save() ");
        odao.save(ovChipkaart);

        Adres sarataAdres = new Adres(12, "3721jl", "397", "Kometenlaan", "Utrecht", sarata);
        adao.save(sarataAdres);

        ovChipkaarten = odao.findAll();
        System.out.println(ovChipkaarten.size() + " OVKaarten\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.

        // Update
        System.out.println("[Test] Eerst \n" + odao.findByKaartNummer(69420) + "\nNa OVChipkaart.update():");
        ovChipkaart.setSaldo(69.69);
        odao.update(ovChipkaart);
        System.out.println(odao.findByKaartNummer(69420) + "\n");

        // Delete
        ovChipkaarten = odao.findAll();
        System.out.print("[Test] Eerst " + ovChipkaarten.size() + " kaarten, na OVChipkaart.delete() ");
        odao.delete(ovChipkaart);
        ovChipkaarten = odao.findAll();
        System.out.println(ovChipkaarten.size() + " kaarten\n");
//

    }




}
