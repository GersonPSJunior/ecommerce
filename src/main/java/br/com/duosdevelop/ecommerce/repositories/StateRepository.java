package br.com.duosdevelop.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.duosdevelop.ecommerce.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
