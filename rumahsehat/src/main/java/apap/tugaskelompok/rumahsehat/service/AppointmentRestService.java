package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRestService {

    AppointmentModel addAppointment(AppointmentModel appointment);
    boolean cek(String idDokter, LocalDateTime time);

    List<AppointmentModel> retrieveListAppointment();

    AppointmentModel retrieveApppointmentByID(String idApt);

    AppointmentModel retrieveAppointmentByTagihan(TagihanModel tagihan);

//    List<AppointmentModel> findByPasien(PasienModel pasien);

    List<AppointmentModel> getListAppointmentByUsername(String username);
}
