package com.example.demo.repositories;

import com.example.demo.entities.Quote;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface QuoteRepository extends CrudRepository<Quote,Long> {

    Optional<Quote> getById(Long id);

    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO quotes (description,vote,user_id) select :description,:vote,:userId")
    void saveQuote (String description,int vote,Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE quotes set description = :description where id = :id")
    void updateQuote(String description,Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO quotes set vote = :num where id = :id")
    void setVote(int num,Long id);
}
