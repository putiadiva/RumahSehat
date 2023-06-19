package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.model.Role;
import apap.tugaskelompok.rumahsehat.model.UserModel;
import apap.tugaskelompok.rumahsehat.service.DokterService;
import apap.tugaskelompok.rumahsehat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dokter")
public class DokterController {

    @Autowired
    private UserService userService;
    @Autowired
    private DokterService dokterService;

    private static final String ERROR_ACTION = "error";
    private static final String DOKTER_ADD = "redirect:/dokter/add";

    @GetMapping(value="/add")
    public String addDokterForm(Model model) {
        var dokter = new DokterModel();
        model.addAttribute("dokter", dokter);
        log.info("Membuka form add dokter");
        return "form-add-dokter";
    }

    @PostMapping(value="/add")
    public String addDokterSubmit(@ModelAttribute DokterModel dokter,
                                     Model model,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttrs) {

        String pass = dokter.getPassword();
        String confirmpass = request.getParameter("confirmpass");
        String tarif = request.getParameter("dtarif");

        for(UserModel user : userService.getListUser()){
            if(user.getUsername().equals(dokter.getUsername())){
                redirectAttrs.addFlashAttribute(ERROR_ACTION,
                       "Username sudah digunakan. Harap gunakan username lain");
                log.error("Gagal add dokter. Username sudah digunakan.");
                return DOKTER_ADD;
            }
        }

        if(!dokterService.checkIfTarifInt(tarif)){
            redirectAttrs.addFlashAttribute(ERROR_ACTION,
                    "Tarif harus dalam bentuk nominal");
            log.error("Gagal add dokter. Tarif tidak berbentuk nominal.");
            return DOKTER_ADD;
        }
        if(!userService.checkIfMatched(pass, confirmpass)){
            redirectAttrs.addFlashAttribute(ERROR_ACTION,
                  "Password dan Password Konfirmasi Berbeda");
            log.error("Gagal add dokter. Password berbeda.");
            return DOKTER_ADD;
        }
        if(!userService.checkIfValidRequirement(pass)){
            redirectAttrs.addFlashAttribute(ERROR_ACTION,
                    "Password harus mengandung angka, huruf besar, huruf kecil, simbol, dan minimal memiliki 8 karakter");
            log.error("Gagal add dokter. Password tidak valid.");
            return DOKTER_ADD;
        }

        dokter.setRole(Role.DOKTER);
        dokter.setTarif(Integer.parseInt(tarif));
        dokter.setIsSso(false);
        dokterService.addDokter(dokter);

        redirectAttrs.addFlashAttribute("success",
                String.format("User baru dengan nama %s dan role Dokter berhasil dibuat",
                        dokter.getNama()));

        model.addAttribute("dokter", dokter);
        log.info("Dokter berhasil dibuat");
        return "redirect:/dokter/viewall";
    }

    @GetMapping(value="/viewall")
    public String viewAllDokter(Model model) {
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        log.info("Menampilkan halaman list dokter");
        return "viewall-dokter";
    }

}
