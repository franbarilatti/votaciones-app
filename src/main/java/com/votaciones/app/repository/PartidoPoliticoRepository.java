package com.votaciones.app.repository;

import com.votaciones.app.model.PartidoPolitico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoPoliticoRepository extends JpaRepository<PartidoPolitico,Long> {
}
