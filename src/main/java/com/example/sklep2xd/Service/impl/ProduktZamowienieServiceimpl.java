package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.ProduktZamowienieDto;
import com.example.sklep2xd.Models.ProduktZamowienieEntity;
import com.example.sklep2xd.Repositories.ProduktZamowienieRep;
import com.example.sklep2xd.Service.ProduktZamowienieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
@Controller
public class ProduktZamowienieServiceimpl implements ProduktZamowienieService {

    private ProduktZamowienieRep produktZamowienieRep;


    @Autowired
    public  ProduktZamowienieServiceimpl(ProduktZamowienieRep produktZamowienieRep){
        this.produktZamowienieRep  =produktZamowienieRep;

        }
    @Override
    public List<ProduktZamowienieDto> findallProduktZamowienies(){
        List<ProduktZamowienieEntity> produktzamowienia  =produktZamowienieRep.findAll();
        return produktzamowienia.stream().map((produktZamowienie) -> mapToAdresDto(produktZamowienie)).collect(Collectors.toList());
    }
    private  ProduktZamowienieDto mapToAdresDto(ProduktZamowienieEntity produktZamowienie){
        ProduktZamowienieDto produktZamowienieDto = ProduktZamowienieDto.builder()
                .ilosc(produktZamowienie.getIlosc())
                .produkt(produktZamowienie.getProduktByProduktId())
                .zamowienie(produktZamowienie.getZamowienieByZamowienieId())
                .build();
        return produktZamowienieDto;
    }
}
