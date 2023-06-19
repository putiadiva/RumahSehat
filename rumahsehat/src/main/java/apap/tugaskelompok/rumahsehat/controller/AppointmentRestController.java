package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.service.AppointmentRestService;
import apap.tugaskelompok.rumahsehat.service.DokterService;
import apap.tugaskelompok.rumahsehat.service.PasienService;
import lombok.extern.slf4j.Slf4j;
import apap.tugaskelompok.rumahsehat.service.TagihanRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    private PasienService pasienService;

    @Autowired
    private DokterService dokterService;


    @Autowired
    private TagihanRestService tagihanRestService;

    @PostMapping("/appointment/add")
    private Map<String, String> addAppointment(@RequestHeader("Authorization") String token, @Valid @RequestBody AppointmentModel appointment, BindingResult bindingResult) {
      Map<String, String> decodedToken = decode(token);
        log.debug("DecodedToken = " + decodedToken);
      String username = decodedToken.get("sub");
        log.debug("username = " + username);
      PasienModel pasienModel = pasienService.getPasienByUsername(username);

      HashMap<String, String> map = new HashMap<>();
      if(bindingResult.hasFieldErrors()) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "gagal");
      }
      else {
          if(!appointmentRestService.cek(appointment.getDokter().getUuid(), appointment.getWaktuAwal())){
             log.error("Gagal membuat appointment");
              map.put("hasil", "gagal");
              return map;
          }
          appointment.setIsDone(false);
          appointment.setDokter(dokterService.getDokterById(appointment.getDokter().getUuid()));
          appointment.setPasien(pasienModel);
          appointmentRestService.addAppointment(appointment);
          map.put("hasil", "sukses");
          log.info("API berhasil membuat appointment");
          return map;
      }

    }

    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson gson = new Gson();
        Map<String, String> decodedToken = gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
        return decodedToken;
    }

//    @GetMapping("/appointment/list-appointment")
//    private List<AppointmentModel> retrieveListAppointment() {
//        return appointmentRestService.retrieveListAppointment();
//    }

    @GetMapping("/appointment/list-appointment")
    private List<AppointmentModel> retrieveListAppointment(@RequestHeader("Authorization") String token) {
        Map<String,String> decodedToken = decode(token);
        String username = decodedToken.get("sub");

        try {
            PasienModel pasien = pasienService.getPasienByUsername(username);
            log.debug("Pasien = " + pasien.getUsername());
            log.info("API mengambil list appointment");
            return appointmentRestService.getListAppointmentByUsername(pasien.getUsername());
        } catch (NoSuchElementException e) {
            log.error("Gagal mengambil list appointment");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien tidak ditemukan pada database"
            );
        }
    }

    @GetMapping("/appointment/listDokter")
    private List<DokterModel> retrieveListDokter() {
        log.info("Mengambil list Dokter");
        return dokterService.getListDokter();
    }

    //Retrieve List Tagihan Pasien
    @GetMapping(value = "/appointment/{kodeTagihan}")
    private AppointmentModel retrieveAppointmentByTagihan(
            @PathVariable("kodeTagihan") String kodeTagihan) {
        System.out.println("masuk");
        TagihanModel tagihan = tagihanRestService.getTagihanByKode(kodeTagihan);
        System.out.println(tagihan);
        return appointmentRestService.retrieveAppointmentByTagihan(tagihan);
    }

}
