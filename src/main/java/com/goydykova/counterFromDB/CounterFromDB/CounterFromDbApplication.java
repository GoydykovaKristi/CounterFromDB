package com.goydykova.counterFromDB.CounterFromDB;

import com.goydykova.counterFromDB.CounterFromDB.model.Counter;
import com.goydykova.counterFromDB.CounterFromDB.service.CounterService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableKafka
public class CounterFromDbApplication {

	private final KafkaTemplate<String, Long> kafka;
	private final CounterService counterService;

	@Autowired
	public CounterFromDbApplication(KafkaTemplate<String, Long> kafka, CounterService counterService) {
		this.kafka = kafka;
		this.counterService = counterService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CounterFromDbApplication.class, args);
	}

	@KafkaListener(topics = "countBreakpointHello")
	public void update(ConsumerRecord<String, Long> record) {
		Counter updateCounter = new Counter("hello", record.value());
		updateCounter.setId(1L);
		counterService.save(updateCounter);
	}

	@KafkaListener(topics = "bye")
	public void updateBye(ConsumerRecord<String, Long> record) {
		Counter obj = new Counter("Bye", record.offset() + 1);
		obj.setId(2L);
		counterService.save(obj);
	}

	@KafkaListener(topics = "givHello")
	public void helpHello() {
		List<Counter> list = counterService.getAllCounters();
		for (Counter counter : list) {
			if (counter.getName().equals("hello")) {
				kafka.send("returnHello", counter.getCount());
			}
		}
	}

}
