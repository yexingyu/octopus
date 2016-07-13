package org.nxstudio.octopus.controller;

import java.util.List;

import org.nxstudio.octopus.fetcher.YahooFetcher;
import org.nxstudio.octopus.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	@Autowired
	private YahooFetcher	yahooFetcher;
	@Autowired
	private SymbolService	symbolService;

	@RequestMapping("/symbols")
	public List<String> greeting() {
		return symbolService.getAllSymbols();
	}

	@RequestMapping("/test")
	public String test() {
		yahooFetcher.fetchHistorical();
		return "OK";
	}
}
