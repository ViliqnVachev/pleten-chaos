package com.pletenchaos.pletenchaos.utils;

import org.modelmapper.ModelMapper;

public class ConvertObjUtil {

	public static <T> T convert(Object obj, Class<T> type, ModelMapper mapper) {
		return mapper.map(obj, type);
	}

}
