package com.example.todoapi.todo_api.utils;

public final  class Constant {

    private Constant() {}

    public static final String AUTH_PATH = "/api/auth";
    public static final String[] PUBLIC_ENDPOINTS = { AUTH_PATH + "/**" };


    public static final String register = "/register";
    public static final String login = "/login";
}
