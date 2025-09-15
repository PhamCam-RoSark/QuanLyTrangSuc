/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlytrangsuc;

/**
 *
 * @author CAM PHAM
 */
public class VongTay extends TrangSuc {
    private double duongKinh;
    private String chatLieu;
    private static final double VAT_RATE = 0.08;

    public VongTay(String maSP, String tenSP, double trongLuong, double donGia,
                   double duongKinh, String chatLieu) throws InvalidDataException {
        super(maSP, tenSP, trongLuong, donGia);
        if (duongKinh <= 0) throw new InvalidDataException("Duong kinh phai > 0");
        if (chatLieu == null || chatLieu.trim().isEmpty()) {
            throw new InvalidDataException("Chat lieu khong duoc rong");
        }
        this.duongKinh = duongKinh;
        this.chatLieu = chatLieu;
    }

    public double getDuongKinh() {
        return duongKinh;
    }

    public void setDuongKinh(double duongKinh) {
        this.duongKinh = duongKinh;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    @Override
    public double tinhVAT() {
        return getTrongLuong() * getDonGia() * VAT_RATE;
    }

    @Override
    public String toString() {
        return "VongTay{ " + super.toString() +
                ", DuongKinh=" + duongKinh +
                ", ChatLieu=" + chatLieu + " }";
    }
}

