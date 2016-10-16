package org.nxstudio.octopus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/probe")
public class ProbeController {

	@RequestMapping("/")
	public String test() {
		return "OK";
	}
}
