package com.satria.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satria.ecommerce.entity.Pengguna;
import com.satria.ecommerce.model.JwtResponse;
import com.satria.ecommerce.model.LoginRequest;
import com.satria.ecommerce.model.RefreshTokenRequest;
import com.satria.ecommerce.model.SignupRequest;
import com.satria.ecommerce.security.jwt.JwtUtils;
import com.satria.ecommerce.security.service.UserDetailsImpl;
import com.satria.ecommerce.security.service.UserDetailsServiceImpl;
import com.satria.ecommerce.service.PenggunaService;

@RestController
@RequestMapping("//auth")
public class AuthController {

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        PenggunaService penggunaService;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        private UserDetailsServiceImpl userDetailsServiceImpl;

        @Autowired
        JwtUtils jwtUtils;

        @PostMapping("/signin")
        public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                SecurityContextHolder.getContext()
                                .setAuthentication(authentication);
                String token = jwtUtils.generateJwtToken(authentication);
                String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
                UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
                return ResponseEntity.ok()
                                .body(new JwtResponse(token, refreshToken, principal.getUsername(),
                                                principal.getEmail(),
                                                principal.getRoles()));
        }

        @PostMapping("/signup")
        public Pengguna signup(@RequestBody SignupRequest request) {
                Pengguna pengguna = new Pengguna();
                pengguna.setId(request.getUsername());
                pengguna.setEmail(request.getEmail());
                pengguna.setPassword(passwordEncoder.encode(request.getPassword()));
                pengguna.setNama(request.getNama());
                pengguna.setRoles("user");
                Pengguna created = penggunaService.create(pengguna);
                return created;
        }

        @PostMapping("/refreshToken")
        public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
                String token = request.getRefreshToken();
                boolean valid = jwtUtils.validateJwtToken(token);
                if (!valid) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                String username = jwtUtils.getUserNameFromJwtToken(token);
                UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null,
                                userDetailsImpl.getAuthorities());
                String newToken = jwtUtils.generateJwtToken(authentication);
                String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
                return ResponseEntity.ok(new JwtResponse(newToken, refreshToken, username, userDetailsImpl.getEmail(),
                                userDetailsImpl.getRoles()));
        }
}
