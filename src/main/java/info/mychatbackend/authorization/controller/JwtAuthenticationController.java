package info.mychatbackend.authorization.controller;

import info.mychatbackend.authorization.exception.AuthenticationException;
import info.mychatbackend.authorization.helper.JwtTokenHelper;
import info.mychatbackend.authorization.model.JwtTokenRequest;
import info.mychatbackend.authorization.model.JwtTokenResponse;
import info.mychatbackend.authorization.service.LogoutService;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chat.systemUser.service.ChatSystemUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {

    @Value("${jwt.http.request.header}")
    private String tokenHeader;
    private AuthenticationManager authenticationManager;
    private JwtTokenHelper jwtTokenHelper;
    private UserDetailsService userDetailsService;
    private ChatSystemUserService userService;
    private LogoutService logoutService;

    public JwtAuthenticationController(
            AuthenticationManager authenticationManager, JwtTokenHelper jwtTokenHelper,
            UserDetailsService userDetailsService, ChatSystemUserService userService,
            LogoutService logoutService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.logoutService = logoutService;
    }

    @RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
    public ResponseEntity createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        ChatSystemUser user = userService.getByUsername(userDetails.getUsername());
        final String token = jwtTokenHelper.generateToken(userDetails);

        return ResponseEntity.ok(new JwtTokenResponse(token, user));
    }

    @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
    public ResponseEntity refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);

        if (jwtTokenHelper.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenHelper.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity logout() {
        return new ResponseEntity<>(logoutService.logout(), HttpStatus.OK);
    }


}
