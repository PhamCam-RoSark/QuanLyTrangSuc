/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlytrangsuc;

/**
 *
 * @author CAM PHAM
 */
public class DayChuyen extends TrangSuc {
    private double chieuDai;
    private String kieuDang;
    private static final double VAT_RATE = 0.10;

    public DayChuyen(String maSP, String tenSP, double trongLuong, double donGia,
                     double chieuDai, String kieuDang) throws InvalidDataException {
        super(maSP, tenSP, trongLuong, donGia);
        if (chieuDai <= 0) throw new InvalidDataException("Chieu dai phai > 0");
        if (kieuDang == null || kieuDang.trim().isEmpty()) {
            throw new InvalidDataException("Kieu dang khong duoc rong");
        }
        this.chieuDai = chieuDai;
        this.kieuDang = kieuDang;
    }

    public double getChieuDai() {
        return chieuDai;
    }

    public void setChieuDai(double chieuDai) {
        this.chieuDai = chieuDai;
    }

    public String getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(String kieuDang) {
        this.kieuDang = kieuDang;
    }

    @Override
    public double tinhVAT() {
        return getTrongLuong() * getDonGia() * VAT_RATE;
    }

    @Override
    public String toString() {
        return "DayChuyen{ " + super.toString() +
                ", ChieuDai=" + chieuDai +
                ", KieuDang=" + kieuDang + " }";
    }
}

