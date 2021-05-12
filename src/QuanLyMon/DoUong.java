/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyMon;

import controller.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class DoUong extends Mon {

    private String coDa;
    private DanhMucEnum danhMuc;
    Connection conn= null;
    private PreparedStatement ptmt = null;

    public DoUong() {
    }

    ;
    public DoUong(int mm,String ten, int gia, String tinhTrang, String thoiDiemBan, String da) {
        super(mm,ten, gia, tinhTrang, thoiDiemBan);
        this.coDa = da;
    }

    private Scanner sc = new Scanner(System.in);
    
    public DoUong InputDoUong(DoUong douong) {
        super.Nhap(douong);
        System.out.print("Có đá: ");
        String coda = sc.nextLine();
        douong.setCoDa(coda);
        DAO t = new DAO();
        conn = t.ketNoi();
        String sql = "INSERT INTO dbo.ThucDon(MaMon,TenMon,Gia,TinhNang,ThoiDiemBan,CoDa) VALUES(?,?,?,?,?,?);";
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt = conn.prepareCall(sql);
            ptmt.setInt(1, this.getMaMon());
            ptmt.setString(2, this.getTenMon());
            ptmt.setInt(3, this.getGia());
            ptmt.setString(4, "No");
            ptmt.setString(5, this.getThoiDiemBan());
            ptmt.setString(6, this.getCoDa());
            
            int k = ptmt.executeUpdate();
            if(k!=0){
                System.out.print("Successed");
            }
            else
                System.out.print("Them khong thanh cong");
            ptmt.close();
            conn.close();
            return douong;
        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public String toString() {
        String m = super.toString();
        m += String.format("Có đá: %s\n", this.coDa);
        return m; //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public String layLoai() {
//        return "Đồ uống";
//    }

    public String getCoDa() {
        return coDa;
    }

    public void setCoDa(String coDa) {
        this.coDa = coDa;
    }

}
