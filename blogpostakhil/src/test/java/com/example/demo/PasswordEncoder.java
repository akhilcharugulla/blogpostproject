package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {

		System.out.println(new BCryptPasswordEncoder().encode("charu"));
	}

	//charu  akhil
	//mani   mani@123
	//laxman  seshu@123
}
