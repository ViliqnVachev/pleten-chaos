package com.pletenchaos.pletenchaos.web;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeCotroller {

	@GetMapping
	public String getIndex() {
		return "index";
	}

}
