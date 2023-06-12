package edu.kh.project.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/sendSms")
@SessionAttributes("authKey")
@RequiredArgsConstructor
@RestController
public class SmsController {



}