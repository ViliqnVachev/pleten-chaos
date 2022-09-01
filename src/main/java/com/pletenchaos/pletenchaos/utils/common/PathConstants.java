package com.pletenchaos.pletenchaos.utils.common;

public final class PathConstants {

	private static final String REDIRECT = "redirect:";

	public static final String LOGIN = "/login";

	public static final String REGISTER = "/register";

	public static final String HOME = "/home";

	public static final String MATERIAL = "/material";

	public static final String ADD_MATERIAL = "/add";

	public static final String AVAILABLE_MATERIAL = "/available";

	public static final String LOGIN_ERROR = "/login-error";

	public static final String REDIRECT_HOME = REDIRECT + HOME;

	public static final String REDIRECT_REGISTER = REDIRECT + REGISTER;

	public static final String REDIRECT_ADD_MATERIAL = REDIRECT + MATERIAL + ADD_MATERIAL;

	public static final String REDIRECT_AVAILABLE_MATERIAL = REDIRECT + MATERIAL + AVAILABLE_MATERIAL;

	public static final String REDIRECT_LOGIN = REDIRECT + LOGIN;

}
