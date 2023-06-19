package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService{
    @Autowired
    private PasienDb pasienDb;

    @Override
    public List<PasienModel> getListPasien(){
        return pasienDb.findAll();
    }

    @Override
    public PasienModel getPasienByUsername(String username) {
        return pasienDb.findByUsername(username).get();
    }
}
