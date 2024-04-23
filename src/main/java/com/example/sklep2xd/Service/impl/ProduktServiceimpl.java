package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Service.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduktServiceimpl implements ProduktService {

    private ProduktRep produktRep;
    @Autowired
    public ProduktServiceimpl(ProduktRep produktRep){
        this.produktRep = produktRep;
    }

    @Override
    public List<ProduktDto> findallProduktys(){
        List<ProduktEntity> produkty = produktRep.findAll();
        return produkty.stream().map((produkt) -> mapToProduktDto(produkt)).collect(Collectors.toList());
    }
    private ProduktDto mapToProduktDto(ProduktEntity produkt){
        ProduktDto produktDto = ProduktDto.builder()
                .idProduktu(produkt.getIdProduktu())
                .nazwa(produkt.getNazwa())
                .cena(produkt.getCena())
                .rozmiar(produkt.getRozmiar())
                .urlzdjecia(produkt.getUrlzdjecia())
                .kategoria(produkt.getKategoriaByKategoriaId())
                .build();
        return produktDto;
    }
}
