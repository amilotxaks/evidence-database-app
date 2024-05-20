package org.example.evidencedatabasewebapp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.evidencedatabasewebapp.dtos.UserRegistrationForm;
import org.example.evidencedatabasewebapp.repository.UserRepository;
import org.example.evidencedatabasewebapp.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {
    @Value("${registration.invitation-key}")
    private String key;

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping(value = "/registration")
    public String registrationForm(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }

        model.addAttribute("title", "Регистрация");
        model.addAttribute("UserRegistrationForm", new UserRegistrationForm());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationSubmit(@Valid @ModelAttribute("UserRegistrationForm") UserRegistrationForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("title", "Регистрация");

        String username = form.getUsername();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String invitationKey = form.getInvitationKey();

        if (userRepository.findByUsername(username).isPresent()) {
            bindingResult.rejectValue("username", "error.usernameTaken", "Имя пользователя уже используется");
            log.error("Имя пользователя {} уже используется.", username);
        }

        if (!password.equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.passwordMismatch", "Пароли не совпадают");
            log.error("Пароли не совпадают.");
        }

        if (!invitationKey.equals(key)) {
            bindingResult.rejectValue("invitationKey", "error.invitationKeyMismatch", "Неверный ключ");
            log.error("Неверный ключ приглашения");
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        try {
            userService.createUser(form);
            redirectAttributes.addFlashAttribute("registrationSuccess", "Регистрация прошла успешно! Войдите в систему.");
            return "redirect:/login";
        } catch (Exception ex) {
            log.error("Произошла ошибка при регистрации: {}", ex.getMessage());
            model.addAttribute("registrationError", "Произошла ошибка при регистрации.");
            return "registration";
        }
    }
}
