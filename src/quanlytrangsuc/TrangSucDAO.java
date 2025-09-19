/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package quanlytrangsuc;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author CAM PHAM
 */
public interface TrangSucDAO {
    void addDayChuyen(DayChuyen dayChuyen) throws SQLException, InvalidDataException;
    DayChuyen readDayChuyen(String maSP) throws SQLException, InvalidDataException ;
    List<DayChuyen> readAllDayChuyen() throws SQLException, InvalidDataException;
    void updateDayChuyen(DayChuyen dayChuyen) throws SQLException, InvalidDataException;
    void deleteDayChuyen(String maSP) throws SQLException;
    void addNhan(Nhan nhan) throws SQLException, InvalidDataException;
    Nhan readNhan(String maSP) throws SQLException, InvalidDataException;
    List<Nhan> readAllNhan() throws SQLException, InvalidDataException;
    void updateNhan(Nhan nhan) throws SQLException, InvalidDataException;
    void deleteNhan(String maSP)throws SQLException;
    void addVongTay(VongTay vongTay)throws SQLException, InvalidDataException;
    VongTay readVongTay(String maSP)throws SQLException, InvalidDataException;
    List<VongTay> readAllVongTay()throws SQLException, InvalidDataException;
    void updateVongTay(VongTay vongTay)throws SQLException, InvalidDataException;
    void deleteVongTay(String maSP)throws SQLException;
    TrangSuc read(String maSP)throws SQLException, InvalidDataException;
    List<TrangSuc> readAll()throws SQLException, InvalidDataException;
    void update(TrangSuc trangSuc)throws SQLException, InvalidDataException;
    void delete(String maSP)throws SQLException;

    List<TrangSuc> searchByTenSP(String tuKhoa) throws SQLException, InvalidDataException;
    List<TrangSuc> searchByMaSP(String maSP) throws SQLException, InvalidDataException;
    
}
