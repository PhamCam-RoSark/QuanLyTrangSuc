package quanlytrangsuc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrangSucDAOImpl implements TrangSucDAO{
    private final Connection connection;

    public TrangSucDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // CRUD methods for DayChuyen
    @Override
    public void addDayChuyen(DayChuyen dayChuyen) throws SQLException, InvalidDataException {
        String sql = "INSERT INTO DayChuyen (maSP, tenSP, trongLuong, donGia, chieuDai, kieuDang, vatRate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dayChuyen.getMaSP());
            stmt.setString(2, dayChuyen.getTenSP());
            stmt.setDouble(3, dayChuyen.getTrongLuong());
            stmt.setDouble(4, dayChuyen.getDonGia());
            stmt.setDouble(5, dayChuyen.getChieuDai());
            stmt.setString(6, dayChuyen.getKieuDang());
            stmt.setDouble(7, 0.10); // VAT_RATE for DayChuyen
            stmt.executeUpdate();
        }
    }

    @Override
    public DayChuyen readDayChuyen(String maSP) throws SQLException, InvalidDataException {
        String sql = "SELECT * FROM DayChuyen WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DayChuyen(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getDouble("chieuDai"),
                        rs.getString("kieuDang")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<DayChuyen> readAllDayChuyen() throws SQLException, InvalidDataException {
        List<DayChuyen> list = new ArrayList<>();
        String sql = "SELECT * FROM DayChuyen";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new DayChuyen(
                    rs.getString("maSP"),
                    rs.getString("tenSP"),
                    rs.getDouble("trongLuong"),
                    rs.getDouble("donGia"),
                    rs.getDouble("chieuDai"),
                    rs.getString("kieuDang")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateDayChuyen(DayChuyen dayChuyen) throws SQLException, InvalidDataException {
        String sql = "UPDATE DayChuyen SET tenSP = ?, trongLuong = ?, donGia = ?, chieuDai = ?, kieuDang = ?, vatRate = ? WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dayChuyen.getTenSP());
            stmt.setDouble(2, dayChuyen.getTrongLuong());
            stmt.setDouble(3, dayChuyen.getDonGia());
            stmt.setDouble(4, dayChuyen.getChieuDai());
            stmt.setString(5, dayChuyen.getKieuDang());
            stmt.setDouble(6, 0.10);
            stmt.setString(7, dayChuyen.getMaSP());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteDayChuyen(String maSP) throws SQLException {
        String sql = "DELETE FROM DayChuyen WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            stmt.executeUpdate();
        }
    }

    // CRUD methods for Nhan
    @Override
    public void addNhan(Nhan nhan) throws SQLException, InvalidDataException {
        String sql = "INSERT INTO Nhan (maSP, tenSP, trongLuong, donGia, kichCo, chatLieu, vatRate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nhan.getMaSP());
            stmt.setString(2, nhan.getTenSP());
            stmt.setDouble(3, nhan.getTrongLuong());
            stmt.setDouble(4, nhan.getDonGia());
            stmt.setString(5, nhan.getKichCo());
            stmt.setString(6, nhan.getChatLieu());
            stmt.setDouble(7, 0.05); // VAT_RATE for Nhan
            stmt.executeUpdate();
        }
    }

    @Override
    public Nhan readNhan(String maSP) throws SQLException, InvalidDataException {
        String sql = "SELECT * FROM Nhan WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Nhan(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getString("kichCo"),
                        rs.getString("chatLieu")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Nhan> readAllNhan() throws SQLException, InvalidDataException {
        List<Nhan> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Nhan(
                    rs.getString("maSP"),
                    rs.getString("tenSP"),
                    rs.getDouble("trongLuong"),
                    rs.getDouble("donGia"),
                    rs.getString("kichCo"),
                    rs.getString("chatLieu")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateNhan(Nhan nhan) throws SQLException, InvalidDataException {
        String sql = "UPDATE Nhan SET tenSP = ?, trongLuong = ?, donGia = ?, kichCo = ?, chatLieu = ?, vatRate = ? WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nhan.getTenSP());
            stmt.setDouble(2, nhan.getTrongLuong());
            stmt.setDouble(3, nhan.getDonGia());
            stmt.setString(4, nhan.getKichCo());
            stmt.setString(5, nhan.getChatLieu());
            stmt.setDouble(6, 0.05);
            stmt.setString(7, nhan.getMaSP());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteNhan(String maSP) throws SQLException {
        String sql = "DELETE FROM Nhan WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            stmt.executeUpdate();
        }
    }

    // CRUD methods for VongTay
    @Override
    public void addVongTay(VongTay vongTay) throws SQLException, InvalidDataException {
        String sql = "INSERT INTO VongTay (maSP, tenSP, trongLuong, donGia, duongKinh, chatLieu, vatRate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vongTay.getMaSP());
            stmt.setString(2, vongTay.getTenSP());
            stmt.setDouble(3, vongTay.getTrongLuong());
            stmt.setDouble(4, vongTay.getDonGia());
            stmt.setDouble(5, vongTay.getDuongKinh());
            stmt.setString(6, vongTay.getChatLieu());
            stmt.setDouble(7, 0.08); // VAT_RATE for VongTay
            stmt.executeUpdate();
        }
    }

    @Override
    public VongTay readVongTay(String maSP) throws SQLException, InvalidDataException {
        String sql = "SELECT * FROM VongTay WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VongTay(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getDouble("duongKinh"),
                        rs.getString("chatLieu")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<VongTay> readAllVongTay() throws SQLException, InvalidDataException {
        List<VongTay> list = new ArrayList<>();
        String sql = "SELECT * FROM VongTay";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new VongTay(
                    rs.getString("maSP"),
                    rs.getString("tenSP"),
                    rs.getDouble("trongLuong"),
                    rs.getDouble("donGia"),
                    rs.getDouble("duongKinh"),
                    rs.getString("chatLieu")
                ));
            }
        }
        return list;
    }

    @Override
    public void updateVongTay(VongTay vongTay) throws SQLException, InvalidDataException {
        String sql = "UPDATE VongTay SET tenSP = ?, trongLuong = ?, donGia = ?, duongKinh = ?, chatLieu = ?, vatRate = ? WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vongTay.getTenSP());
            stmt.setDouble(2, vongTay.getTrongLuong());
            stmt.setDouble(3, vongTay.getDonGia());
            stmt.setDouble(4, vongTay.getDuongKinh());
            stmt.setString(5, vongTay.getChatLieu());
            stmt.setDouble(6, 0.08);
            stmt.setString(7, vongTay.getMaSP());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteVongTay(String maSP) throws SQLException {
        String sql = "DELETE FROM VongTay WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            stmt.executeUpdate();
        }
    }

    // Generic CRUD methods for all TrangSuc types
    @Override
    public TrangSuc read(String maSP) throws SQLException, InvalidDataException {
        DayChuyen dc = readDayChuyen(maSP);
        if (dc != null) return dc;
        Nhan nhan = readNhan(maSP);
        if (nhan != null) return nhan;
        return readVongTay(maSP);
    }

    @Override
    public List<TrangSuc> readAll() throws SQLException, InvalidDataException {
        List<TrangSuc> list = new ArrayList<>();
        list.addAll(readAllDayChuyen());
        list.addAll(readAllNhan());
        list.addAll(readAllVongTay());
        return list;
    }

    @Override
    public void update(TrangSuc trangSuc) throws SQLException, InvalidDataException {
        switch (trangSuc) {
            case DayChuyen dayChuyen -> updateDayChuyen(dayChuyen);
            case Nhan nhan -> updateNhan(nhan);
            case VongTay vongTay -> updateVongTay(vongTay);
            default -> throw new InvalidDataException("Loai trang suc khong hop le");
        }
    }

    @Override
    public void delete(String maSP) throws SQLException {
        deleteDayChuyen(maSP);
        deleteNhan(maSP);
        deleteVongTay(maSP); // Attempts deletion in all tables; no error if not found
    }

    @Override
    public List<TrangSuc> searchByTenSP(String tuKhoa) throws SQLException, InvalidDataException {
        List<TrangSuc> result = new ArrayList<>();
        String keyword = "%" + tuKhoa + "%";

        // Search DayChuyen
        String sqlDayChuyen = "SELECT * FROM DayChuyen WHERE tenSP LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlDayChuyen)) {
            stmt.setString(1, keyword);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new DayChuyen(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getDouble("chieuDai"),
                        rs.getString("kieuDang")
                    ));
                }
            }
        }

        // Search Nhan
        String sqlNhan = "SELECT * FROM Nhan WHERE tenSP LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlNhan)) {
            stmt.setString(1, keyword);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new Nhan(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getString("kichCo"),
                        rs.getString("chatLieu")
                    ));
                }
            }
        }

        // Search VongTay
        String sqlVongTay = "SELECT * FROM VongTay WHERE tenSP LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlVongTay)) {
            stmt.setString(1, keyword);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new VongTay(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getDouble("duongKinh"),
                        rs.getString("chatLieu")
                    ));
                }
            }
        }

        return result;
    }

    public List<TrangSuc> searchByMaSP(String maSP) throws SQLException, InvalidDataException {
        List<TrangSuc> result = new ArrayList<>();
        
        // Search DayChuyen
        String sqlDayChuyen = "SELECT * FROM DayChuyen WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlDayChuyen)) {
            stmt.setString(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result.add(new DayChuyen(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getDouble("chieuDai"),
                        rs.getString("kieuDang")
                    ));
                }
            }
        }

        // Search Nhan
        String sqlNhan = "SELECT * FROM Nhan WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlNhan)) {
            stmt.setString(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result.add(new Nhan(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getString("kichCo"),
                        rs.getString("chatLieu")
                    ));
                }
            }
        }

        // Search VongTay
        String sqlVongTay = "SELECT * FROM VongTay WHERE maSP = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlVongTay)) {
            stmt.setString(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result.add(new VongTay(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("trongLuong"),
                        rs.getDouble("donGia"),
                        rs.getDouble("duongKinh"),
                        rs.getString("chatLieu")
                    ));
                }
            }
        }

        return result;
    }
}