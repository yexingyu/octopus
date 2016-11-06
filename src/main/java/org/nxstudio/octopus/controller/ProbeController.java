package org.nxstudio.octopus.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/probe")
public class ProbeController {

	@RequestMapping("/")
	public String test() {
		return "OK";
	}
}
