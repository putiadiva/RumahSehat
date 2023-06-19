package apap.tugaskelompok.rumahsehat.restcontroller;

import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.model.Role;
import apap.tugaskelompok.rumahsehat.payload.MessageResponse;
import apap.tugaskelompok.rumahsehat.payload.Nominal;
import apap.tugaskelompok.rumahsehat.payload.PasienDTO;
import apap.tugaskelompok.rumahsehat.service.PasienRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Base64;
import java.util.Map;
import java.util.NoSuchElementException;
@CrossOrigin(maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class PasienRestController {
    @Autowired
    PasienRestService pasienRestService;

// Buat flutter
    @PostMapping(value = "/pasien/add")
    public ResponseEntity<Object> registrasiPasien(@RequestBody PasienDTO pasienModel) {
        //
        try {
            pasienRestService.getProfile(pasienModel.getUsername());
            log.error("Gagal membuat pasien, username telah digunakan");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User " + pasienModel.getUsername() + "telah digunakan"
            );
        } catch (NoSuchElementException e) {
            var pasien = new PasienModel();
            pasien.setNama(pasienModel.getNama());
            pasien.setUsername(pasienModel.getUsername());
            pasien.setPassword(pasienModel.getPassword());
            pasien.setEmail(pasienModel.getEmail());
            pasien.setUmur(pasienModel.getUmur());
            pasien.setSaldo(0);
            pasien.setIsSso(false);
            pasien.setRole(Role.PASIEN);
            pasienRestService.registrasiPasien(pasien);
            log.info("Pasien berhasil dibuat");
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
    }

    @GetMapping(value = "/pasien")
    public PasienModel retrieveUser(
            @RequestHeader("Authorization") String token) {
        Map<String, String> decodedToken = decode(token);
        String username = decodedToken.get("sub");
        try {
            PasienModel user = pasienRestService.getProfile(username);
            log.info("Return patient information");
            return user;
        } catch (NoSuchElementException e) {
            log.error("Patient not found");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User " + username + " tidak ditemukan pada database"
            );
        }
    }

    @PostMapping(value = "/pasien/topUp")
    public PasienModel topUpSaldo(@RequestHeader("Authorization") String token ,@Valid @RequestBody Nominal nominal) {
        try {
            Map<String, String> decodedToken = decode(token);
            String username = decodedToken.get("sub");
            var pasienModel = pasienRestService.getProfile(username);
            pasienRestService.topUp(pasienModel, Integer.parseInt(nominal.getnominal()));
            log.info("Top Up berhasil");
            return pasienModel;
        } catch (NoSuchElementException e) {
            log.error("Top Up gagal");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pasien tidak ditemukan pada database"
            );
        }
    }

    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        var decoder = Base64.getUrlDecoder();
        var payload = new String(decoder.decode(chunks[1]));
        var gson = new Gson();
        return gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
    }

   @GetMapping(value = "/pasien/{username}")
    public PasienModel getProfileByUsername(@PathVariable("username") String username) {
        try {
            return pasienRestService.getProfileByUsername(username);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient with username " + username + " Not Found."
            );
        }
    }
}
