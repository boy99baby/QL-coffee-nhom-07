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

/**
 *
 * @author DELL
 */
public class MenuChinh {

    public static void menu(QuanLiNhanVien qlnv, QuanLiMon qlmon, QuanLiBan qlban,
            QuanLiDatBan qlDatBan, ThongKe ThongKe) throws ParseException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int chon;
        do {
            System.out.println("___DEMO CHƯƠNG TRÌNH QUẢN LÍ QUÁN CAFE___");
            System.out.println("1. Quản lí nhân viên");
            System.out.println("2. Quản lí món");
            System.out.println("3. Quản lí bàn");
            System.out.println("4. Đặt bàn");
            System.out.println("5. Xuất Hóa Đơn");
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
                    break;
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
            System.out.println("5. Tìm kiếm nhân viên.");
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

                    // Update Nhan Vien willbe update late.
                    break;
                case 4:
                    System.out.println("===Danh Sach Nhan Vien===");
                    QuanLiNhanVien ql = new QuanLiNhanVien();
                    System.out.println(ql.viewList());
                    break;
                case 5:
                    QuanLiNhanVien ql1 = new QuanLiNhanVien();
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
                    QuanLiMon qlm = new QuanLiMon();
                    System.out.println(qlm.ViewResultfood());
                    System.err.println("------");
                    System.out.println(qlm.ViewFoodList());
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
                            System.out.println(qlmon.FindWithName1(tenTimKiem));
                            break;
                        case 2:
                            System.out.print("Nhập giá trong khoảng từ: ");
                            int gia1 = scanner.nextInt();
                            System.out.println("Đến: ");
                            int gia2 = scanner.nextInt();
                            System.out.println(qlmon.FindWithPrice(gia1, gia2));
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
                    scanner.nextLine();
                    System.out.println("Enter Your table code want to delete: ");
                    String kw = scanner.nextLine();
                    QuanLiBan qlb = new QuanLiBan();
                    //System.out.println(kw);
                    if (qlb.deleteTable(kw)) {
                        System.out.println("Successed");
                    } else {
                        System.err.println("Wrong input type");
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

        System.out.println("=== Food List ===");
        QuanLiMon qlm = new QuanLiMon();
        System.out.println(qlm.ViewResultfood());
        int k = 2;
        String mabill, maban, urName;
        System.out.println("Enter a new IDBill: ");
        mabill = scanner.nextLine();
        System.out.println("=== Table List===");
        ArrayList<Ban> viewBan = new ArrayList<>();
        qlban.outputview(qlban.xemDSBanTrong(viewBan));
        System.out.println("Enter MaBan: ");
        maban = scanner.nextLine();
        System.out.println("Enter Your custome name: ");
        urName = scanner.nextLine();
        int a = 0;
        if (bill.CreateBill(mabill, urName, maban, 1)) {
            System.out.println("Create your bill Success");
        }
        System.out.print("Add Your favorist food(MaMon) in your list: ");
        do {
            a = scanner.nextInt();
            HoaDon hd = new HoaDon();
            if (hd.AddFoodBill(a, mabill)) {
                System.out.println("Add one food successed.");
            }
        } while (qlmon.CheckFoodList(a));
        System.out.println("it can be out.");
    }

    public static void menuThongKe(QuanLiDatBan qlDatBan) {
        System.out.println("Enter your Bill id: ");
        Scanner sc = new Scanner(System.in);
        String idbill = sc.nextLine();
        HoaDon hd = new HoaDon();
        System.out.println("Your list food u added into your bill: " + hd.ListFoodPay(idbill));
        System.out.println("Your money u must pay: "+ hd.PayMoney(idbill) + " VND");
    }

    public String taaa() throws ClassNotFoundException {
        try {
            QuanLiNhanVien qlnv = new QuanLiNhanVien();
            QuanLiMon qlmon = new QuanLiMon();
            QuanLiBan qlban = new QuanLiBan();
            QuanLiDatBan qlDatBan = new QuanLiDatBan(qlban, qlmon);
            ThongKe thongKe = new ThongKe(qlDatBan);
            menu(qlnv, qlmon, qlban, qlDatBan, thongKe);
        } catch (ParseException ex) {
            Logger.getLogger(MenuChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
