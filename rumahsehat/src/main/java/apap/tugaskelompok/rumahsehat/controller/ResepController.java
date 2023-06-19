package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.*;
import apap.tugaskelompok.rumahsehat.repository.ObatDb;
import apap.tugaskelompok.rumahsehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResepController {
    @Autowired
    private ObatService obatService;

    @Autowired
    private ResepService resepService;

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private JumlahService jumlahService;

    @Autowired
    private TagihanService tagihanService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private ObatDb obatDb;


    @GetMapping("/appointment/resep/{idApt}")
    public String addResepFormPage(Model model, @PathVariable String idApt){
        List<ObatModel> listObat = obatService.getAllObat();
        List<JumlahModel> listJumlah = jumlahService.getListJumlah();

        ResepModel resep = new ResepModel();

        AppointmentModel appointment = appointmentService.getApppointmentByID(idApt);
        resep.setAppointment(appointment);
        //System.out.println(appointment);

        JumlahModel jumlahResepObat = new JumlahModel();
        jumlahResepObat.setObat(new ObatModel());
        resep.setJumlah(new ArrayList<>());
        resep.getJumlah().add(jumlahResepObat);
        //System.out.println(listObat);

        model.addAttribute("listObat", listObat);
        model.addAttribute("listJumlah", listJumlah);
        model.addAttribute("resep", resep);
        return "form-add-resep";
    }



    @PostMapping(value = "/appointment/resep", params={"save"})
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, Model model){
        LocalDateTime now = LocalDateTime.now();
        resep.setCreatedAt(now);
        resep.setIsDone(false);
        resep.setAppointment(appointmentService.getApppointmentByID(resep.getAppointment().getKode()));

        List<JumlahModel> jumlahResepObat = resep.getJumlah();

        for (JumlahModel jumlah : jumlahResepObat){
            ObatModel obat = obatService.getObatById(jumlah.getObat().getIdObat());
            jumlah.setResep(resep);
            jumlah.setObat(obat);

        }

        //System.out.println(jumlahResepObat);
        resepService.addResep(resep);

        for (JumlahModel jumlah : jumlahResepObat){
            jumlah.setResep(resep);
            jumlahService.addJumlah(jumlah);
        }
        model.addAttribute("idResep", resep.getId());
        return "add-resep";
    }


    @PostMapping(value= "/appointment/resep", params = {"addRowResep"})
    private String addRowResepMultiple( @ModelAttribute ResepModel resep, Model model
    ){
        if(resep.getJumlah() == null || resep.getJumlah().size() == 0){
            resep.setJumlah(new ArrayList<>());
        }
        resep.getJumlah().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getAllObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        return "form-add-resep";
    }

    @PostMapping(value = "/appointment/resep", params = {"deleteRowResep"})
    private String deleteRowResepMultiple( @ModelAttribute ResepModel resep, @RequestParam("deleteRowResep") Integer row, Model model){
        final Integer rowId = row;
        resep.getJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getAllObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat );
        return "form-add-resep";

    }

    @GetMapping("/resep/viewall")
    public String viewAllResep(Model model) {
        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }

    @GetMapping("/resep/detail/{idResep}")
    public String viewDetailResepPage(@PathVariable Long idResep, Model model){
        ResepModel resep= resepService.getResepById(idResep);
        List<JumlahModel> listResep = resep.getJumlah();
        String dokter = resep.getAppointment().getDokter().getNama();

        model.addAttribute("listResep", listResep);
        model.addAttribute("dokter", dokter);
        model.addAttribute("resep", resep);
        System.out.print(resep);
        return "view-resep-detail";
    }

    @GetMapping("/resep/confirm/{idResep}")
    public String confirmResepPage(@PathVariable Long idResep, Model model, HttpServletRequest request){
        ResepModel resep= resepService.getResepById(idResep);
        AppointmentModel apt = appointmentService.getApppointmentByID(resep.getAppointment().getKode());
        List<JumlahModel> jumlahResepObat = resep.getJumlah();
        TagihanModel tagihan = tagihanService.getTagihanById(apt.getTagihan().getKode());
        Integer totalTagihan = 0;
        for (JumlahModel jumlah : jumlahResepObat){
            ObatModel obat = obatService.getObatById(jumlah.getObat().getIdObat());
            if(jumlah.getKuantitas() > obat.getStok() ){
                return "gagal-confirm";
            } else {
                totalTagihan += obat.getHarga()*jumlah.getKuantitas();
            }
        }

        resep.setIsDone(Boolean.TRUE);
        apt.setIsDone(Boolean.TRUE);
        totalTagihan += apt.getDokter().getTarif();
        tagihan.setJumlahTagihan(totalTagihan);

        ApotekerModel aptkr = apotekerService.getApotekerByUsername(request.getRemoteUser());
        resep.setApoteker(aptkr);

        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }


}
