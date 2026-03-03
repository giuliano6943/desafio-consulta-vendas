package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleMinDTO>> getReport(
            @RequestParam(name = "minDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateInicial,
            @RequestParam(name = "maxDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dateFinal,
            @RequestParam(name = "name", required = false) String name,
            Pageable pageable) {

        Page<SaleMinDTO> dto = service.findSales(dateInicial, dateFinal, name, pageable);
        return ResponseEntity.ok(dto);
    }

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSummaryProjection>> getSummary(
            @RequestParam(name = "minDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam(name = "maxDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate maxDate,
            Pageable pageable
    ) {
        Page<SaleSummaryProjection> projections = service.summaryBySeller(minDate,maxDate,pageable);
        return ResponseEntity.ok(projections);
	}
}
