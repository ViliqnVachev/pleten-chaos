package com.pletenchaos.pletenchaos.service.interfaces;

import com.pletenchaos.pletenchaos.model.binding.NewMaterialBinding;

public interface IMaterialService {

	boolean isUnique(String name);

	boolean addMaterial(NewMaterialBinding binding, String userName);

}
