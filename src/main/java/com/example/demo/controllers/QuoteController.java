package com.example.demo.controllers;

import com.example.demo.dto.QuoteDto;
import com.example.demo.entities.Quote;
import com.example.demo.services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("/save/quote")
    public ResponseEntity<QuoteDto> saveQuote(@RequestBody Quote quote){
        if (quote.getDescription() != null){
            this.quoteService.saveQuote(quote.getDescription(),quote.getVote(),quote.getUsers().getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/delete/quote/{id}")
    public ResponseEntity<QuoteDto> deleteQuote(@PathVariable Long id){
        Optional<QuoteDto> qId = quoteService.getQuotes(id);
        if (qId.get().getUser().getId().equals(id)){
            this.quoteService.deleteQuote(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/quote/{id}")
    public Optional<QuoteDto> getQuote(@PathVariable Long id){
      return this.quoteService.getQuotes(id);
    }

    @PostMapping("/update/quote")
    public ResponseEntity<QuoteDto> updateQuote(@RequestBody QuoteDto quote){
        if (quote.getDescription() != null){
            this.quoteService.updateQuote(quote.getDescription(),quote.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/set/vote/{id}/{number}")
    public void setVote(@PathVariable int number, @PathVariable Long id){
        this.quoteService.setVote(id,number);
    }
}
