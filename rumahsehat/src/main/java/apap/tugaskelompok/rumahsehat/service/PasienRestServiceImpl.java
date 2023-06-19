package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService {

    @Autowired
    private PasienDb pasienDb;

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    @Override
    public PasienModel registrasiPasien(PasienModel pasienModel) {
        pasienModel.setPassword(encrypt(pasienModel.getPassword()));
        return pasienDb.save(pasienModel);
    }
    
    @Override
    public PasienModel getProfile(String username) {
        Optional<PasienModel> pasien = pasienDb.findByUsername(username);
        if(pasien.isPresent()) {
            return pasien.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public PasienModel getProfileByUsername(String username) {
        Optional<PasienModel> pasien = pasienDb.findPasienModelByUsername(username);
        if(pasien.isPresent()) {
            return pasien.get();
        } else {
            throw new NoSuchElementException();
        }
    }


    @Override
    public PasienModel topUp(PasienModel pasienModel, Integer nominal){
        pasienModel.setSaldo(pasienModel.getSaldo() + nominal);
        return pasienDb.save(pasienModel);
    }

    @Override
    public PasienModel update(PasienModel tagihan){
        return pasienDb.save(tagihan);
    }
}
