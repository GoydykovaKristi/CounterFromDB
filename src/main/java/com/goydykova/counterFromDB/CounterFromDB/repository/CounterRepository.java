package com.goydykova.counterFromDB.CounterFromDB.repository;

import com.goydykova.counterFromDB.CounterFromDB.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long>  {
}
