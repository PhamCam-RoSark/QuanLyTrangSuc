package quanlytrangsuc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;

public class TrangSucForm1 extends JFrame {
    private JComboBox<String> loaiCombo;
    private JTextField maField, tenField, trongLuongField, donGiaField;
    private JTextField chieuDaiField, kieuDangField, kichCoField, chatLieuField, duongKinhField;
    private JTable bang;
    private DefaultTableModel bangModel;
    private JPanel panelLoai;

    private TrangSucDAO dao;

    public TrangSucForm1(Connection conn) {
        // Thiết lập cửa sổ
        setTitle("Quản Lý Trang Sức");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        dao = new TrangSucDAOImpl(conn);

        // Panel nhập liệu
        JPanel panelNhap = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Loại sản phẩm
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelNhap.add(new JLabel("Loại:"), gbc);
        loaiCombo = new JComboBox<>(new String[]{"DayChuyen", "Nhan", "VongTay"});
        gbc.gridx = 1;
        panelNhap.add(loaiCombo, gbc);

        // Mã sản phẩm
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelNhap.add(new JLabel("Mã:"), gbc);
        maField = new JTextField(10);
        gbc.gridx = 1;
        panelNhap.add(maField, gbc);

        // Tên sản phẩm
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelNhap.add(new JLabel("Tên:"), gbc);
        tenField = new JTextField(10);
        gbc.gridx = 1;
        panelNhap.add(tenField, gbc);

        // Trọng lượng
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelNhap.add(new JLabel("Trọng Lượng:"), gbc);
        trongLuongField = new JTextField(10);
        gbc.gridx = 1;
        panelNhap.add(trongLuongField, gbc);

        // Đơn giá
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelNhap.add(new JLabel("Đơn Giá:"), gbc);
        donGiaField = new JTextField(10);
        gbc.gridx = 1;
        panelNhap.add(donGiaField, gbc);

        // Panel cho các thuộc tính riêng
        panelLoai = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panelNhap.add(panelLoai, gbc);

        // Khởi tạo các trường nhập liệu
        chieuDaiField = new JTextField(10);
        kieuDangField = new JTextField(10);
        kichCoField = new JTextField(10);
        chatLieuField = new JTextField(10);
        duongKinhField = new JTextField(10);

        capNhatPanelLoai();
        loaiCombo.addActionListener(e -> capNhatPanelLoai());

        // Panel nút
        JPanel panelNut = new JPanel(new FlowLayout());
        JButton themButton = new JButton("Thêm");
        JButton suaButton = new JButton("Sửa");
        JButton xoaButton = new JButton("Xóa");
        JButton hienThiButton = new JButton("Hiển Thị");
        JButton ExitButton = new JButton("Thoát");
        panelNut.add(themButton);
        panelNut.add(suaButton);
        panelNut.add(xoaButton);
        panelNut.add(hienThiButton);
        panelNut.add(ExitButton);

        // Bảng hiển thị
        String[] columns = {"Mã", "Loại", "Tên", "Trọng Lượng", "Đơn Giá",
                "Chiều Dài", "Kiểu Dáng", "Kích Cỡ", "Chất Liệu", "Đường Kính"};
        bangModel = new DefaultTableModel(columns, 0);
        bang = new JTable(bangModel);
        JScrollPane bangScroll = new JScrollPane(bang);

        // Thêm vào frame
        add(panelNhap, BorderLayout.NORTH);
        add(panelNut, BorderLayout.CENTER);
        add(bangScroll, BorderLayout.SOUTH);

        // Sự kiện nút
        themButton.addActionListener(e -> themSanPham());
        suaButton.addActionListener(e -> suaSanPham());
        xoaButton.addActionListener(e -> xoaSanPham());
        hienThiButton.addActionListener(e -> hienThiDanhSach());
        ExitButton.addActionListener(e -> thoatAction());

        // Sự kiện chọn dòng
        bang.getSelectionModel().addListSelectionListener(e -> {
            int row = bang.getSelectedRow();
            if (row != -1) {
                chonDong(row);
            }
        });
    }

    private void capNhatPanelLoai() {
        panelLoai.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String loai = (String) loaiCombo.getSelectedItem();
        if (loai.equals("DayChuyen")) {
            gbc.gridx = 0; gbc.gridy = 0;
            panelLoai.add(new JLabel("Chiều Dài:"), gbc);
            gbc.gridx = 1; panelLoai.add(chieuDaiField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            panelLoai.add(new JLabel("Kiểu Dáng:"), gbc);
            gbc.gridx = 1; panelLoai.add(kieuDangField, gbc);

        } else if (loai.equals("Nhan")) {
            gbc.gridx = 0; gbc.gridy = 0;
            panelLoai.add(new JLabel("Kích Cỡ:"), gbc);
            gbc.gridx = 1; panelLoai.add(kichCoField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            panelLoai.add(new JLabel("Chất Liệu:"), gbc);
            gbc.gridx = 1; panelLoai.add(chatLieuField, gbc);

        } else if (loai.equals("VongTay")) {
            gbc.gridx = 0; gbc.gridy = 0;
            panelLoai.add(new JLabel("Đường Kính:"), gbc);
            gbc.gridx = 1; panelLoai.add(duongKinhField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            panelLoai.add(new JLabel("Chất Liệu:"), gbc);
            gbc.gridx = 1; panelLoai.add(chatLieuField, gbc);
        }

        panelLoai.revalidate();
        panelLoai.repaint();
    }

    private void thoatAction() {
        dispose();
    }
    private void themSanPham() {
        String loai = (String) loaiCombo.getSelectedItem();
        String ma = maField.getText();
        String ten = tenField.getText();
        String tl = trongLuongField.getText();
        String dg = donGiaField.getText();

        if (ma.isEmpty() || ten.isEmpty() || tl.isEmpty() || dg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin!");
            return;
        }

        try {
            double trongLuong = Double.parseDouble(tl);
            double donGia = Double.parseDouble(dg);

            if (loai.equals("DayChuyen")) {
                String cd = chieuDaiField.getText();
                String kd = kieuDangField.getText();
                double chieuDai = Double.parseDouble(cd);
                dao.addDayChuyen(new DayChuyen(ma, ten, trongLuong, donGia, chieuDai, kd));
            } else if (loai.equals("Nhan")) {
                String kc = kichCoField.getText();
                String cl = chatLieuField.getText();
                dao.addNhan(new Nhan(ma, ten, trongLuong, donGia, kc, cl));
            } else if (loai.equals("VongTay")) {
                String dk = duongKinhField.getText();
                String cl = chatLieuField.getText();
                double duongKinh = Double.parseDouble(dk);
                dao.addVongTay(new VongTay(ma, ten, trongLuong, donGia, duongKinh, cl));
            }

            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            xoaForm();
            hienThiDanhSach();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm!");
        }
    }

    private void suaSanPham() {
        int row = bang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để sửa!");
            return;
        }

        String loai = (String) loaiCombo.getSelectedItem();
        String ma = maField.getText();
        String ten = tenField.getText();
        String tl = trongLuongField.getText();
        String dg = donGiaField.getText();

        try {
            double trongLuong = Double.parseDouble(tl);
            double donGia = Double.parseDouble(dg);

            if (loai.equals("DayChuyen")) {
                String cd = chieuDaiField.getText();
                String kd = kieuDangField.getText();
                double chieuDai = Double.parseDouble(cd);
                dao.updateDayChuyen(new DayChuyen(ma, ten, trongLuong, donGia, chieuDai, kd));
            } else if (loai.equals("Nhan")) {
                String kc = kichCoField.getText();
                String cl = chatLieuField.getText();
                dao.updateNhan(new Nhan(ma, ten, trongLuong, donGia, kc, cl));
            } else if (loai.equals("VongTay")) {
                String dk = duongKinhField.getText();
                String cl = chatLieuField.getText();
                double duongKinh = Double.parseDouble(dk);
                dao.updateVongTay(new VongTay(ma, ten, trongLuong, donGia, duongKinh, cl));
            }

            JOptionPane.showMessageDialog(this, "Sửa thành công!");
            xoaForm();
            hienThiDanhSach();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi sửa!");
        }
    }

    private void xoaSanPham() {
        int row = bang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa!");
            return;
        }

        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            String ma = (String) bang.getValueAt(row, 0);
            String loai = (String) bang.getValueAt(row, 1);

            try {
                if (loai.equals("DayChuyen")) {
                    dao.deleteDayChuyen(ma);
                } else if (loai.equals("Nhan")) {
                    dao.deleteNhan(ma);
                } else if (loai.equals("VongTay")) {
                    dao.deleteVongTay(ma);
                }

                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                xoaForm();
                hienThiDanhSach();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi xóa!");
            }
        }
    }

    private void hienThiDanhSach() {
        bangModel.setRowCount(0);

        try {
            for (DayChuyen dc : dao.readAllDayChuyen()) {
                Object[] row = {dc.getMaSP(), "DayChuyen", dc.getTenSP(),
                        dc.getTrongLuong(), dc.getDonGia(),
                        dc.getChieuDai(), dc.getKieuDang(), "", "", ""};
                bangModel.addRow(row);
            }

            for (Nhan nh : dao.readAllNhan()) {
                Object[] row = {nh.getMaSP(), "Nhan", nh.getTenSP(),
                        nh.getTrongLuong(), nh.getDonGia(),
                        "", "", nh.getKichCo(), nh.getChatLieu(), ""};
                bangModel.addRow(row);
            }

            for (VongTay vt : dao.readAllVongTay()) {
                Object[] row = {vt.getMaSP(), "VongTay", vt.getTenSP(),
                        vt.getTrongLuong(), vt.getDonGia(),
                        "", "", "", vt.getChatLieu(), vt.getDuongKinh()};
                bangModel.addRow(row);
            }

            JOptionPane.showMessageDialog(this, "Đã load danh sách!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load danh sách!");
        }
    }

    private void chonDong(int row) {
        String loai = (String) bang.getValueAt(row, 1);
        loaiCombo.setSelectedItem(loai);

        maField.setText((String) bang.getValueAt(row, 0));
        tenField.setText((String) bang.getValueAt(row, 2));
        trongLuongField.setText(String.valueOf(bang.getValueAt(row, 3)));
        donGiaField.setText(String.valueOf(bang.getValueAt(row, 4)));

        chieuDaiField.setText("");
        kieuDangField.setText("");
        kichCoField.setText("");
        chatLieuField.setText("");
        duongKinhField.setText("");

        if (loai.equals("DayChuyen")) {
            chieuDaiField.setText(String.valueOf(bang.getValueAt(row, 5)));
            kieuDangField.setText((String) bang.getValueAt(row, 6));
        } else if (loai.equals("Nhan")) {
            kichCoField.setText((String) bang.getValueAt(row, 7));
            chatLieuField.setText((String) bang.getValueAt(row, 8));
        } else if (loai.equals("VongTay")) {
            duongKinhField.setText(String.valueOf(bang.getValueAt(row, 9)));
            chatLieuField.setText((String) bang.getValueAt(row, 8));
        }
    }

    private void xoaForm() {
        maField.setText("");
        tenField.setText("");
        trongLuongField.setText("");
        donGiaField.setText("");
        chieuDaiField.setText("");
        kieuDangField.setText("");
        kichCoField.setText("");
        chatLieuField.setText("");
        duongKinhField.setText("");
    }

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            new TrangSucForm1(conn).setVisible(true);
        } catch (Exception e) {
            System.out.println("Lỗi kết nối!");
        }
    }
}