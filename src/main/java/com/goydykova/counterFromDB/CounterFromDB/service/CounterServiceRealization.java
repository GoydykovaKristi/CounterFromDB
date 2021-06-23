package com.goydykova.counterFromDB.CounterFromDB.service;

import com.goydykova.counterFromDB.CounterFromDB.model.Counter;
import com.goydykova.counterFromDB.CounterFromDB.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CounterServiceRealization implements CounterService{

    private final CounterRepository counterRepository;

    @Autowired
    public CounterServiceRealization(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public Counter getById(long id) {
        return counterRepository.getById(id);
    }

    @Override
    public List<Counter> getAllCounters() {
        return counterRepository.findAll();
    }

    @Override
    public void save(Counter counter) {

        counterRepository.save(counter);

    }
}
