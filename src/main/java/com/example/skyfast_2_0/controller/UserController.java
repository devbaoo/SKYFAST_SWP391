package com.example.skyfast_2_0.controller;

import com.example.skyfast_2_0.dto.UserDTO;
import com.example.skyfast_2_0.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userlist")
    public String getAllUsers(Model model) {
        List<UserDTO> users = userService.getAllActiveAndInactiveUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("user/detail/{id}")
    public String getUserDetail(@PathVariable Integer id, Model model) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "UserDetail";
        }
        return "redirect:/manager/userlist";
    }

    @PostMapping("/create/user")
    public String createUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(userDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create user: " + e.getMessage());
        }
        return "redirect:/manager/userlist";
    }

    @PostMapping("user/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        System.out.println("Received dateOfBirth: " + userDTO.getDateOfBirth()); // Kiểm tra dữ liệu nhận được

        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            if (updatedUser != null) {
                redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update user: " + e.getMessage());
        }
        return "redirect:/manager/userlist";
    }


    @PostMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            if (userService.deleteUser(id)) {
                redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "User not found!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete user: " + e.getMessage());
        }
        return "redirect:/manager/userlist";
    }
}
