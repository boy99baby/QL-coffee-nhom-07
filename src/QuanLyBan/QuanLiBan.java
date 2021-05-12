/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBan;

import QuanLyBan.Ban;
import controller.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class QuanLiBan extends Ban {

    private ArrayList<Ban> dsBan = new ArrayList<>();

    Connection _con = null;
    private PreparedStatement _pre = null;

    // Check Maban 
    public boolean traCuu(String maban) {
        DAO t = new DAO();
        ResultSet rs = null;
        _con = t.ketNoi();
        String sql = "Select * from dbo.Ban where MaBan = " + maban;
        try {
            _pre = _con.prepareCall(sql);
            rs = _pre.executeQuery(sql);
            if (rs != null) {
                return true;
            }
            _pre.close();
            _con.close();
        } catch (Exception ex) {
            System.err.println("Something wrong during execute or wrong input type.");
            return false;
        }
        return false;
    }

    // Enter your output ban.
    public boolean NhapBan() {
        //Scanner scanner = new Scanner(System.in);
        Ban sv = new Ban();
        sv = Nhap(sv);
        DAO t = new DAO();
        _con = t.ketNoi();
        String sql = "ADDBan ?, ?, 1 ";
        try {
            _pre = _con.prepareCall(sql);
            _pre.setString(1, sv.getMaBan());
            _pre.setInt(2, sv.getSucChua());
            _pre.executeUpdate();
            _pre.close();
            _con.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Delete ban
    public boolean XoaBan() {
        DAO t = new DAO();
        _con = t.ketNoi();
        String sql = " DELETE dbo.Ban WHERE MaBan = N'999'";
        try {
            _pre = conn.prepareStatement(sql);
            int executeQuery = _pre.executeUpdate();
            if (executeQuery != 0) {
                _pre.close();
                _con.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteTable(String kw){
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "DELETE  dbo.Ban WHERE MaBan = N'"+kw+"'";
        try {
             _pre = conn.prepareStatement(sql);

            int k = _pre.executeUpdate();

            if (k != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    // View Ban Null
    public ArrayList<Ban> xemDSBanTrong(ArrayList<Ban> viewBan) {
        DAO t = new DAO();
        ResultSet rs = null;
        _con = t.ketNoi();
        String sql = "Select * from dbo.Ban Where TinhTrang = 1 ";
        try {
            _pre = _con.prepareCall(sql);
            rs = _pre.executeQuery();
            while (rs.next()) {
                Ban addb = new Ban();
                addb.setMaBan(rs.getString("MaBan"));
                addb.setSucChua(rs.getInt("SucChua"));
                viewBan.add(addb);
            }
            _pre.executeUpdate();
            _pre.close();
            _con.close();
        } catch (Exception ex) {
        }
        return viewBan;
    }

    public boolean outputview(ArrayList<Ban> ban) {
        if (ban != null) {
            for (Ban ds : ban) {
                System.out.println(ds);
            }
        }
        return false;
    }

    public ArrayList<Ban> getDsBan() {
        return dsBan;
    }

    public void setDsBan(ArrayList<Ban> dsBan) {
        this.dsBan = dsBan;
        for (Ban ds : dsBan) {
            System.out.println(ds);
        }
    }

    public boolean XoaBan(String kw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
