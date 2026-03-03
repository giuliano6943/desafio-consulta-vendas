package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Locale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT obj " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :dateInicial and :dateFinal " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name,'%')) "
    )
    Page<Sale> searchBySale(LocalDate dateInicial ,LocalDate dateFinal, String name,  Pageable pageable);


    @Query("SELECT obj.seller.name AS sellerName, SUM(obj.amount) AS total " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate and :maxDate " +
            "GROUP BY obj.seller.name ")
    Page<SaleSummaryProjection> summaryBySeller(LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
