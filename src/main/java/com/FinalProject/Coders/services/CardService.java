package com.FinalProject.Coders.services;

import com.FinalProject.Coders.DTOs.CardDTO;
import com.FinalProject.Coders.entities.CardEntity;
import com.FinalProject.Coders.entities.UserEntity;
import com.FinalProject.Coders.mapper.ModelMapperUtil;
import com.FinalProject.Coders.repositories.CardRepository;
import com.FinalProject.Coders.repositories.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final ModelMapperUtil modelMapperUtil;
    private final UserRepo userRepo;

    public CardDTO addCard(CardDTO cardDTO, String email){
        CardEntity card=modelMapperUtil.convertToEntity(cardDTO, CardEntity.class);
        UserEntity user=userRepo.findByEmail(email).orElseThrow();
        card.setUser(user);
        return modelMapperUtil.convertToDto(cardRepository.saveAndFlush(card), CardDTO.class);
    }
}
