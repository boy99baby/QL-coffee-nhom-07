/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatBanTestCase;

import QuanLyBan.QuanLiBan;
import QuanLyMon.Mon;
import QuanLyMon.QuanLiMon;
import QuanLyNhanVien.NhanVien;
import QuanLyNhanVien.QuanLiNhanVien;
import java.text.ParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thang
 */
public class Tesster {

    @Test
    public void tesDeleteNV_Str() {
        int a = 199;
        QuanLiNhanVien sv = new QuanLiNhanVien();
        boolean kq = sv.xoaNhanVien(a);
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void TestDeleteNV() {
        int a = 99;
        QuanLiNhanVien sv = new QuanLiNhanVien();
        boolean kq = sv.xoaNhanVien(a);
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_01() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.FindWithName("Thang");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_02() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.FindWithName(" ");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_03() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.FindWithName("1");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_04() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.FindWithName("--");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_05() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.Findwithid("2");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_06() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.Findwithid("--");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_07() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.Findwithid("thhhhhhhhhhhhhhhhh");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void testfind_08() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.Findwithid(" ");
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }

    @Test
    public void TestFIND_01() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.findwithHomeTown("");
        boolean asn = true;
        Assert.assertEquals(asn, kq);
    }

    @Test
    public void TestFIND_02() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.findwithHomeTown("--");
        boolean asn = true;
        Assert.assertEquals(asn, kq);
    }

    @Test
    public void TestFIND_03() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.findwithHomeTown("ls");
        boolean asn = true;
        Assert.assertEquals(asn, kq);
    }

    @Test
    public void TestFIND_04() {
        QuanLiNhanVien ql = new QuanLiNhanVien();
        boolean kq = ql.findwithHomeTown("HCM");
        boolean asn = true;
        Assert.assertEquals(asn, kq);
    }

    @Test
    public void ADD_DEL() {
        QuanLiMon qlm = new QuanLiMon();
        Mon mon = new Mon(5, "--", 20, "70", "today");
        boolean kq = qlm.themMon(mon);
        boolean ans = true;
        Assert.assertEquals(ans, kq);
    }

    @Test
    public void ADD_DEL_1() {
        QuanLiMon qlm = new QuanLiMon();
        Mon mon = new Mon(5, "keocaosu", 20, "70", "today");
        boolean kq = qlm.themMon(mon);
        boolean ans = true;
        Assert.assertEquals(ans, kq);
    }

    @Test
    public void delte_Food() {
        QuanLiMon qlm = new QuanLiMon();
        boolean kq = qlm.xoaMon(99);
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }
    
    @Test
    public void delte_Food_1() {
        QuanLiMon qlm = new QuanLiMon();
        boolean kq = qlm.xoaMon(-1);
        boolean anw = true;
        Assert.assertEquals(anw, kq);
    }
    
    @Test
    public void TestFindName(){
        QuanLiBan qlb = new  QuanLiBan();
        boolean a = qlb.deleteTable(" ");
        boolean b = true;
        Assert.assertEquals(b, a);
    } 

}
