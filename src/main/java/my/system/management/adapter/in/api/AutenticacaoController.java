package my.system.management.adapter.in.api;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import my.system.management.domain.usuario.dto.DadosAutenticacao;
import my.system.management.domain.usuario.model.Usuario;
import my.system.management.infra.security.DadosTokenJWT;
import my.system.management.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());

            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }

}