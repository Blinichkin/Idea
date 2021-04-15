package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.Cost;
import ru.urfu.idea.mapper.ICostMapper;
import ru.urfu.idea.mapper.request.CostRequest;
import ru.urfu.idea.security.UserPrincipal;
import ru.urfu.idea.service.ICostService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/cost")
public class CostController {
	
	private final ICostService costService;
	private final ICostMapper costMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Cost> create(@RequestBody @Validated final CostRequest costRequest) {
		Cost cost = costMapper.requestToModel(costRequest);
		Cost createdCost = costService.create(cost);
		
		return new ResponseEntity<>(createdCost, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN') || (hasAuthority('USER') && #id == #userPrincipal.id)")
	public ResponseEntity<Cost> update(@AuthenticationPrincipal UserPrincipal userPrincipal,
										  @PathVariable("id") final long id,
										  @RequestBody @Validated final CostRequest costRequest) {
		Cost cost = costMapper.requestToModel(costRequest);
		Cost updatedCost = costService.update(id, cost);
		
		return new ResponseEntity<>(updatedCost, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<Cost>> getAll() {
		Collection<Cost> cost = costService.findAll();
		
		return new ResponseEntity<>(cost, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cost> getById(@PathVariable("id") final long id) {
		Cost cost = costService.findById(id);
		if (cost == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(cost, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Cost> delete(@PathVariable("id") final long id) {
		Cost cost = costService.delete(id);
		if (cost == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
