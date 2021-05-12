/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangnhap;

import DatBan.DanhSachMonChon;
import QuanLyMon.DanhMucEnum;
import DatBan.HoaDon;
import QuanLyMon.MonFactory;
import DatBan.MotMonChon;
import QuanLyNhanVien.NhanVien;
import DatBan.QuanLiDatBan;
import QuanLyMon.QuanLiMon;
import QuanLyNhanVien.QuanLiNhanVien;
import ThongKe.ThongKe;
import QuanLyMon.ThucAn;
import QuanLyMon.DoUong;
import QuanLyMon.Mon;
import QuanLyBan.QuanLiBan;
import QuanLyBan.Ban;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import sun.applet.Main;

/**
 *
 * @author DELL
 */
public class test {

    public static void menu(QuanLiNhanVien qlnv, QuanLiMon qlmon, QuanLiBan qlban,
            QuanLiDatBan qlDatBan, ThongKe ThongKe) throws ParseException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int chon;
        int kq;
        do {
            System.out.println("***CHƯƠNG TRÌNH QUẢN LÍ QUÁN CAFE***");
            System.out.println("1. Quản lí nhân viên");
            System.out.println("2. Quản lí món");
            System.out.println("3. Quản lí bàn");
            System.out.println("4. Đặt bàn");
            System.out.println("5. Thống kê");
            System.out.println("0. Thoát");
            System.out.print("Chọn số: ");

            chon = scanner.nextInt();
            switch (chon) {
                case 1:
                    menuNhanVien(qlnv);
                    break;
                case 2:
                    menuMon(qlmon);
                    break;
                case 3:
                    menuBan(qlban);
                    break;
                case 4:
                    menuDatBan(qlmon, qlban, qlDatBan);
                    break;
                case 5:
                    menuThongKe(qlDatBan);
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng");
                    System.exit(0);
                    break;
            }
        } while (chon != 0);
    }

    public static void menuNhanVien(QuanLiNhanVien qlnv) throws ParseException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int chon1;
        do {
            System.out.println("---Chức Năng Nhân Viên---");
            System.out.println("1. Thêm Nhân Viên");
            System.out.println("2. Xóa Nhân Viên");
            System.out.println("3. Cập nhật Nhân Viên");
            System.out.println("4. Xem danh sách Nhân Viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn số: ");
            chon1 = scanner.nextInt();
            switch (chon1) {
                case 1:
                    NhanVien newnv = new NhanVien();
                    if (newnv.Nhap(newnv)) {
                        System.out.println("Success");
                    }
                    break;
                case 2:
                    System.out.print("Nhập mã số nhân viên cần xóa: ");
                    int xoa = scanner.nextInt();
                    QuanLiNhanVien sv = new QuanLiNhanVien();
                    if (sv.xoaNhanVien(xoa)) {
                        System.out.println("Successed.");
                    } else {
                        System.err.println("Somthing was Wrong when we try to deleted.");
                    }
                    break;
                case 3:
                    System.out.print("Enter your MaSV need tobe update: ");
                    int capnhat = scanner.nextInt();
                    // Pending.....
                    break;
                case 4:
                    System.out.println("===Danh Sach Nhan Vien===");
                    QuanLiNhanVien ql = new QuanLiNhanVien();
                    System.out.println(ql.viewList());
                    break;
                case 5:
                    QuanLiNhanVien ql1 = new QuanLiNhanVien();
                    System.out.println("Tìm Kiếm Nhân Viên.");
                    ql1.traCuuNhanVien();
                    break;
                case 0:
                    break;
            }
        } while (chon1 != 0);

    }

    public static void menuMon(QuanLiMon qlmon) {
        Scanner scanner = new Scanner(System.in);
        int chon2;
        do {
            System.out.println("---Quản Lí Thực Đơn---");
            System.out.println("1. Thêm món");
            System.out.println("2. Xóa món");
            System.out.println("3. Xem thực đơn");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Sắp xếp theo giá");
            System.out.println("0. Quay lại");
            System.out.print("Chọn số: ");

            chon2 = scanner.nextInt();
            switch (chon2) {
                case 1:
                    System.out.println("1. Thức ăn");
                    System.out.println("2. Đồ uống ");
                    System.out.println("Chọn danh mục: ");
                    int chonDanhMuc;
                    chonDanhMuc = scanner.nextInt();
                    Mon monThem1,
                     monThem2;
                    ThucAn thucAn1 = new ThucAn();
                    DoUong doUuong1 = new DoUong();
                    switch (chonDanhMuc) {
                        case 1:
                            monThem1 = MonFactory.layLoai(DanhMucEnum.THUC_AN);
                            thucAn1.InputThucAn(thucAn1);
                            break;
                        case 2:
                            monThem2 = MonFactory.layLoai(DanhMucEnum.DO_UONG);
                            doUuong1.InputDoUong(doUuong1);
                            break;
                        default:
                            System.out.println("Khong hợp lệ.");
                    }

                    break;
                case 2:
                    int maMonXoa;
                    System.out.print("Nhập mã món cần xóa: ");
                    maMonXoa = scanner.nextInt();
                    if (qlmon.xoaMon(maMonXoa)) {
                        System.out.println("Successed");
                    } else {
                        System.out.println("Something wrong in your input type or some exception.");
                    }
                    break;
                case 3:
                    System.out.println("=== Thực Đơn ===");
//                    QuanLiMon ql = new QuanLiMon();
//                    System.out.println(ql.ViewResultfood());
                    //Mon nab = new Mon(1,"Thhang", 3, "chay", "Now");
                    //System.out.println(nab);
                    break;
                case 4:
                    System.out.println("1. Tìm kiếm theo tên");
                    System.out.println("2. Tìm kiếm theo khoảng giá");
                    System.out.print("Chọn: ");
                    int chonTimKiem = scanner.nextInt();
                    switch (chonTimKiem) {
                        case 1:
                            System.out.print("Nhập tên tìm kiếm: ");
                            String hungCase4 = scanner.nextLine();
                            String tenTimKiem = scanner.nextLine();
                            qlmon.xuatOutput(qlmon.FindWith_Name(tenTimKiem));
                            break;
                        case 2:
                            System.out.print("Nhập giá trong khoảng: ");
                            int gia1 = scanner.nextInt();
                            int gia2 = scanner.nextInt();
                            qlmon.xuatOutput(qlmon.timKiemTheoGia(gia1, gia2));
                            break;
                    }
                    break;
            }
        } while (chon2 != 0);
    }

    public static void menuBan(QuanLiBan qlban) {
        Scanner scanner = new Scanner(System.in);
        int chonBan;
        do {
            System.out.println("---Quản Lí Bàn---");
            System.out.println("1. Thêm bàn");
            System.out.println("2. Xóa bàn");
            System.out.println("3. Xem danh sách bàn Trống");
            System.out.println("0. Quay lại");
            System.out.print("Chọn số: ");

            chonBan = scanner.nextInt();
            int sucChua;
            switch (chonBan) {
                case 1: //Add
                    if (qlban.NhapBan()) {
                        System.out.println("Successed");
                    }
                    break;
                case 2: //Delete
                    System.out.println("Enter Your table code want to delete: ");
                    String kw = scanner.nextLine();
                    if (qlban.XoaBan(kw)) {
                        System.out.println("Successed");
                    }
                    break;

                case 3:// xem danh sách bàn trống
                    System.out.println("===Danh sách bàn trống===");
                    ArrayList<Ban> viewBan = new ArrayList<>();
                    qlban.outputview(qlban.xemDSBanTrong(viewBan));
                    break;
            }
        } while (chonBan != 0);

    }

    public static void menuDatBan(QuanLiMon qlmon, QuanLiBan qlban,
            QuanLiDatBan qlDatBan) {
        HoaDon bill = new HoaDon();
        ArrayList<String> addf = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int chonBan, chonMon, soLuong;
        System.out.println("=== Table List===");
        ArrayList<Ban> viewBan = new ArrayList<>();
        qlban.outputview(qlban.xemDSBanTrong(viewBan));
        System.out.println("=== Food List ===");
        qlmon.ViewResultfood();
        int k = 2;
        String mabill, maban, urName;
        System.out.println("Enter IDBill: ");
        mabill = scanner.nextLine();
        System.out.println("Enter MaBan: ");
        maban = scanner.nextLine();
        System.out.println("Enter Your custome name: ");
        urName = scanner.nextLine();
        int a = 0;
        System.out.print("Add Your favorist food(MaMon) in your list: ");
        do {
            a = scanner.nextInt();
        } while (qlmon.CheckFoodList(a));
        System.out.println("it can be out.");

    }

    public static void menuThongKe(QuanLiDatBan qlDatBan) {
        ThongKe thongKe = new ThongKe(qlDatBan);
        Scanner scanner = new Scanner(System.in);
        int luaChon;
        do {
            System.out.println("1. Thống kê theo tháng");
            System.out.println("2. Thống kê theo quý");
            System.out.println("0. Thoát5");
            System.out.print("Chọn: ");
            luaChon = scanner.nextInt();
            switch (luaChon) {
                case 1:
                    thongKe.ThongKeTheoThang();
                    break;
                case 2:
                    thongKe.ThongKeTheoQuy();
                    break;

            }
        } while (luaChon != 0);

    }

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            QuanLiNhanVien qlnv = new QuanLiNhanVien();
            QuanLiMon qlmon = new QuanLiMon();
            QuanLiBan qlban = new QuanLiBan();
            QuanLiDatBan qlDatBan = new QuanLiDatBan(qlban, qlmon);
            ThongKe thongKe = new ThongKe(qlDatBan);
            menu(qlnv, qlmon, qlban, qlDatBan, thongKe);
        } catch (Exception Ex) {
            Ex.printStackTrace();
            Logger.getLogger(MenuChinh.class.getName()).log(Level.SEVERE, null, Ex);
        }
    }
}
