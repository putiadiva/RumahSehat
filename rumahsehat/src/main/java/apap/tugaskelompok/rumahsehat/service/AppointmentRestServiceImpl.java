package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.model.ResepModel;
import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.repository.AppointmentDb;
import apap.tugaskelompok.rumahsehat.repository.TagihanDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService {

    @Autowired
    AppointmentDb appointmentDb;

    //logic untuk range waktu
    //if(new.waktuMulai < old.waktuMulai && new.waktuAkhir > old.waktuMulai) || new.waktuMulai < old.waktuAkhir && new.waktuAkhir > old.waktuAkhir)
    //return berupa boolean, bool tolak atau lanjut masukkan appointment ke model nya
    //new adalah waktu appointment yang baru (yang akan dibuat)
    //old adalawh waktu appointment yang sudah ada pada si dokter.
    //presumabbly algorithm ini dilakukan di dalam looping ke list appointment si dokter tersebut

    @Override
    public AppointmentModel addAppointment(AppointmentModel appointment) {
       return appointmentDb.save(appointment);
    }

    public boolean cek(String idDokter, LocalDateTime time) {
        List<AppointmentModel> listAppointment = appointmentDb.findAll();
        for (AppointmentModel a: listAppointment) {
            if (a.getDokter().getUuid().equals(idDokter)) {
                if (time.isEqual(a.getWaktuAwal()) || time.isAfter(a.getWaktuAwal()) && time.isBefore(a.getWaktuAkhir(a.getWaktuAwal()))
                        || time.isBefore(a.getWaktuAwal()) && time.isAfter(a.getWaktuAwal().minusHours(1))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<AppointmentModel> getListAppointmentByUsername(String username) {
        return appointmentDb.findAllByPasienUsername(username);
    }

    @Override
    public List<AppointmentModel> retrieveListAppointment(){
        return appointmentDb.findAll();
    }

    @Override
    public AppointmentModel retrieveApppointmentByID(String idApt){
        Optional<AppointmentModel> apt = appointmentDb.findById(idApt);
        if (apt.isPresent()){
            return apt.get();
        }else return null;
    }

//    @Override
//    public List<AppointmentModel> findByPasien(PasienModel pasien) {
//        return appointmentDb.findAllByPasien(pasien);
//    }

    @Override
    public AppointmentModel retrieveAppointmentByTagihan(TagihanModel tagihan){
        System.out.println(appointmentDb.findAppointmentModelByTagihan(tagihan));
        return appointmentDb.findAppointmentModelByTagihan(tagihan);
    }
}
