package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.ChartViewDto;
import apap.tugaskelompok.rumahsehat.model.DokterModel;
import apap.tugaskelompok.rumahsehat.projection.DokterMonthlyIncomeProjection;
import apap.tugaskelompok.rumahsehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/dokter/chart")
public class DokterChartController {

    @Autowired
    DokterService dokterService;

    @RequestMapping("/line/1")
    public String defaultViewTest4(Model model) {
        List<DokterMonthlyIncomeProjection> lst = dokterService.getIncomeEachDokterThisYearPerMonth();
        List<Map<String, Object>> dataset = new ArrayList<>();

        int jumlahDokter = lst.size()/12;

        for (int i=0; i<jumlahDokter ; i++) {
            String currDokter = lst.get(i*12).getDokter();
            Map<String, Object> currMap = new HashMap<>();
            ArrayList<Integer> currDokterIncomes = new ArrayList<>();
            Collections.addAll(currDokterIncomes, 0,0,0,0,0,0,0,0,0,0,0,0);

            for (int j=i*12; j<(i+1)*12 ; j++) {
                if (currDokter.equals(lst.get(j).getDokter())) {
                    currDokterIncomes.set((j+11)%12, lst.get(j).getIncome());
                } else {
                    break;
                }
            }
            currMap.put("dokter", currDokter);
            currMap.put("incomes", currDokterIncomes);
            dataset.add(currMap);
        }

        model.addAttribute("dokterIncomes", dataset);
        return "chart-dokter-default";
    }

    @GetMapping(value = "/line/2")
    public String pilihDokterForm(
            Model model
    ) {
        ChartViewDto chartViewDto = new ChartViewDto();
        List<DokterModel> allDokter = dokterService.getListDokter();
        model.addAttribute("chartViewDto", chartViewDto);
        model.addAttribute("allDokter", allDokter);
        return "form-select-dokter";
    }

    @PostMapping(value = "/line/2")
    public String submitPilihanDokter(
            @ModelAttribute ChartViewDto chartViewDto,
            Model model
    ) {
        Set<DokterModel> dokters = new HashSet<>();
        ChartViewDto c = chartViewDto;

        Optional<DokterModel> d1 = dokterService.getDokterByUuid(c.getDokter1().getUuid());
        if (d1.isPresent()) {
            dokters.add(d1.get());
        }

        Optional<DokterModel> d2 = dokterService.getDokterByUuid(c.getDokter2().getUuid());
        if (d2.isPresent()) {
            dokters.add(d2.get());

        }

        Optional<DokterModel> d3 = dokterService.getDokterByUuid(c.getDokter3().getUuid());
        if (d3.isPresent()) {
            dokters.add(d3.get());

        }

        Optional<DokterModel> d4 = dokterService.getDokterByUuid(c.getDokter4().getUuid());
        if (d4.isPresent()) {
            dokters.add(d4.get());

        }

        Optional<DokterModel> d5 = dokterService.getDokterByUuid(c.getDokter5().getUuid());
        if (d5.isPresent()) {
            dokters.add(d5.get());

        }

        List<Map<String, Object>> dataset = new ArrayList<>();
        for (DokterModel dokter : dokters) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("dokter", dokter.getNama());
            List<DokterMonthlyIncomeProjection> tempList = dokterService.getIncomeByDokterByYear(dokter, 2022);
            List<Integer> incomes = new ArrayList<>();
            for (int i=0; i< tempList.size(); i++) {
                incomes.add(tempList.get(i).getIncome());
            }
            tempMap.put("incomes", incomes);
            dataset.add(tempMap);
        }

        model.addAttribute("dokterIncomes", dataset);
        return "chart-dokter-selected";
    }
}
