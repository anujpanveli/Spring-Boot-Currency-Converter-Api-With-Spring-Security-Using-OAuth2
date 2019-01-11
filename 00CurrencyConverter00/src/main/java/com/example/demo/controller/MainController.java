package com.example.demo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Example;
import com.example.demo.service.MainService;

@RestController
public class MainController {

	@Autowired
	MainService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Welcome";
	}	
	
	@GetMapping(value = "/converter/{Amount}/USD/{TargetCurrency}")
	public List<Example> converter(@PathVariable BigDecimal Amount,@PathVariable String TargetCurrency) throws ParseException, IOException {
		return service.sendLiveRequest(Amount,TargetCurrency);
	}   
}