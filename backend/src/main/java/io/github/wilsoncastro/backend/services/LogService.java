package io.github.wilsoncastro.backend.services;

import io.github.wilsoncastro.backend.model.Log;
import io.github.wilsoncastro.backend.model.enums.TipoLog;
import io.github.wilsoncastro.backend.model.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Page<Log> getAll(String tipo, Pageable pageable) {
        return logRepository.buscarPorTipo("%"+tipo+"%", pageable);
    }

    public Log save(TipoLog tipo, String descricao) {
        Log log = new Log();

        log.setTipo(tipo.getDescricao());
        log.setDescricao(descricao);

        return logRepository.save(log);
    }

}
