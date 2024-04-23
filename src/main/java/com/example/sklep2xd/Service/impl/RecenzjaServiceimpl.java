package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.RecenzjaEntity;
import com.example.sklep2xd.Repositories.RecenzjaRep;
import com.example.sklep2xd.Service.RecenzjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RecenzjaServiceimpl implements RecenzjaService {

    private RecenzjaRep recenzjaRep;

    @Autowired
    public  RecenzjaServiceimpl (RecenzjaRep recenzjaRep){
        this.recenzjaRep = recenzjaRep;

    }
    @Override
    public List<RecenzjaDto> findallRecenzjas(){
        List<RecenzjaEntity> recenzje = recenzjaRep.findAll();
        return recenzje.stream().map((recenzja) -> mapToAdresDto(recenzja)).collect(Collectors.toList());
    }
    private RecenzjaDto mapToAdresDto(RecenzjaEntity recenzja){
        RecenzjaDto recenzjaDto = RecenzjaDto.builder()
                .idRecenzji(recenzja.getIdRecenzji())
                .ocena(recenzja.getOcena())
                .tresc(recenzja.getTresc())
                .produkt(recenzja.getProduktByProduktId())
                .klient(recenzja.getKlientByKlientId())
                .build();
        return recenzjaDto;
    }
}
