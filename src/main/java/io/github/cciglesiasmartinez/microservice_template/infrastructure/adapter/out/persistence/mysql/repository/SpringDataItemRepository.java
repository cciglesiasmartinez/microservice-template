package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Esta interfaz extiende JpaRepository, esto recoge la implementación de JPA. Aquí se deben especificar los métodos
 * que posteriormente se implementarán en ItemRepositoryImpl, que implementará esta interfaz.
 */
@Repository
public interface SpringDataItemRepository extends JpaRepository<ItemEntity, String> {

    Optional<ItemEntity> findById(String id);

    @Query("SELECT i FROM ItemEntity i WHERE i.name = :name")
    boolean existsByName(@Param("name") String name);

}
