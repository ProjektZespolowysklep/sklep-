package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.RecenzjaEntity;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.RecenzjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/Recenzja")
public class RecenzjaController {

        private final RecenzjaService recenzjaService;

        @Autowired
        public RecenzjaController(RecenzjaService recenzjaService) {
            this.recenzjaService = recenzjaService;
        }

        @GetMapping("/lista")
        public String listRecenzje(Model model) {
            List<RecenzjaDto> recenzje = recenzjaService.findAllRecenzje();
            model.addAttribute("header", "Lista wszystkich Recenzji");
            model.addAttribute("recenzjaList", recenzje);
            return "Recenzje";
        }

        @GetMapping("/dodajform")
        public String createRecenzjaForm(Model model) {
            RecenzjaEntity recenzja = new RecenzjaEntity();
            model.addAttribute("recenzja", recenzja);
            return "NowaRecenzja";
        }

        @PostMapping("/dodajform")
        public String saveRecenzja(@ModelAttribute("recenzja") RecenzjaEntity recenzja) {
            recenzjaService.saveRecenzja(recenzja);
            return "redirect:/Recenzja/lista";
        }

        @GetMapping("/edytuj/{recenzjaId}")
        public String editRecenzjaForm(@PathVariable("recenzjaId") int recenzjaId, Model model) {
            RecenzjaDto recenzja = recenzjaService.findRecenzjaById(recenzjaId);
            model.addAttribute("recenzja", recenzja);
            return "EdytujRecenzje";
        }

        @PostMapping("/edytuj/{recenzjaId}")
        public String updateRecenzja(@PathVariable("recenzjaId") int recenzjaId, @ModelAttribute("recenzja") RecenzjaDto recenzjaDto) {
            recenzjaDto.setIdRecenzji(recenzjaId);
            recenzjaService.updateRecenzja(recenzjaDto);
            return "redirect:/Recenzja/lista";
        }
    }