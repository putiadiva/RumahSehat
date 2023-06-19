package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.DokterModel;

import java.util.Optional;

public interface DokterChartService {

    Optional<DokterModel> getDataForDefaultView();
}
