package io.github.wilsoncastro.backend.rest.logs;

import io.github.wilsoncastro.backend.model.Log;
import io.github.wilsoncastro.backend.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin("*")
public class LogController {

    @Autowired
    private LogService service;

    @GetMapping
    public Page<LogFormRequest> index(
        @RequestParam(value = "tipo", required = false, defaultValue = "") String tipo,
        Pageable pageable
    ) {
        return service
                .getAll(tipo, pageable)
                .map( LogFormRequest::fromModel );
    }
}
