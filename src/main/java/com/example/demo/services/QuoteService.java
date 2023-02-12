package com.example.demo.services;

import com.example.demo.dto.QuoteDto;
import com.example.demo.entities.Quote;
import com.example.demo.repositories.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public void saveQuote(String descr, int vote, Long userId) {
        quoteRepository.saveQuote(descr, vote, userId);
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public Optional<QuoteDto> getQuotes(Long id) {
        Optional<Quote> quote = quoteRepository.getById(id);
        return Optional.ofNullable(QuoteDto.builder()
                .description(quote.get().getDescription())
                .vote(quote.get().getVote())
                .user(quote.get().getUsers())
                .build());
    }

    public void updateQuote(String description, Long id) {
        quoteRepository.updateQuote(description, id);
    }

    public void setVote(Long id, int num) {
        quoteRepository.setVote(num, id);
    }
}
