package br.com.cardif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cardif.eao.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
