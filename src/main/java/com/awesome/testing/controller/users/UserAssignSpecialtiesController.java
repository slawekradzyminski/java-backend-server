package com.awesome.testing.controller.users;

import com.awesome.testing.controller.utils.authorization.OperationWithSecurity;
import com.awesome.testing.controller.utils.authorization.PreAuthorizeForDoctorAndAdmin;
import com.awesome.testing.dto.specialty.SpecialtiesUpdateDto;
import com.awesome.testing.dto.users.UserResponseDto;
import com.awesome.testing.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAssignSpecialtiesController {

    private final UserService userService;

    @PreAuthorizeForDoctorAndAdmin
    @OperationWithSecurity(summary = "Assign specialties to doctor")
    @PutMapping("/specialties")
    public UserResponseDto updateSPECIALTYIES(@RequestBody SpecialtiesUpdateDto specialtiesUpdateDto) {
        return userService.updateSpecialties(specialtiesUpdateDto.getSpecialtyIds());
    }
}