package com.productservice.productservice.inheritancerelations.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_tarepository")
public interface TARepository extends JpaRepository<TA, Long> {
    @Override
    <T extends TA> T save(T t);
}
