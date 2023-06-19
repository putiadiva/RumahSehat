package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.ApotekerModel;
import apap.tugaskelompok.rumahsehat.repository.ApotekerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class ApotekerServiceImpl implements ApotekerService{

    @Autowired
    private ApotekerDb apotekerDb;

    @Override
    public ApotekerModel addApoteker(ApotekerModel apoteker) {
        String pass = encrypt(apoteker.getPassword());
        apoteker.setPassword(pass);
        return apotekerDb.save(apoteker);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }


    @Override
    public List<ApotekerModel> getListApoteker(){
        return apotekerDb.findAll();
    }

    @Override
    public ApotekerModel getApotekerByUsername(String idAptk){
        Optional<ApotekerModel> aptk = apotekerDb.findByUsername(idAptk);
        if (aptk.isPresent()){
            return aptk.get();
        }else return null;
    }

}
