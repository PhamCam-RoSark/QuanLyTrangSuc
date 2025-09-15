package quanlytrangsuc;

// Lop cha abstract cho tat ca loai trang suc
public abstract class TrangSuc {
    private final String maSP;
    private String tenSP;
    private double trongLuong;
    private double donGia;

    public TrangSuc(String maSP, String tenSP, double trongLuong, double donGia) throws InvalidDataException {
        if (maSP == null || maSP.trim().isEmpty()) {
            throw new InvalidDataException("Ma san pham khong duoc rong");
        }
        if (tenSP == null || tenSP.trim().isEmpty()) {
            throw new InvalidDataException("Ten san pham khong duoc rong");
        }
        if (trongLuong <= 0) {
            throw new InvalidDataException("Trong luong phai > 0");
        }
        if (donGia <= 0) {
            throw new InvalidDataException("Don gia phai > 0");
        }
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.trongLuong = trongLuong;
        this.donGia = donGia;
    }

    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) throws InvalidDataException {
        if (tenSP == null || tenSP.trim().isEmpty()) {
            throw new InvalidDataException("Ten khong hop le");
        }
        this.tenSP = tenSP;
    }

    public double getTrongLuong() { return trongLuong; }
    public void setTrongLuong(double trongLuong) throws InvalidDataException {
        if (trongLuong <= 0) throw new InvalidDataException("Trong luong phai > 0");
        this.trongLuong = trongLuong;
    }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) throws InvalidDataException {
        if (donGia <= 0) throw new InvalidDataException("Don gia phai > 0");
        this.donGia = donGia;
    }

    // Tinh VAT (ghi de o lop con)
    public abstract double tinhVAT();

    // Tinh thanh tien
    public double tinhThanhTien() {
        return trongLuong * donGia + tinhVAT();
    }

    @Override
    public String toString() {
        return "MaSP=" + maSP +
               ", Ten=" + tenSP +
               ", TL=" + trongLuong +
               ", DonGia=" + donGia +
               ", VAT=" + tinhVAT() +
               ", ThanhTien=" + tinhThanhTien();
    }
}
