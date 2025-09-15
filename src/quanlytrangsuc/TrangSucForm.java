package quanlytrangsuc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;

public class TrangSucForm extends JFrame {
    private JComboBox<String> loaiSPCombo;
    private JTextField maSPField, tenSPField, trongLuongField, donGiaField;
    private JTextField chieuDaiField, kieuDangField, kichCoField, chatLieuField, duongKinhField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel typeSpecificPanel;

    private TrangSucDAO dao;

    public TrangSucForm(Connection conn) {
        setTitle("Quản Lý Trang Sức");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        dao = new TrangSucDAO(conn);

        // ==== PANEL INPUT ====
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Loại sản phẩm
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Loại SP:"), gbc);
        loaiSPCombo = new JComboBox<>(new String[]{"DayChuyen", "Nhan", "VongTay"});
        gbc.gridx = 1;
        inputPanel.add(loaiSPCombo, gbc);

        // Mã SP
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Mã SP:"), gbc);
        maSPField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(maSPField, gbc);

        // Tên SP
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Tên SP:"), gbc);
        tenSPField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(tenSPField, gbc);

        // Trọng lượng
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Trọng Lượng:"), gbc);
        trongLuongField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(trongLuongField, gbc);

        // Đơn giá
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Đơn Giá:"), gbc);
        donGiaField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(donGiaField, gbc);

        // Panel riêng cho từng loại
        typeSpecificPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        inputPanel.add(typeSpecificPanel, gbc);

        // Các textfield loại riêng
        chieuDaiField = new JTextField(15);
        kieuDangField = new JTextField(15);
        kichCoField = new JTextField(15);
        chatLieuField = new JTextField(15);
        duongKinhField = new JTextField(15);

        updateTypeSpecificFields();
        loaiSPCombo.addActionListener(e -> updateTypeSpecificFields());

        // ==== NÚT THÊM + HIỂN THỊ ====
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Thêm");
        JButton showButton = new JButton("Hiển thị danh sách");
        buttonPanel.add(addButton);
        buttonPanel.add(showButton);

        // ==== BẢNG ====
        String[] columnNames = {"Mã SP", "Loại SP", "Tên SP", "Trọng lượng", "Đơn giá",
                "Chiều dài", "Kiểu dáng", "Kích cỡ", "Chất liệu", "Đường kính"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);

        // Add vào frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(tableScroll, BorderLayout.SOUTH);

        // ==== XỬ LÝ NÚT THÊM ====
        addButton.addActionListener(e -> handleAdd());

        // ==== XỬ LÝ NÚT HIỂN THỊ ====
        showButton.addActionListener(e -> loadData());
    }

    private void updateTypeSpecificFields() {
        typeSpecificPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String loai = (String) loaiSPCombo.getSelectedItem();
        if ("DayChuyen".equals(loai)) {
            gbc.gridx = 0; gbc.gridy = 0;
            typeSpecificPanel.add(new JLabel("Chiều dài:"), gbc);
            gbc.gridx = 1; typeSpecificPanel.add(chieuDaiField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            typeSpecificPanel.add(new JLabel("Kiểu dáng:"), gbc);
            gbc.gridx = 1; typeSpecificPanel.add(kieuDangField, gbc);

        } else if ("Nhan".equals(loai)) {
            gbc.gridx = 0; gbc.gridy = 0;
            typeSpecificPanel.add(new JLabel("Kích cỡ:"), gbc);
            gbc.gridx = 1; typeSpecificPanel.add(kichCoField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            typeSpecificPanel.add(new JLabel("Chất liệu:"), gbc);
            gbc.gridx = 1; typeSpecificPanel.add(chatLieuField, gbc);

        } else if ("VongTay".equals(loai)) {
            gbc.gridx = 0; gbc.gridy = 0;
            typeSpecificPanel.add(new JLabel("Đường kính:"), gbc);
            gbc.gridx = 1; typeSpecificPanel.add(duongKinhField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            typeSpecificPanel.add(new JLabel("Chất liệu:"), gbc);
            gbc.gridx = 1; typeSpecificPanel.add(chatLieuField, gbc);
        }

        typeSpecificPanel.revalidate();
        typeSpecificPanel.repaint();
    }

    private void handleAdd() {
        try {
            String loai = (String) loaiSPCombo.getSelectedItem();
            String ma = maSPField.getText();
            String ten = tenSPField.getText();
            double tl = Double.parseDouble(trongLuongField.getText());
            double dg = Double.parseDouble(donGiaField.getText());

            if ("DayChuyen".equals(loai)) {
                double cd = Double.parseDouble(chieuDaiField.getText());
                String kd = kieuDangField.getText();
                dao.addDayChuyen(new DayChuyen(ma, ten, tl, dg, cd, kd));
            } else if ("Nhan".equals(loai)) {
                String kc = kichCoField.getText();
                String cl = chatLieuField.getText();
                dao.addNhan(new Nhan(ma, ten, tl, dg, kc, cl));
            } else if ("VongTay".equals(loai)) {
                double dk = Double.parseDouble(duongKinhField.getText());
                String cl = chatLieuField.getText();
                dao.addVongTay(new VongTay(ma, ten, tl, dg, dk, cl));
            }

            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void loadData() {
        try {
            tableModel.setRowCount(0);

            for (DayChuyen dc : dao.readAllDayChuyen()) {
                Object[] row = {dc.getMaSP(), "DayChuyen", dc.getTenSP(),
                        dc.getTrongLuong(), dc.getDonGia(),
                        dc.getChieuDai(), dc.getKieuDang(), "", "", ""};
                tableModel.addRow(row);
            }

            for (Nhan nh : dao.readAllNhan()) {
                Object[] row = {nh.getMaSP(), "Nhan", nh.getTenSP(),
                        nh.getTrongLuong(), nh.getDonGia(),
                        "", "", nh.getKichCo(), nh.getChatLieu(), ""};
                tableModel.addRow(row);
            }

            for (VongTay vt : dao.readAllVongTay()) {
                Object[] row = {vt.getMaSP(), "VongTay", vt.getTenSP(),
                        vt.getTrongLuong(), vt.getDonGia(),
                        "", "", "", vt.getChatLieu(), vt.getDuongKinh()};
                tableModel.addRow(row);
            }

            JOptionPane.showMessageDialog(this, "Đã tải danh sách!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        maSPField.setText("");
        tenSPField.setText("");
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
            SwingUtilities.invokeLater(() -> new TrangSucForm(conn).setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
