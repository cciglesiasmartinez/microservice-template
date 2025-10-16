package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

/**
 * Esta es la clase de entidad de persistencia, que es diferente de la de entidad de dominio. Esto representa una
 * entidad en la base de datos, por lo que los atributos son primitivos o clases estándar (como String). Las entidades
 * no contienen ningún tipo de lógica de dominio, salvo algunos detalles como la longitud (length=60) de algún campo
 * o similares.
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "items")
public class ItemEntity implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Column(length = 60)
    private String name;

}
