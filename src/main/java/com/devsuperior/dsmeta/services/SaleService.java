package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
    @Transactional(readOnly = true)
    public Page<SaleMinDTO> findSales(LocalDate dateInicial , LocalDate dateFinal, String name, Pageable pageable) {
        if(dateFinal == null){
            dateFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()); //Instante exato do sistema
        }
        if(dateInicial == null){
            dateInicial = dateFinal.minusYears(1L); //1 ano antes da final
        }
        if(name == null){
            name = "";
        }

        Page<Sale> result = repository.searchBySale(dateInicial,dateFinal,name,pageable);
        return  result.map(SaleMinDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<SaleSummaryProjection> summaryBySeller(LocalDate minDate , LocalDate maxDate, Pageable pageable) {
        if(maxDate == null){
            maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()); //Instante exato do sistema
        }
        if(minDate == null){
            minDate = maxDate.minusYears(1L); //1 ano antes da final
        }
        System.out.println("minDate: " + minDate + ", maxDate: " + maxDate);

        return  repository.summaryBySeller(minDate,maxDate,pageable);
    }
}
