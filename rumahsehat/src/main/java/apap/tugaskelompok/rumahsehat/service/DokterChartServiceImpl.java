package apap.tugaskelompok.rumahsehat.service;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.repository.DokterChartDb;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DokterChartServiceImpl implements DokterChartService {

    @Autowired
    DokterChartDb dokterChartDb;

    @Override
    public Optional<DokterModel> getDataForDefaultView() {
        return dokterChartDb.defaultViewData();
    }
}
