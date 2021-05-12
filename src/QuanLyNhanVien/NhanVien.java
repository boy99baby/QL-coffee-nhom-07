/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyNhanVien;

import controller.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class NhanVien {

    Connection conn = null;
    private PreparedStatement ptmt = null;

    private int maNV;
    private String hoTen;
    private String gioiTinh;
    private String queQuan;
    private Date ngaySinh;
    private Date ngayVaoLam;
    private String boPhan;
    private static int demMaNV = 0;

    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");

    public NhanVien() {
        
    }

    // input NhanVien into database
    public NhanVien(String ten, String gioiTinh, String queQuan, String ngaySinh, String ngayVaoLam, String bp) throws ParseException {
        this.hoTen = ten;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.ngaySinh = f.parse(queQuan);
        this.ngayVaoLam = f.parse(ngayVaoLam);
        this.boPhan = bp;
    }
    
    public NhanVien UpdateNV(NhanVien nv) {
        String ngaySinhTam;
        String ngayVaoLamTam;
        System.out.println("==== Nhập thông tin Cập Nhật ====");
        //String nextLine = scanner.nextLine();
        System.out.print("Họ tên: ");
        String ht = scanner.nextLine();
        nv.setHoTen(ht);
        System.out.print("Giới tính: ");
        String gt = scanner.nextLine();
        nv.setGioiTinh(gt);
        System.out.print("Quê quán: ");
        String qq = scanner.nextLine();
        nv.setQueQuan(qq);
        System.out.print("Ngày sinh: ");
        ngaySinhTam = scanner.nextLine();
        try{
        Date nst = f.parse(ngaySinhTam);
        nv.setNgaySinh(nst);
        System.out.print("Ngày vào làm: ");
        ngayVaoLamTam = scanner.nextLine();
        Date nvl = f.parse(ngayVaoLamTam);
        nv.setNgayVaoLam(nvl);
        }
        catch(ParseException ex){
            return null;
        }
        return nv;
    }

    public boolean capNhat(NhanVien nv) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "update dbo.NhanVien set MaNv = '" + nv.getMaNV() + "';"
                + "HoTen = '" + nv.getHoTen() + "';"
                + "GioiTinh = '" + nv.getGioiTinh() + "' where MaNv ='" + nv.getMaNV() + "'";
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, nv.getMaNV());
            ptmt.setString(2, nv.getHoTen());
            ptmt.setString(3, nv.getGioiTinh());
            ptmt.setString(4, nv.getQueQuan());
            ptmt.setDate(5, new java.sql.Date(nv.getNgaySinh().getTime()));
            ptmt.setDate(6, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            int k = ptmt.executeUpdate();
            
            ptmt.close();
            conn.close();
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

    public boolean Nhap(NhanVien nv) throws ParseException {
        nv = UpdateNV(nv);
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "INSERT INTO dbo.NhanVien(MaNV,HoTen,GioiTinh,QueQuan,NgaySinh,NgayVaoLam) VALUES(?,?,?,?,?,?);";
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, nv.getMaNV());
            ptmt.setString(2, nv.getHoTen());
            ptmt.setString(3, nv.getGioiTinh());
            ptmt.setString(4, nv.getQueQuan());
            ptmt.setDate(5, new java.sql.Date(nv.getNgaySinh().getTime()));
            ptmt.setDate(6, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            // ptmt.setString(7, this.boPhan);
            int k = ptmt.executeUpdate();
            ptmt.close();
            conn.close();
            if (k != 0) {
                return true;
            } else {
                System.err.println("Some Error during add "+ nv.getHoTen() +" into your database");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Some Error during add "+ nv.getHoTen() +" into your database");
            return false;
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String m = String.format("Mã nhân viên: %d\nTên nhân viên: %s\n"
                + "Giới tính: %s\nQuê Quán: %s\nNgày sinh: %s\nNgày vào làm: %s\nBộ phận: %s\n",
                this.maNV, this.hoTen, this.gioiTinh, this.queQuan,
                f.format(this.ngaySinh), f.format(this.ngayVaoLam), this.boPhan);
        return m; //To change body of generated methods, choose Tools | Templates.
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    /**
     * @return the boPhan
     */
    public String getBoPhan() {
        return boPhan;
    }

    /**
     * @param boPhan the boPhan to set
     */
    public void setBoPhan(String boPhan) {
        this.boPhan = boPhan;
    }

}
