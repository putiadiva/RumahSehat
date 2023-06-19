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

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentDb appointmentDb;

    @Override
    public AppointmentModel getApppointmentByID(String idApt){
        Optional<AppointmentModel> apt = appointmentDb.findByKode(idApt);
        if (apt.isPresent()){
            return apt.get();
        }else return null;
    }

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }

    @Override
    public List<AppointmentModel> getListAppointmentByDokter(DokterModel dokter){
        return appointmentDb.findAllByDokter(dokter);
    }

    @Override
    public List<AppointmentModel> getListAppointmentByPasien(PasienModel pasien) {
        return appointmentDb.findAllByPasien(pasien);
    }

}
