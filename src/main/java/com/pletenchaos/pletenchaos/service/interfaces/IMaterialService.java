package com.pletenchaos.pletenchaos.service.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.model.binding.UpdateMaterialBinding;

public interface IMaterialService {

	boolean addMaterial(MaterialBinding binding, String userName);

	List<MaterialBinding> getMaterialsByUser(String loginName);

	boolean isOwner(String username, Long id);

	MaterialBinding getMaterialById(Long id);

	void deleteMaterial(Long id);

	void updateMaterial(Long id, @Valid UpdateMaterialBinding updatedMaterial);

}
