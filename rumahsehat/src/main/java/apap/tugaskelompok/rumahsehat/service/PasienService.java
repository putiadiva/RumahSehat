package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.PasienModel;

import java.util.List;

public interface PasienService {

    List<PasienModel> getListPasien();

    PasienModel getPasienByUsername(String username);
}
