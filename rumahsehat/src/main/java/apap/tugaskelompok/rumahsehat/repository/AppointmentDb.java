package apap.tugaskelompok.rumahsehat.repository;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {
    Optional<AppointmentModel> findByKode(String idApt);

    List<AppointmentModel> findAllByDokter(DokterModel dokter);
    List<AppointmentModel> findAllByPasien(PasienModel pasien);

    @Query("SELECT a FROM AppointmentModel a WHERE a.pasien.username = :username")
    List<AppointmentModel> findAllByPasienUsername(@Param("username") String username);


    AppointmentModel findAppointmentModelByTagihan(TagihanModel tagihan);
}
