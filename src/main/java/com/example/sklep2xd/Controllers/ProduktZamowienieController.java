package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Dto.ProduktZamowienieDto;
import com.example.sklep2xd.Models.ProduktZamowienieEntity;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.ProduktZamowienieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/ProduktZamowienie")
public class ProduktZamowienieController {

    private final ProduktZamowienieService produktZamowienieService;

    @Autowired
    public ProduktZamowienieController(ProduktZamowienieService produktZamowienieService) {
        this.produktZamowienieService = produktZamowienieService;
    }

    @GetMapping("/lista")
    public String listProduktZamowienia(Model model) {
        List<ProduktZamowienieDto> produktZamowienia = produktZamowienieService.findAllProduktZamowienia();
        model.addAttribute("header", "Lista wszystkich Produktów w Zamówieniach");
        model.addAttribute("produktZamowienieList", produktZamowienia);
        return "ProduktZamowienia";
    }

    @GetMapping("/dodajform")
    public String createProduktZamowienieForm(Model model) {
        ProduktZamowienieEntity produktZamowienie = new ProduktZamowienieEntity();
        model.addAttribute("produktZamowienie", produktZamowienie);
        return "NoweProduktZamowienie";
    }

    @PostMapping("/dodajform")
    public String saveProduktZamowienie(@ModelAttribute("produktZamowienie") ProduktZamowienieEntity produktZamowienie) {
        produktZamowienieService.saveProduktZamowienie(produktZamowienie);
        return "redirect:/ProduktZamowienie/lista";
    }

    @GetMapping("/edytuj/{produktZamowienieId}")
    public String editProduktZamowienieForm(@PathVariable("produktZamowienieId") int produktZamowienieId, Model model) {
        ProduktZamowienieDto produktZamowienie = produktZamowienieService.findProduktZamowienieById(produktZamowienieId);
        model.addAttribute("produktZamowienie", produktZamowienie);
        return "EdytujProduktZamowienie";
    }

    @PostMapping("/edytuj/{produktZamowienieId}")
    public String updateProduktZamowienie(@PathVariable("produktZamowienieId") int produktZamowienieId, @ModelAttribute("produktZamowienie") ProduktZamowienieDto produktZamowienieDto) {
        produktZamowienieDto.setIdpz(produktZamowienieId);//idk co sie dzieje
        produktZamowienieService.updateProduktZamowienie(produktZamowienieDto);
        return "redirect:/ProduktZamowienie/lista";
    }
}