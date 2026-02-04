package br.com.forumhub.controller;

import br.com.forumhub.domain.Usuario;
import br.com.forumhub.dto.LoginRequestDTO;
import br.com.forumhub.dto.TokenResponseDTO;
import br.com.forumhub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
            var authentication = authenticationManager.authenticate(authToken);

            var usuario = (Usuario) authentication.getPrincipal();
            String token = tokenService.gerarToken(usuario);

            return new TokenResponseDTO(token);

        } catch (AuthenticationException e) {
            // Por padrão, Spring retornaria 401. Aqui devolvemos 401 também.
            throw new RuntimeException("Login ou senha inválidos");
        }
    }
}
