package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.projection.DokterIncomeProjection;
import apap.tugaskelompok.rumahsehat.projection.DokterMonthlyIncomeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, Long> {
    Optional<TagihanModel> findByKode(String kode);
    Optional<TagihanModel> findTagihanModelByKode(String kode);

    @Query(value = "SELECT d.nama as dokter, SUM(t.jumlahTagihan) as income " +
            "FROM TagihanModel as t, AppointmentModel as a, DokterModel d " +
            "WHERE t = a.tagihan " +
            "AND a.dokter = d " +
            "AND YEAR(t.tanggalBayar) = YEAR(CURRENT_TIME) " +
            "AND t.isPaid=true " +
            "GROUP BY d.uuid")
    List<DokterIncomeProjection> getIncomeEachDokterThisYear();

    @Query(value = "SELECT d.nama as dokter, MONTH(t.tanggalBayar) as month, SUM(t.jumlahTagihan) as income " +
            "FROM TagihanModel as t, AppointmentModel as a, DokterModel d " +
            "WHERE t.kode = a.tagihan.kode " +
            "AND a.dokter.uuid = d.uuid " +
            "AND YEAR(t.tanggalBayar) = YEAR(CURRENT_TIME) " +
            "AND t.isPaid=true " +
            "GROUP BY d.uuid, MONTH(t.tanggalBayar)")
    List<DokterMonthlyIncomeProjection> getIncomeEachDokterThisYearPerMonth();

    @Query(value = "SELECT d.nama as dokter, MONTH(t.tanggalBayar) as month, SUM(t.jumlahTagihan) as income " +
            "FROM TagihanModel as t, AppointmentModel as a, DokterModel d " +
            "WHERE d.uuid = :uuid " +
            "AND t = a.tagihan " +
            "AND a.dokter = d " +
            "AND YEAR(t.tanggalBayar) = :tahun " +
            "AND t.isPaid=true " +
            "GROUP BY MONTH(t.tanggalBayar)")
    List<DokterMonthlyIncomeProjection> getIncomeByDokterByYear(@Param("uuid") String uuid, @Param("tahun") Integer tahun);

}
