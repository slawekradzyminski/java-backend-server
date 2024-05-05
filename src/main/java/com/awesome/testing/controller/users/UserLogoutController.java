package com.awesome.testing.controller.users;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.awesome.testing.security.JwtTokenUtil;
import com.awesome.testing.security.TokenBlacklistService;

import static com.awesome.testing.util.TokenCookieUtil.buildTokenCookie;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserLogoutController {

    private final JwtTokenUtil jwtTokenUtil;
    private final TokenBlacklistService tokenBlacklistService;

    @Operation(summary = "Logouts customer by expiring the HttpOnly cookie")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = jwtTokenUtil.extractTokenFromRequest(request);
        if (token != null) {
            tokenBlacklistService.addToBlacklist(token);
        }

        response.addHeader("Set-Cookie", buildTokenCookie(null, 0));
        return ResponseEntity.ok().build();
    }
}
