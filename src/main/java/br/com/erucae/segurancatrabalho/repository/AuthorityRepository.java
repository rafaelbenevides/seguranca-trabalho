package br.com.erucae.segurancatrabalho.repository;

import br.com.erucae.segurancatrabalho.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
