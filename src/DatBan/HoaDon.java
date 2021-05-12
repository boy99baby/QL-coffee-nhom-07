/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatBan;

import controller.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author shini
 */
public class HoaDon {

    Connection cnt = null;

    public HoaDon() {
    }
    private PreparedStatement _pre = null;

    private ArrayList<HoaDon> list;
    private ArrayList<String> listfood;

    public ArrayList<String> ViewFoodAddList(String idbill, String Namevalue) {
        ResultSet rs = null;
        listfood = new ArrayList<>();
        DAO t = new DAO();
        cnt = t.ketNoi();
        int ans = 0;
        String sql = "SELECT TenMon FROM dbo.HoaDon,dbo.Instance_People ,dbo.Instance_ThucDon,dbo.ThucDon,dbo.NhanVien,dbo.People WHERE  \n"
                + "HoaDon.IDBill = N'" + idbill + "' AND\n"
                + "Instance_People.ID = People.ID\n"
                + "AND Instance_ThucDon.IDBill = Instance_People.IDBill\n"
                + "AND HoaDon.IDBill = Instance_People.IDBill\n"
                + "AND Instance_ThucDon.IDBill = HoaDon.IDBill\n"
                + "AND Name = N'" + Namevalue + "' AND Instance_ThucDon.MaMon = ThucDon.MaMon";
        try {
            _pre = cnt.prepareCall(sql);
            rs = _pre.executeQuery();
            while (rs.next()) {
                listfood.add(rs.getString("TenMon"));
            }
        } catch (Exception ex) {
        }
        return listfood;
    }

    // Create new bill with your new ID
    public boolean CreateBill(String idbill, String name, String maban, int a) {
        DAO data = new DAO();
        cnt = data.ketNoi();
        String sql = "CreateHD ?,?,?,?";
        try {
            _pre = cnt.prepareCall(sql);
            _pre.setString(1, idbill);
            _pre.setString(2, name);
            _pre.setString(3, maban);
            _pre.setInt(4, a);
            int executeUpdate = _pre.executeUpdate();
            _pre.close();
            cnt.close();

        } catch (Exception ex) {
//            System.out.println("Tell me your problem.");
//            ex.printStackTrace();
        }
        return true;
    }

    //output Bill and pay Money
    public int PayMoney(String idbill) {
        DAO t = new DAO();
        cnt = t.ketNoi();
        ResultSet rs = null;
        int Pay = 1;
        String sql = "SELECT SUM(gia) as Moneyddddd FROM dbo.HoaDon ,dbo.Instance_ThucDon,dbo.ThucDon WHERE  \n"
                + "HoaDon.IDBill = N'" + idbill + "'\n"
                + "AND Instance_ThucDon.IDBill = HoaDon.IDBill\n"
                + "AND Instance_ThucDon.MaMon = ThucDon.MaMon ;";
        try {
            _pre = cnt.prepareCall(sql);
            rs = _pre.executeQuery();

            while (rs.next()) {
                int a = rs.getInt("Moneyddddd");
                Pay = a;
            }
            int executeUpdate = _pre.executeUpdate();
            _pre.close();
            cnt.close();
        } catch (Exception e) {
            //   e.printStackTrace();
        }
        return Pay;
    }

    public ArrayList<String> ListFoodPay(String idbill) {
        ArrayList<String> listfoods = new ArrayList<>();
        DAO t = new DAO();
        cnt = t.ketNoi();
        ResultSet rs = null;
        int Pay = 1;
        String sql = "SELECT TenMon FROM dbo.HoaDon ,dbo.Instance_ThucDon,dbo.ThucDon WHERE  \n"
                + "HoaDon.IDBill = N'" + idbill + "'\n"
                + "AND Instance_ThucDon.IDBill = HoaDon.IDBill\n"
                + "AND Instance_ThucDon.MaMon = ThucDon.MaMon ;";
        try {
            _pre = cnt.prepareCall(sql);
            rs = _pre.executeQuery();

            while (rs.next()) {
                String kq = rs.getString("TenMon");
                listfoods.add(kq);
            }
            int executeUpdate = _pre.executeUpdate();
            _pre.close();
            cnt.close();
        } catch (Exception e) {
            //   e.printStackTrace();
        }
        return listfoods;
    }

    // Create Instance_ThucDon with bill and maHD
    public boolean AddFoodBill(int Mahd, String idbill) {
        DAO t = new DAO();
        cnt = t.ketNoi();
        String SQL = " IN_Thucdon ?,?";
        try {
            _pre = cnt.prepareCall(SQL);
            _pre.setString(1, idbill);
            _pre.setInt(2, Mahd);

            int executeUpdate = _pre.executeUpdate();
            _pre.close();
            cnt.close();
        } catch (Exception ex) {
//            System.out.println("Show me??");
//            ex.printStackTrace();
        }
        return true;
    }

    //Money/
    public int TongBill(String idbill, String Namevalue) {
        int a = 0;
        ResultSet rs = null;
        DAO t = new DAO();
        cnt = t.ketNoi();
        String sql = " SELECT SUM(Gia) AS TIEN FROM dbo.HoaDon,dbo.Instance_People ,dbo.Instance_ThucDon,dbo.ThucDon,dbo.People WHERE  \n"
                + "HoaDon.IDBill = N'" + idbill + "' AND\n"
                + "Instance_People.ID = People.ID\n"
                + "AND Instance_ThucDon.IDBill = Instance_People.IDBill\n"
                + "AND HoaDon.IDBill = Instance_People.IDBill\n"
                + "AND Instance_ThucDon.IDBill = HoaDon.IDBill\n"
                + "AND Name = N'" + Namevalue + "' AND Instance_ThucDon.MaMon = ThucDon.MaMon";
        try {
            _pre = cnt.prepareCall(sql);
            rs = _pre.executeQuery(sql);
            a = rs.getInt("TIEN");
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return a;
    }

    //Out put Bill
    public String OuputBill(String name, String idbill) {
        String outputString = "";
        int Price = TongBill(idbill, name);
        for (String i : listfood) {
            System.out.println("Tên Món: " + i);
        }
        System.out.println("Tổng tiền: " + Price);
        return "Thank you " + name;
    }
}
