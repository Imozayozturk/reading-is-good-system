package com.imozayozturk.readingisgood.controller;

import com.imozayozturk.readingisgood.configuration.security.JwtTokenUtil;
import com.imozayozturk.readingisgood.model.request.JwtRequest;
import com.imozayozturk.readingisgood.model.response.JwtResponse;
import com.imozayozturk.readingisgood.service.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/authenticate")
public class JwtAuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  /**
   * Token creation endpoint.
   *
   * @param authenticationRequest JwtRequest type parameter.
   * @return Token within ResponseEntity.
   * @throws Exception in generic.
   */
  @PostMapping
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {
    userDetailsService
        .authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token));
  }


}