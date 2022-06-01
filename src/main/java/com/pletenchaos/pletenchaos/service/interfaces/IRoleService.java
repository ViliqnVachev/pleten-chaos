package com.pletenchaos.pletenchaos.service.interfaces;

import com.pletenchaos.pletenchaos.model.binding.RoleBinding;
import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;

public interface IRoleService {

	RoleBinding findByRole(RoleEnum admin);

}
