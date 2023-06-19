package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.AppointmentModel;
import apap.tugaskelompok.rumahsehat.model.ResepModel;
import apap.tugaskelompok.rumahsehat.model.TagihanModel;
import apap.tugaskelompok.rumahsehat.model.UserModel;
import apap.tugaskelompok.rumahsehat.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResepService resepService;
    @Autowired
    private TagihanService tagihanService;

    @Autowired
    private ObatService obatService;

    @GetMapping("/viewall")
    public String viewAllAppointment(Model model) {
        //buat fungsi search yang berbeda-beda untuk tiap role nya.
        //fungsi nya disesuaikan dengan ketentuan soal. misal Dokter harus pakai constraint appointment yg id nya si dokter tersebut
        var auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel userSession = userService.getUserByUsername(auth.getName());
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();

        if (userSession.getRole().toString().equals("ADMIN")) {
            log.info("View Appointment sebagai ADMIN");
            model.addAttribute("role", userSession.getRole());
            model.addAttribute("listAppointment", listAppointment);
        }
        else if (userSession.getRole().toString().equals("PASIEN")){
            log.info("View Appointment sebagai PASIEN");
            List<AppointmentModel> listAppointmentPasien = new ArrayList<>();
            model.addAttribute("role", userSession.getRole());
            for (var i = 0; i < listAppointment.size(); i++) {
                AppointmentModel appointment = listAppointment.get(i);
                if (appointment.getPasien().getUsername().equals(auth.getName())) {
                    listAppointmentPasien.add(appointment);
                }
            }
            model.addAttribute("listAppointment", listAppointmentPasien);
        }
        else if (userSession.getRole().toString().equals("DOKTER")){
            log.info("View Appointment sebagai DOKTER");
            List<AppointmentModel> listAppointmentDokter = new ArrayList<>();
            model.addAttribute("role", userSession.getRole());
            for(var i = 0; i < listAppointment.size(); i++) {
                AppointmentModel appointment = listAppointment.get(i);
                if (appointment.getDokter().getUsername().equals(auth.getName())){
                    listAppointmentDokter.add(appointment);
                }
            }
            model.addAttribute("listAppointment", listAppointmentDokter);
        }
        return "viewall-appointment";
    }

    @GetMapping("/detail/{idApt}")
    public String viewDetailResepPage(@PathVariable String idApt, Model model){
        AppointmentModel apt= appointmentService.getApppointmentByID(idApt);

        model.addAttribute("apt", apt);
        return "view-appointment-detail";
    }

    @GetMapping("/confirm/{idApt}")
    public String confirmAppointmentPage(@PathVariable String idApt, Model model, HttpServletRequest request){
        AppointmentModel apt = appointmentService.getApppointmentByID(idApt);
        ResepModel resep = null;

        TagihanModel tagihan = tagihanService.getTagihanById(apt.getTagihan().getKode());
        Integer totalTagihan = 0;
        if(apt.getResep() != null){
            resep = resepService.getResepById(apt.getResep().getId());
            if(resep.getJumlah().size()!=0) {
                if (resep.getIsDone() == Boolean.FALSE) {
                    return "gagal-confirm";
                } else {
                    apt.setIsDone(Boolean.TRUE);
                    totalTagihan += apt.getDokter().getTarif();
                    tagihan.setJumlahTagihan(totalTagihan);
                }
            }
        }else{
            apt.setIsDone(Boolean.TRUE);
            totalTagihan += apt.getDokter().getTarif();
            tagihan.setJumlahTagihan(totalTagihan);
        }

        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        model.addAttribute("listAppointment", listAppointment);
        return "viewall-appointment";
    }
}
