package com.productservice.productservice.inheritancerelations.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_tarepository")
public interface TARepository extends JpaRepository<TA,Long> {
    @Override
    <T extends TA> T save(T t);
}
