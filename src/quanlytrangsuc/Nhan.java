/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlytrangsuc;

/**
 *
 * @author CAM PHAM
 */
public class Nhan extends TrangSuc {
    private String kichCo;
    private String chatLieu;
    private static final double VAT_RATE = 0.05;

    public Nhan(String maSP, String tenSP, double trongLuong, double donGia,
                String kichCo, String chatLieu) throws InvalidDataException {
        super(maSP, tenSP, trongLuong, donGia);
        if (kichCo == null || kichCo.trim().isEmpty()) {
            throw new InvalidDataException("Kich co khong duoc rong");
        }
        if (chatLieu == null || chatLieu.trim().isEmpty()) {
            throw new InvalidDataException("Chat lieu khong duoc rong");
        }
        this.kichCo = kichCo;
        this.chatLieu = chatLieu;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
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
        return "Nhan{ " + super.toString() +
                ", KichCo=" + kichCo +
                ", ChatLieu=" + chatLieu + " }";
    }
}

    