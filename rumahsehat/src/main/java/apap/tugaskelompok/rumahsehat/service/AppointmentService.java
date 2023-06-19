package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface AppointmentService {

    AppointmentModel getApppointmentByID(String idApt);

    List<AppointmentModel> getListAppointment() ;
    List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter);
    List<AppointmentModel> getListAppointmentByPasien(PasienModel pasien);


}
