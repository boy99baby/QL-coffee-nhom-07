/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyMon;

//import QuanLyMon.Mon;
import controller.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.plaf.synth.Region;

/**
 *
 * @author DELL
 */
public class QuanLiMon {

    //private ArrayList<Mon> thucdon = new ArrayList<>();
    Connection conn = null;
    private PreparedStatement ptmt = null;
    private Mon mon = null;

    public boolean themMon(Mon mon) {
        mon = mon.Nhap(mon);
        return true;
    }

    public ArrayList<Mon> ViewResultfood() {
        ResultSet rs = null;
        ArrayList<Mon> listfood = new ArrayList<>();
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "SELECT MaMon,TenMon,Gia,TinhNang,ThoiDiemBan FROM dbo.ThucDon ";
        try {
            ptmt = conn.prepareCall(sql);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                Mon adm = new Mon();

                adm.setMaMon(rs.getInt("MaMon"));
                adm.setTenMon(rs.getString("TenMon"));
                adm.setGia(rs.getInt("Gia"));
                adm.setTinhTrang(rs.getString("TinhNang"));
                adm.setThoiDiemBan(rs.getString("ThoiDiemBan"));

                listfood.add(adm);
            }
            ptmt.executeQuery();
            ptmt.close();
            conn.close();
        } catch (SQLException ex) {

        }
        return listfood;
    }

    // View id food list
    public int ViewFoodList() {
//        ArrayList<String> listf = new ArrayList<>();
        int Reslutad = 0;
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "SELECT * FROM dbo.ThucDon";
        ResultSet rs = null;
        try {
            ptmt = conn.prepareCall(sql);
           rs = ptmt.executeQuery();
            while (rs.next()) {
                Reslutad++;
            }
            int executeUpdate = ptmt.executeUpdate();
            ptmt.close();
            conn.close();
        } catch (Exception ex) {
          //  System.err.println("Something wrong during add or execute query.");
        }
        return Reslutad;
    }

    // check input <= id foodlist
    public boolean CheckFoodList(int a) {
        return a < ViewFoodList() && a > 0;
    }

    
    // output List food 
    public void xuatOutput(ArrayList<Mon> mon) {
        if (mon != null) {
            for (Mon f1 : mon) {
                System.out.println(mon);
            }
        } else {
            System.err.println("Something wrong during execute.");
        }
    }

    // Delete Food
    public boolean xoaMon(int maMon) {
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "DELETE FROM ThucDon where MaMon =" + maMon;
        try {
            ptmt = conn.prepareStatement(sql);

            int k = ptmt.executeUpdate();
            if (k != 0) {

                ptmt.close();
                conn.close();
                return true;
            } else {

                ptmt.close();
                conn.close();
                System.err.println("Something wrong or wrong input type.");
                return false;
            }
        } catch (Exception e) {
//            System.err.println("Something Wrong or wrong input type or your connection was disable.");
//            e.printStackTrace();
            return false;
        }
    }
    
    //Find Food With name
    public ArrayList<Mon> FindWithName1(String kw) {
        ArrayList<Mon> thucdon = ViewResultfood();
        ArrayList<Mon> foodNeed = new ArrayList<>();
        for (Mon find1 : thucdon) {
            if (find1.getTenMon().contains(kw)) {
                foodNeed.add(find1);
            }
        }
        return foodNeed;
    }
    
    // Find Food List
    public ArrayList<Mon> FindWith_Name(String kw) {
        ArrayList<Mon> thucdon = new ArrayList<>();
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "SELECT * FROM dbo.ThucDon where TenMon Like %" + kw + "%";
        ResultSet rs = null;
        try {
            ptmt = conn.prepareCall(sql);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                Mon monn = new Mon();
                monn.setMaMon(rs.getInt("MaMon"));
                monn.setTenMon(rs.getString("TenMon"));
                monn.setGia(rs.getInt("Gia"));
                monn.setThoiDiemBan(rs.getString("ThoiDiemBan"));
                monn.setTinhTrang(rs.getString("TinhNang"));
                thucdon.add(monn);
            }
            int executeUpdate = ptmt.executeUpdate();
            ptmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return thucdon;
    }
    
    //Find Food with price
    public ArrayList<Mon> FindWithPrice(int f_price, int l_price) {
        ArrayList<Mon> listf = ViewResultfood();
        ArrayList<Mon> listAnw = new ArrayList<>();
        for (Mon duyetL : listf) {
            if (duyetL.getGia() > f_price && duyetL.getGia() < l_price) {
                listAnw.add(duyetL);
            }
        }
        return listAnw;
    }
    public ArrayList<Mon> timKiemTheoGia(int f_price, int L_price) {
        ArrayList<Mon> thucdon = new ArrayList<>();
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "SELECT * FROM dbo.ThucDon ";
        ResultSet rs = null;
        try {
            ptmt = conn.prepareCall(sql);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("Gia") > f_price && rs.getInt("Gia") < L_price) {
                    mon.setMaMon(rs.getInt("MaMon"));
                }
                mon.setTenMon(rs.getString("TenMon"));
                mon.setGia(rs.getInt("Gia"));
                mon.setThoiDiemBan(rs.getString("ThoiDiemBan"));
                mon.setTinhTrang(rs.getString("TinhTrang"));
                thucdon.add(mon);
            }
            int executeUpdate = ptmt.executeUpdate();
            ptmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return thucdon;
    }
    public void sapXepTang() {
//        this.thucdon.sort((mon1, mon2) -> mon1.soSanh(mon2));
    }

    public void sapXepGiam() {
//        this.thucdon.sort((mon1, mon2) -> -mon1.soSanh(mon2));
    }

}
