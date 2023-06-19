package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.PasienModel;

public interface PasienRestService {
    String encrypt(String password);
    PasienModel registrasiPasien(PasienModel pasienModel);

    PasienModel getProfile(String username);

    PasienModel getProfileByUsername(String username);

    PasienModel topUp(PasienModel pasienModel, Integer nominal);

    PasienModel update(PasienModel tagihan);
}
