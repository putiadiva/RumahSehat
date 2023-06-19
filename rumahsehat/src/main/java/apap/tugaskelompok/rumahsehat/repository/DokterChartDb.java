package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DokterChartDb extends JpaRepository<TagihanModel, String> {

    @Query(value = "SELECT tagihan" +
            "FROM appointment,tagihan" +
            "WHERE is_paid=true" +
            "GROUP BY uuid_dokter", nativeQuery = true)
    Optional<DokterModel> defaultViewData();
}
