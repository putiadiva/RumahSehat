package apap.tugaskelompok.rumahsehat.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.ResepModel;
import apap.tugaskelompok.rumahsehat.service.ResepRestService;
import apap.tugaskelompok.rumahsehat.service.ResepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1")

public class ResepRestController {

    @Autowired
    private ResepRestService resepRestService;

    @GetMapping(value="/resep/{idResep}")
    private ResepModel getResepDetail(@PathVariable("idResep") Long idResep){
        try{
            return resepRestService.retrieveResepByID(idResep);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Resep " + idResep + " not found"
            );
        }
    }

}
