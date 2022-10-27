package io.github.wilsoncastro.backend.rest.usuarios;

import io.github.wilsoncastro.backend.model.Usuario;
import io.github.wilsoncastro.backend.model.repository.UsuarioRepository;
import io.github.wilsoncastro.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioFormRequest> salvar(@RequestBody UsuarioFormRequest request) {
        Usuario usuario = request.toMoldel();

        return ResponseEntity.ok(UsuarioFormRequest.fromModel(service.save(usuario)));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(
        @PathVariable Long id,
        @RequestBody UsuarioFormRequest request
    ) {

        Usuario usuario = request.toMoldel();
        usuario.setId(id);
        service.save(usuario);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioFormRequest> getById(@PathVariable Long id){
        Usuario usuario = service.getById(id);
        return ResponseEntity.ok().body(UsuarioFormRequest.fromModel(usuario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<UsuarioFormRequest> getLista(
        @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
        @RequestParam(value = "cpf", required = false, defaultValue = "") String cpf,
        @RequestParam(value = "rg", required = false, defaultValue = "") String rg,
        Pageable pageable
    ){
        return service
                .getAll(nome, cpf, rg, pageable)
                .map( UsuarioFormRequest::fromModel );
    }
}
