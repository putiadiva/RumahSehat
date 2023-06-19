package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.ResepModel;
import apap.tugaskelompok.rumahsehat.repository.AppointmentDb;
import apap.tugaskelompok.rumahsehat.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ResepRestServiceImpl implements ResepRestService{

    @Autowired
    ResepDb resepDb;
    @Override
    public ResepModel retrieveResepByID(Long idResep){
        Optional<ResepModel> resep = resepDb.findById(idResep);
        if (resep.isPresent()){
            return resep.get();
        }else return null;
    }
}
