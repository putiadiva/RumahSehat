package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.projection.DokterIncomeProjection;
import apap.tugaskelompok.rumahsehat.projection.DokterMonthlyIncomeProjection;
import apap.tugaskelompok.rumahsehat.repository.DokterDb;
import apap.tugaskelompok.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DokterServiceImpl implements DokterService{

    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private TagihanDb tagihanDb;

    @Override
    public DokterModel addDokter(DokterModel dokter) {
        String pass = encrypt(dokter.getPassword());
        dokter.setPassword(pass);
        return dokterDb.save(dokter);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
    @Override
    public List<DokterModel> getListDokter(){
        return dokterDb.findAll();
    }

    @Override
    public DokterModel getDokterById(String id) {
        Optional<DokterModel> dokter = dokterDb.findById(id);
        if (dokter.isPresent()) {
            return dokter.get();
        } else
            return null;
    }

    @Override
    public boolean checkIfTarifInt(String tarif){
        if (tarif == null) {
            return false;
        }
        int length = tarif.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (tarif.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = tarif.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<DokterIncomeProjection> getTagihanEachDokterThisYear() {
        return tagihanDb.getIncomeEachDokterThisYear();
    }

    @Override
    public List<DokterMonthlyIncomeProjection> getIncomeEachDokterThisYearPerMonth() {
        return tagihanDb.getIncomeEachDokterThisYearPerMonth();
    }

    @Override
    public Optional<DokterModel> getDokterByUuid(String uuid) {
        return dokterDb.findDokterModelByUuid(uuid);
    }

    @Override
    public List<DokterMonthlyIncomeProjection> getIncomeByDokterByYear(DokterModel dokter, Integer tahun) {
        return tagihanDb.getIncomeByDokterByYear(dokter.getUuid(), tahun);
    }
}
