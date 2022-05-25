package ru.otus.salebooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.salebooks.domain.Sale;

@RepositoryRestResource(path = "salebooks")
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
