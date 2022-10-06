package br.com.daysesoares.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.daysesoares.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
