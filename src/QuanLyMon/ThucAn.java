/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyMon;


import controller.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class ThucAn extends Mon {
    Scanner scanner = new Scanner(System.in);
    private String chay;
    private DanhMucEnum danhMuc;
    Connection conn= null;
    private PreparedStatement ptmt = null;


    public ThucAn() {
    }

    ;
    public ThucAn(int mm,String ten, int gia, String tinhTrang, String thoiDiemBan, String chay) {
        super(mm,ten, gia, tinhTrang, thoiDiemBan);
        this.chay = chay;
    }        
            
    public ThucAn InputThucAn(ThucAn thucan) {
        super.Nhap(thucan);
        System.out.print("Đồ chay: ");
        String chayy = scanner.nextLine();
        thucan.setChay(chayy);
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "INSERT INTO dbo.ThucDon(MaMon,TenMon,Gia,TinhNang,ThoiDiemBan,Chay) VALUES(?,?,?,?,?,?);";
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, this.getMaMon());
            ptmt.setString(2, this.getTenMon());
            ptmt.setInt(3, this.getGia());
            ptmt.setString(4, this.getTinhTrang());
            ptmt.setString(5, this.getThoiDiemBan());
            ptmt.setString(6, this.getChay());
            
            int k = ptmt.executeUpdate();
            if(k!=0){
                System.out.print("Them thanh cong\n");
            }
            else
                System.out.print("Them khong thanh cong");
            ptmt.close();
            conn.close();
            return thucan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        String m = super.toString();
        m += String.format("Đồ chay: %s\n", this.chay);
        return m; //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public String layLoai() {
//        return "Thức ăn";
//    }

//    public String getDanhMuc() {
//        return danhMuc;
//    }
//
//    public void setDanhMuc(String danhMuc) {
//        this.danhMuc = danhMuc;
//    }
//    
    public String getChay() {
        return chay;
    }

    public void setChay(String chay) {
        this.chay = chay;
    }

}
