package com.goydykova.counterFromDB.CounterFromDB.service;

import com.goydykova.counterFromDB.CounterFromDB.model.Counter;

import java.util.List;

public interface CounterService {

    Counter getById(long id);

    List<Counter> getAllCounters();

    void save(Counter counter);


}
