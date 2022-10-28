package com.pletenchaos.pletenchaos.utils.common;

public final class PathConstants {

	public static final String REDIRECT = "redirect:";

	public static final String ID = "/{id}";

	public static final String LOGIN = "/login";

	public static final String REGISTER = "/register";

	public static final String HOME = "/home";

	public static final String MATERIAL = "/material";

	public static final String ORDER = "/order";

	public static final String ADD = "/add";

	public static final String DELETE_MATERIAL = "/delete" + ID;

	public static final String UPDATE_MATERIAL = "/update" + ID;

	public static final String AVAILABLE_MATERIAL = "/available";

	public static final String LOGIN_ERROR = "/login-error";

	public static final String REDIRECT_HOME = REDIRECT + HOME;

	public static final String REDIRECT_REGISTER = REDIRECT + REGISTER;

	public static final String REDIRECT_MATERIAL = REDIRECT + MATERIAL;

	public static final String REDIRECT_ORDER = REDIRECT + ORDER;

	public static final String REDIRECT_ADD_MATERIAL = REDIRECT_MATERIAL + ADD;

	public static final String REDIRECT_ADD_ORDER = REDIRECT_ORDER + ADD;

	public static final String REDIRECT_AVAILABLE_MATERIAL = REDIRECT_MATERIAL + AVAILABLE_MATERIAL;

	public static final String REDIRECT_MATERIAL_DETAIL = REDIRECT_MATERIAL + ID;

	public static final String REDIRECT_LOGIN = REDIRECT + LOGIN;


}
