package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Dto.KategoriaDto;
import com.example.sklep2xd.Models.AdresEntity;
import com.example.sklep2xd.Models.KategoriaEntity;
import com.example.sklep2xd.Service.AdresService;
import com.example.sklep2xd.Service.KategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/Kategoria")
public class KategoriaController {

    private KategoriaService kategoriaService;

    @Autowired
    public KategoriaController(KategoriaService kategoriaService){
        this.kategoriaService = kategoriaService;
    }

    @GetMapping("/Kategoria")
    public String listKlients(Model model){
        List<KategoriaDto> kategorie = kategoriaService.findallKategories();
        model.addAttribute("Kategorie", kategorie);
        return "Kategorie";
    }

    @GetMapping("/dodajform")
    public String createKategoriaForm(Model model){
        KategoriaEntity kategoria= new KategoriaEntity();
        model.addAttribute("kategoria", kategoria);
        return "nawakategoria";
    } //wyświetlenie formularza dodania

    @PostMapping("/dodajform")
    public String saveKategoria(@ModelAttribute("kategoria") KategoriaEntity kategoria) {
        kategoriaService.saveKategoria(kategoria);
        return "kategorie/NowaKategoria"; //nie wiem o co chodzi co tam kiszka nakminił
    }

    @GetMapping("/edytuj/{kategoriaId}")
    public String editKategoriaForm(@PathVariable("kategoriaId") int kategoriaId, Model model){
        KategoriaDto kategoria = kategoriaService.findKategoriaByIdKategori(kategoriaId); //w tutorialu tu daje jeszcze .get() ale to chyba nie ma znaczenia
        model.addAttribute("kategoria", kategoria);
        return "kategorie/Edytujkategorie";
    }
    @PostMapping("/edytuj/{kategoriaId}")
    public String updateKategoria(@PathVariable("kategoriaId") int kategoriaId, @ModelAttribute("kategoria") KategoriaDto kategoriaDto){
        kategoriaDto.setIdKategorii(kategoriaId);
        kategoriaService.updateKategoria(kategoriaDto);
        return "redirect:/Kategoria/lista";
    }

}
