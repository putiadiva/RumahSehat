package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.ApotekerModel;
import apap.tugaskelompok.rumahsehat.model.Role;
import apap.tugaskelompok.rumahsehat.model.UserModel;
import apap.tugaskelompok.rumahsehat.service.ApotekerService;
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
@RequestMapping("/apoteker")
public class ApotekerController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApotekerService apotekerService;

    private static final String ERROR_ACTION = "error";
    private static final String APOTEKER_ADD = "redirect:/apoteker/add";

    @GetMapping(value="/add")
    public String addApotekerForm(Model model) {
        var apoteker = new ApotekerModel();
        model.addAttribute("apoteker", apoteker);
        log.info("Membuka form add apoteker");
        return "form-add-apoteker";
    }

    @PostMapping(value="/add")
    public String addApotekerSubmit(@ModelAttribute ApotekerModel apoteker,
                                     Model model,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttrs) {

        String pass = apoteker.getPassword();
        String confirmpass = request.getParameter("confirmpass");

        for(UserModel user : userService.getListUser()){
            if(user.getUsername().equals(apoteker.getUsername())){
                redirectAttrs.addFlashAttribute(ERROR_ACTION,
                        "Username sudah digunakan. Harap masukkan username lain");
                log.error("Gagal add apoteker. Username sudah digunakan.");
                return APOTEKER_ADD;
            }
        }

        if(!userService.checkIfMatched(pass, confirmpass)){
            redirectAttrs.addFlashAttribute(ERROR_ACTION,
                    "Password dan Password Konfirmasi Berbeda");
            log.error("Gagal add apoteker. Password berbeda.");
            return APOTEKER_ADD;
        }
        if(!userService.checkIfValidRequirement(pass)){
            redirectAttrs.addFlashAttribute(ERROR_ACTION,
                    "Password harus mengandung angka, huruf besar, huruf kecil, simbol, dan minimal memiliki 8 karakter");
            log.error("Gagal add apoteker. Password tidak valid.");
            return APOTEKER_ADD;
        }

        apoteker.setRole(Role.APOTEKER);
        apoteker.setIsSso(false);
        apotekerService.addApoteker(apoteker);

        redirectAttrs.addFlashAttribute("success",
                String.format("User baru dengan nama %s dan role Apoteker berhasil dibuat",
                        apoteker.getNama()));

        model.addAttribute("apoteker", apoteker);
        log.info("Apoteker berhasil dibuat.");
        return "redirect:/apoteker/viewall";
    }

    @GetMapping(value="/viewall")
    public String viewAllApoteker(Model model) {
        List<ApotekerModel> listApoteker = apotekerService.getListApoteker();
        model.addAttribute("listApoteker", listApoteker);
        log.info("Menampilkan halaman list apoteker");
        return "viewall-apoteker";
    }

}