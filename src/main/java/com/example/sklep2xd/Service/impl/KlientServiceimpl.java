package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Repositories.AdresRep;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Service.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KlientServiceimpl implements KlientService {

    private KlientRep klientRep;
    private AdresRep adresRep;

    @Autowired
    public KlientServiceimpl(KlientRep klientRep, AdresRep adresRep){
        this.adresRep = adresRep;
        this.klientRep = klientRep;

    }

    @Override
    public List<KlientDto> findallKlients(){
        List<KlientEntity> klienci = klientRep.findAll();
        return klienci.stream().map((klient) -> mapToKlientDto(klient)).collect(Collectors.toList());
    }

    @Override
    public KlientDto findKlientByidKlienta(int id) {
        KlientEntity klient = klientRep.findByIdKlienta(id);
        return mapToKlientDto(klient);
    }

    private KlientDto mapToKlientDto(KlientEntity klient){
        KlientDto klientDto  =KlientDto.builder()
                .idKlienta(klient.getIdKlienta())
                .login(klient.getLogin())
                .email(klient.getEmail())
                .haslo(klient.getHaslo())
                .telefon(klient.getTelefon())
                .imie(klient.getImie())
                .nazwisko(klient.getNazwisko())
                .adresId(klient.getAdresByAdresId())
                .build();
                return klientDto;
    }
}
