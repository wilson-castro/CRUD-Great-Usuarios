package io.github.wilsoncastro.backend.services;

import io.github.wilsoncastro.backend.model.Usuario;
import io.github.wilsoncastro.backend.model.enums.TipoLog;
import io.github.wilsoncastro.backend.model.repository.UsuarioRepository;
import io.github.wilsoncastro.backend.services.exceptions.AlreadyExistsException;
import io.github.wilsoncastro.backend.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private  LogService logService;

    public Usuario getById(Long id){

        logService.save(TipoLog.SELECT, "Busca de um registro de usuário por ID = "+id);

        return usuarioRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Não foi encontrado nenhum registro com esse ID"));
    }

    public Page<Usuario> getAll(String nome, String cpf, String rg, Pageable pageable){

        String camposBusca = "Busca de usuários por campos [";

        if (!nome.isBlank())  camposBusca += " NOME = "+nome+",";
        if (!rg.isBlank())    camposBusca += " RG = "+rg+",";
        if (!cpf.isBlank())   camposBusca += " CPF = "+cpf+",";

        camposBusca = camposBusca.equals("Busca de usuários por campos [") ? "Busca geral": camposBusca.substring(0, camposBusca.length() - 1) + " ]";

        logService.save(TipoLog.SELECT, camposBusca);

        return usuarioRepository.buscarPorNomeCpfRg( "%"+nome+"%", "%"+cpf+"%", "%"+rg+"%", pageable);
    }
    public Usuario save(Usuario usuario) {
        if(usuario.getId() != null) {
            return usuarioRepository.findById(usuario.getId())
                    .map( usuarioExistente -> {
                        boolean cpfNaoAlterado = usuarioExistente.getCpf().equals(usuario.getCpf());
                        boolean rgNaoAlterado = usuarioExistente.getRg().equals(usuario.getRg());

                        if( !cpfNaoAlterado && usuarioRepository.existsByCpf(usuario.getCpf()))
                            throw new AlreadyExistsException("Já existe um usuário com esse CPF.");
                        if( !rgNaoAlterado && usuarioRepository.existsByRg(usuario.getRg()))
                            throw new AlreadyExistsException("Já existe um usuário com esse RG.");

                        logService.save(TipoLog.UPDATE, "Alteração do usuário de ID:  "+usuario.getId());

                        return usuarioRepository.save(usuario);
                    })
                    .orElseThrow( () -> new EntityNotFoundException("Não foi encontrado um registro com esse ID"));
        }

        if(usuarioRepository.existsByCpf(usuario.getCpf()))
            throw new AlreadyExistsException("Já existe um usuário com esse CPF.");

        if(usuarioRepository.existsByRg(usuario.getRg()))
            throw new AlreadyExistsException("Já existe um usuário com esse RG.");

        usuarioRepository.save(usuario);

        logService.save(TipoLog.INSERT, "Criação do usuário de ID:  "+usuario.getId());

        return usuario;
    }

    public void delete(Long id) {
        Usuario usuario = this.getById(id);

        logService.save(TipoLog.DELETE, "Deleção do usuário de ID: "+usuario.getId());

        usuarioRepository.delete(usuario);
    }
}
