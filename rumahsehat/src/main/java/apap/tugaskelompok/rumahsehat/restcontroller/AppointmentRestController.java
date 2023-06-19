package apap.tugaskelompok.rumahsehat.restcontroller;


import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.ResepModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.service.AppointmentRestService;
import apap.tugaskelompok.rumahsehat.service.AppointmentRestServiceImpl;
import apap.tugaskelompok.rumahsehat.service.ResepService;
import apap.tugaskelompok.rumahsehat.service.TagihanRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController("AppointmentRestController")
@RequestMapping("/api/v1")
public class AppointmentRestController {

    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    private TagihanRestService tagihanRestService;


    @CrossOrigin
    @GetMapping(value="/list-appointment")
    private List<AppointmentModel> getListAppointment(){
        return appointmentRestService.retrieveListAppointment();
    }

    @GetMapping(value="/appointment/{idApt}")
    private AppointmentModel getAppointmentDetail(@PathVariable("idApt") String idApt){
        try{
            return appointmentRestService.retrieveApppointmentByID(idApt);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Appointmnet " + idApt + " not found"
            );
        }
    }

    @GetMapping(value = "/appointment/tagihan/{kodeTagihan}")
    public AppointmentModel retrieveAppointmentByTagihan(
            @PathVariable("kodeTagihan") String kodeTagihan) {
        TagihanModel tagihan = tagihanRestService.getTagihanByKode(kodeTagihan);
        log.info("Retrieve Appointment Model Berhasil");
        return appointmentRestService.retrieveAppointmentByTagihan(tagihan);
    }
}
