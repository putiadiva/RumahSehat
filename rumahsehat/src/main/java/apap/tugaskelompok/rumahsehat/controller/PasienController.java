package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.PasienModel;
import apap.tugaskelompok.rumahsehat.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pasien")
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @GetMapping(value="/viewall")
    private String viewAllPasien(Model model) {
        List<PasienModel> listPasien = pasienService.getListPasien();
        model.addAttribute("listPasien", listPasien);
        return "viewall-pasien";
    }
}


