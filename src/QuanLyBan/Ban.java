/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBan;

import controller.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Ban {

    /**
     * @return the maBan
     */
    public String getMaBan() {
        return maBan;
    }

    /**
     * @param maBan the maBan to set
     */
    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    /**
     * @return the sucChua
     */
    public int getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    /**
     * @return the tinhTrang
     */
    public boolean isTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    Connection conn = null;
    private PreparedStatement ptmt = null;

    private String maBan;
    private int sucChua;
    private boolean tinhTrang;
    private static int dem = 0;

    {
        setTinhTrang(true);
        ++dem;
    }

    public Ban() {
    }
    
    public Ban Nhap(Ban inpBan) {
        Scanner scanner = new Scanner(System.in);
    //    String hungNextline1 = scanner.nextLine();
        System.out.print("Mã Bàn: ");
        String mb = scanner.nextLine();
        inpBan.setMaBan(mb);
        System.out.print("Sức chứa: ");
        int sc = scanner.nextInt();
        inpBan.setSucChua(sc);
        return inpBan;
    }

    @Override
    public String toString() {
        String m = String.format("Mã bàn: %s\n"
                + "Sức chứa: %d\n",
                this.getMaBan(),
                 this.getSucChua()
        );
        return m;
    }
}
