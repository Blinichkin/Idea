package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.CostType;
import ru.urfu.idea.mapper.ICostTypeMapper;
import ru.urfu.idea.mapper.request.CostTypeRequest;
import ru.urfu.idea.service.ICostTypeService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/costTypes")
public class CostTypeController {
	
	private final ICostTypeService costTypeService;
	private final ICostTypeMapper costTypeMapper;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CostType> create(@RequestBody @Validated final CostTypeRequest typeRequest) {
		CostType type = costTypeMapper.requestToModel(typeRequest);
		CostType createdType = costTypeService.create(type);
		
		return new ResponseEntity<>(createdType, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CostType> update(@PathVariable("id") final long id,
										   @RequestBody @Validated final CostTypeRequest typeRequest) {
		CostType type = costTypeMapper.requestToModel(typeRequest);
		CostType updatedType = costTypeService.update(id, type);
		
		return new ResponseEntity<>(updatedType, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<CostType>> getAll() {
		Collection<CostType> types = costTypeService.findAll();
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CostType> getById(@PathVariable("id") final long id) {
		CostType type = costTypeService.findById(id);
		if (type == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(type, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CostType> delete(@PathVariable("id") final long id) {
		CostType costType = costTypeService.delete(id);
		if (costType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
