/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyNhanVien;

import controller.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.management.AttributeList;

/**
 *
 * @author DELL
 */
public class QuanLiNhanVien {

    Connection conn = null;
    private PreparedStatement ptmt = null;
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");

    /**
     *
     * @param id
     * @return
     */
    
    
    private ArrayList<NhanVien> list = new ArrayList<>();

    // Return a view list with talble and value input
    public ArrayList<NhanVien> viewResultls(String table, String value) {
        ResultSet rs = null;
        DAO t = new DAO();
        conn = t.ketNoi();
        ArrayList<NhanVien> listvn = new ArrayList<>();
        String sql = "SELECT * FROM dbo.NhanVien WHERE " + table + " = " + value;
        try {
            ptmt = conn.prepareCall(sql);
            rs = ptmt.executeQuery();
//            rs.first();
            while (rs.next()) {
                NhanVien sv = new NhanVien();

                sv.setMaNV(rs.getInt("MaNv"));
                sv.setHoTen(rs.getString("HoTen"));
                sv.setGioiTinh(rs.getString("GioiTinh"));
                sv.setQueQuan(rs.getString("QueQuan"));
                sv.setNgaySinh(rs.getDate("NgaySinh"));
                sv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                sv.setBoPhan(rs.getString("BoPhan"));

                listvn.add(sv);
            }
            int executeUpdate = ptmt.executeUpdate();
            ptmt.close();
            conn.close();
        } catch (Exception ex) {

        }
        return listvn;
    }

    // return a NhanVien List
    public ArrayList<NhanVien> viewList() {
        ResultSet rs = null;
        DAO t = new DAO();
        conn = t.ketNoi();
        ArrayList<NhanVien> listNV = new ArrayList<>();

        String sql = "SELECT * FROM NhanVien";
        try {
            ptmt = conn.prepareCall(sql);
            rs = ptmt.executeQuery();
//            rs.first();
            while (rs.next()) {
                NhanVien sv = new NhanVien();

                sv.setMaNV(rs.getInt("MaNv"));
                sv.setHoTen(rs.getString("HoTen"));
                sv.setGioiTinh(rs.getString("GioiTinh"));
                sv.setQueQuan(rs.getString("QueQuan"));
                sv.setNgaySinh(rs.getDate("NgaySinh"));
                sv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                sv.setBoPhan(rs.getString("BoPhan"));

                listNV.add(sv);
            }
            int executeUpdate = ptmt.executeUpdate();
            ptmt.close();
            conn.close();
        } catch (Exception ex) {

        }
        return listNV;

    }

    // Find NhanVien wiht value Home Town
    public boolean findwithHomeTown(String kw) {
        if (viewResultls("QueQuan", kw) != null) {
            System.out.println(viewResultls("QueQuan", kw));
            return true;
        }
        return false;
    }

    // Delete a NhanVien with id input
    public boolean xoaNhanVien(int id) {
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "DELETE FROM dbo.NhanVien where MaNv =" + id;
        try {
            ptmt = conn.prepareStatement(sql);

            int k = ptmt.executeUpdate();

            if (k != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Find NhanVien with id
    public boolean Findwithid(String cv) {
        if (viewResultls("MaNV", cv) != null) {
            System.out.println(viewResultls("MaNV", cv));
            return true;
        }
        return false;
    }
    
    // Find Nhan vien with their name
    public boolean FindWithName(String name) {
        if (ViewName(name) != null) {
            System.out.println(ViewName(name));
            return true;
        }
        return false;
    }

    
    // Return a viewName for Findwihtname function
    public ArrayList<NhanVien> ViewName(String value) {
        ResultSet rs = null;
        DAO t = new DAO();
        conn = t.ketNoi();
        ArrayList<NhanVien> listvn = new ArrayList<>();
        String sql = "SELECT * FROM dbo.NhanVien WHERE HoTen LIKE N'%" + value + "%'";
        try {
            ptmt = conn.prepareCall(sql);
            rs = ptmt.executeQuery();
//            rs.first();
            while (rs.next()) {
                NhanVien sv = new NhanVien();

                sv.setMaNV(rs.getInt("MaNv"));
                sv.setHoTen(rs.getString("HoTen"));
                sv.setGioiTinh(rs.getString("GioiTinh"));
                sv.setQueQuan(rs.getString("QueQuan"));
                sv.setNgaySinh(rs.getDate("NgaySinh"));
                sv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                sv.setBoPhan(rs.getString("BoPhan"));

                listvn.add(sv);
            }
            int executeUpdate = ptmt.executeUpdate();
            ptmt.close();
            conn.close();
        } catch (Exception ex) {

        }
        return listvn;
    }

    // Function Menus
    public void traCuuNhanVien() throws ParseException {
        //QuanLiNhanVien dctc = new QuanLiNhanVien();
        System.out.println("1.Theo id.");
        System.out.println("2.Theo Họ Tên.");
        System.out.println("3.Theo quê quán.");
        System.out.print("Chọn mục tra cứu: ");
        int chon = scanner.nextInt();
        ArrayList<NhanVien> list = new ArrayList<>();
        switch (chon) {
            case 1:
                String value = "MaNV";
                System.out.println("Enter your id: ");
                scanner.nextLine();
                String kw = scanner.nextLine();
                System.out.println("=== NhanVien List find ===");
                if (!Findwithid(kw)) {
                    System.out.println("no any people");
                }
                break;
            case 2:
                System.out.println("Enter your Name");
                scanner.nextLine();
                String kw1 = scanner.nextLine();
                System.out.println("=== NhanVien List find ===");
                if (!FindWithName(kw1)) {
                    System.out.println("no any people");
                }
                break;
//                
            case 3:
                System.out.println("Enter your Home town:");
                scanner.nextLine();
                String kw2 = scanner.nextLine();
                System.out.println("=== NhanVien List find ===");
                if (!findwithHomeTown(kw2)) {
                    System.out.println("no any people");
                }
                break;
        }
    }

    public String toString() {
        String kq = "";
        for (NhanVien sv : this.list) {
            kq += sv;
        }
        return kq;
    }
}
