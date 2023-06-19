package apap.tugaskelompok.rumahsehat.restcontroller;

import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.service.PasienRestService;
import apap.tugaskelompok.rumahsehat.service.TagihanRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/tagihan")
public class TagihanRestController {
    @Autowired
    private TagihanRestService tagihanRestService;

    @Autowired
    private PasienRestService pasienRestService;

    @GetMapping(value = "/list-tagihan/{username}")
    private List<TagihanModel> retrieveListTagihan(@PathVariable("username") String username){
        log.info("API mengambil list tagihan pasien");
        return tagihanRestService.retrieveListTagihan(username);
    }

    @GetMapping(value = "/list-tagihan")
    private List<TagihanModel> retrieveListAllTagihan(){
        log.info("API mengambil seluruh list tagihan");
        return tagihanRestService.retrieveAllListTagihan();
    }

    @PutMapping(value = "/{kode}")
    private TagihanModel updateTagihanPaid(@PathVariable("kode") String kode){
        TagihanModel tagihan = tagihanRestService.getTagihanByKode(kode);
        tagihan.setIsPaid(true);
        tagihan.setTanggalBayar(LocalDate.now());
        tagihanRestService.update(tagihan);

        PasienModel pasien = tagihan.getAppointment().getPasien();
        pasien.setSaldo(pasien.getSaldo() - tagihan.getJumlahTagihan());
        pasienRestService.update(pasien);

        log.info("Tagihan berhasil dibayar");

        return tagihan;
    }

}