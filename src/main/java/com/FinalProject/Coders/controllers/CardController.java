package com.FinalProject.Coders.controllers;

import com.FinalProject.Coders.DTOs.CardDTO;
import com.FinalProject.Coders.DTOs.GeneralDTO;
import com.FinalProject.Coders.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/card")
    public ResponseEntity<GeneralDTO> addCard(@RequestBody CardDTO cardDTO, @RequestParam("email")String email){
        CardDTO dto =cardService.addCard(cardDTO, email);
        GeneralDTO generalDTO=new GeneralDTO();
        generalDTO.setStatusCode(HttpStatus.ACCEPTED);
        generalDTO.setDTO(dto);
        return ResponseEntity.ok(generalDTO);
    }
}
