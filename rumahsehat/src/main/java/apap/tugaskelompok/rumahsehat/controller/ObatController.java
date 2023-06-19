package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.ObatModel;
import apap.tugaskelompok.rumahsehat.model.UserModel;
import apap.tugaskelompok.rumahsehat.service.ObatService;
import apap.tugaskelompok.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/obat")
public class ObatController {

    @Autowired
    ObatService obatService;

    @Autowired
    private UserService userService;

    @GetMapping("/view-all")
    public String viewAllObat(Model model, Principal principal) {
        UserModel user = userService.getUserByUsername(principal.getName());
        List<ObatModel> allObat =obatService.getAllObat();
        model.addAttribute("allObat", allObat);
        if (user.getRole().name().equals("APOTEKER")) {
            return "viewall-obat";
        } else {
            return "viewall-obat-admin";
        }
    }

    @GetMapping("/{idObat}/update")
    public String updateObatForm(
            @PathVariable String idObat,
            Model model
    ) {
        ObatModel obat = obatService.getObatById(idObat);
        model.addAttribute("obat", obat);
        return "form-update-obat";
    }

    @PostMapping("/{idObat}/update")
    public String updateObatSubmit(
            @PathVariable String idObat,
            @ModelAttribute ObatModel obat,
            RedirectAttributes redirectAttributes,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "The error occurred.");
            return "redirect:/obat/view-all";
        }
        ObatModel obatToUpdate = obatService.getObatById(idObat);
        obatService.updateStokObat(obatToUpdate, obat.getStok());
        redirectAttributes.addFlashAttribute("success","Stok obat berhasil diupdate");
        return "redirect:/obat/view-all";
    }
}
