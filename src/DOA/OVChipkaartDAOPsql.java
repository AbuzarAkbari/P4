package DOA;

import domain.Adres;
import domain.OVChipkaart;
import domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {

    private Connection conn;
    private ReizigerDAO rdao;


    public OVChipkaartDAOPsql(Connection conn,ReizigerDAO rdao){
        this.conn = conn;
        this.rdao = rdao;

    }

    @Override
    public boolean save(OVChipkaart ovChipkaart){
        try {
            PreparedStatement prepStatement = conn.prepareStatement("INSERT INTO ov_chipkaart values(?, ?, ?, ?, ?)");
            prepStatement.setInt(1, ovChipkaart.getKaartNummer());
            prepStatement.setDate(2, ovChipkaart.getGeldigTot());
            prepStatement.setInt(3, ovChipkaart.getKlasse());
            prepStatement.setDouble(4, ovChipkaart.getSaldo());
            prepStatement.setInt(5, ovChipkaart.getReiziger().getId());

            prepStatement.execute();

            prepStatement.close();

            return true;
        } catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            PreparedStatement prepStatement = conn.prepareStatement("UPDATE ov_chipkaart SET geldig_tot=?, klasse=?, saldo=? WHERE kaart_nummer=?");
            prepStatement.setDate(1, ovChipkaart.getGeldigTot());
            prepStatement.setInt(2, ovChipkaart.getKlasse());
            prepStatement.setDouble(3, ovChipkaart.getSaldo());
            prepStatement.setInt(4, ovChipkaart.getKaartNummer());

            prepStatement.execute();

            prepStatement.close();

            return true;
        } catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart)  {
        try {
            PreparedStatement prepStatement = conn.prepareStatement("DELETE FROM ov_chipkaart WHERE kaart_nummer=?");
            prepStatement.setInt(1, ovChipkaart.getKaartNummer());
            prepStatement.execute();
            prepStatement.close();
            return true;
        } catch(SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public OVChipkaart findByKaartNummer(int kaartNummer){

        try{
            PreparedStatement prepStatement = conn.prepareStatement("SELECT * FROM ov_chipkaart WHERE kaart_nummer=?");
            prepStatement.setInt(1,kaartNummer);
            ResultSet result = prepStatement.executeQuery();

            OVChipkaart ovChipkaart = null;


            while (result.next()) {

                int klasse = result.getInt("klasse");
                Date geldigTot = result.getDate("geldig_tot");
                double saldo = result.getDouble("saldo");
                int reizigerId = result.getInt("reiziger_id");
                ovChipkaart = new OVChipkaart(kaartNummer, geldigTot, klasse, saldo, rdao.findById(reizigerId));

            }

            prepStatement.close();
            result.close();
            return ovChipkaart;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger)  {
        try {
            PreparedStatement prepStatement = conn.prepareStatement("SELECT * FROM ov_chipkaart WHERE reiziger_id=?");
            prepStatement.setInt(1, reiziger.getId());

            ResultSet result = prepStatement.executeQuery();

            List<OVChipkaart> ovChipkaarten = new ArrayList<>();

            while(result.next()){

                int kaartNummer = result.getInt("kaart_nummer");
                Date geldigTot = result.getDate("geldig_tot");
                int klasse = result.getInt("klasse");
                double saldo = result.getDouble("saldo");

                ovChipkaarten.add(new OVChipkaart(kaartNummer,geldigTot,klasse,saldo,reiziger));

            }
            prepStatement.close();
            result.close();
            return ovChipkaarten;
        } catch(SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<OVChipkaart> findAll()  {
        try {
            PreparedStatement prepStatement = conn.prepareStatement("SELECT * FROM ov_chipkaart");
            ResultSet result = prepStatement.executeQuery();

            List<OVChipkaart> ovChipkaarten = new ArrayList<>();

            while(result.next()){

                int kaartNummer = result.getInt("kaart_nummer");
                Date geldigTot = result.getDate("geldig_tot");
                int klasse = result.getInt("klasse");
                double saldo = result.getDouble("saldo");
                int reizigerId = result.getInt("reiziger_id");

                ovChipkaarten.add(new OVChipkaart(kaartNummer,geldigTot,klasse,saldo,rdao.findById(reizigerId)));

            }
            prepStatement.close();
            result.close();
            return ovChipkaarten;
        } catch(SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
