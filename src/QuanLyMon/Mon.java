/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyMon;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class Mon {

    private int maMon;
    private String tenMon;
    private int gia;
    private String tinhTrang;
    private String thoiDiemBan;

    public Mon() {

    }

//    public abstract String layLoai();
    public Mon(int mm,String ten, int gia, String tinhTrang, String thoiDiemBan) {
        this.maMon = mm;
        this.tenMon = ten;
        this.gia = gia;
        this.tinhTrang = tinhTrang;
        this.thoiDiemBan = thoiDiemBan;
    }

    public Mon Nhap(Mon addmon) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mã Món(int): ");
        int MM = scanner.nextInt();
        addmon.setMaMon(MM);
        scanner.nextLine();
        System.out.print("Tên món: ");
        String tmon = scanner.nextLine();
        addmon.setTenMon(tmon);
        System.out.print("Giá: ");
        int Price = scanner.nextInt();
        addmon.setGia(Price);
        String hungNextline2 = scanner.nextLine();
        System.out.print("Thời điểm bán: ");
        String tdb = scanner.nextLine();
        addmon.setThoiDiemBan(tdb);
        return addmon;
    }

    @Override
    public String toString() {
        String m = String.format("Mã món: %s\nTên món: %s\nGiá: %d\nTình trạng:"
                + " %s\nThời điểm bán: %s\n", this.getMaMon(),
                this.tenMon, this.gia, this.tinhTrang, this.thoiDiemBan);
        return m; //To change body of generated methods, choose Tools | Templates.
    }

    public int soSanh(Mon mon1) {
        double k = this.getGia() - mon1.getGia();
        if (k > 0) {
            return 1;
        } else {
            return -1;
        }

    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getThoiDiemBan() {
        return thoiDiemBan;
    }

    public void setThoiDiemBan(String thoiDiemBan) {
        this.thoiDiemBan = thoiDiemBan;
    }

    /**
     * @return the maMon
     */
    public int getMaMon() {
        return maMon;
    }

    /**
     * @param maMon the maMon to set
     */
    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

}
