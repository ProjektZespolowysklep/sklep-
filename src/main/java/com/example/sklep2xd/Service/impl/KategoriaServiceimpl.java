package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.KategoriaDto;
import com.example.sklep2xd.Models.KategoriaEntity;
import com.example.sklep2xd.Repositories.KategoriaRep;
import com.example.sklep2xd.Service.KategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KategoriaServiceimpl implements KategoriaService {

    private KategoriaRep kategoriaRep;

    @Autowired
    public KategoriaServiceimpl(KategoriaRep kategoriaRep){
        this.kategoriaRep = kategoriaRep;
    }

    @Override
    public List<KategoriaDto> findallKategories(){
        List<KategoriaEntity> kategorie =kategoriaRep.findAll();
        return kategorie.stream().map((kategoria) -> mapToKategoriaDto(kategoria)).collect(Collectors.toList());
    }
    private KategoriaDto mapToKategoriaDto(KategoriaEntity kategoria){
        KategoriaDto kategoriaDto = KategoriaDto.builder()
                .idKategorii(kategoria.getIdKategorii())
                .nazwaKategorii(kategoria.getNazwaKategorii())
                .build();
        return kategoriaDto;
    }
}
