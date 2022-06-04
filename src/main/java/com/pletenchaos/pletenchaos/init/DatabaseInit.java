package com.pletenchaos.pletenchaos.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pletenchaos.pletenchaos.service.interfaces.IRoleService;

@Component
public class DatabaseInit implements CommandLineRunner {

	private final IRoleService roleService;

	@Autowired
	public DatabaseInit(IRoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public void run(String... args) throws Exception {
		roleService.initRoles();
	}

}
