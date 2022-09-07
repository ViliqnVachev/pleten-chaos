package com.pletenchaos.pletenchaos.service.interfaces;

import java.util.List;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;

public interface IMaterialService {

	boolean isUnique(String name);

	boolean addMaterial(MaterialBinding binding, String userName);

	List<MaterialBinding> getMaterialsByUser(String loginName);

	boolean isOwner(String username, Long id);

	MaterialBinding getMaterialById(Long id);

}
