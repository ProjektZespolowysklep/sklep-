package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Dto.ProduktZamowienieDto;
import com.example.sklep2xd.Models.ProduktZamowienieEntity;

import java.util.List;

public interface ProduktZamowienieService {
    List<ProduktZamowienieDto> findAllProduktZamowienia();
    ProduktZamowienieDto findProduktZamowienieById(int id);
    ProduktZamowienieEntity saveProduktZamowienie(ProduktZamowienieEntity produktZamowienie);
    void updateProduktZamowienie(ProduktZamowienieDto produktZamowienieDto);
}
