package ru.urfu.idea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.urfu.idea.entity.User;
import ru.urfu.idea.mapper.IUserMapper;
import ru.urfu.idea.mapper.request.UserRegister;
import ru.urfu.idea.mapper.response.UserResponse;
import ru.urfu.idea.service.IUserService;

import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final IUserService userService;
	private final IUserMapper userMapper;
	
	@PostMapping
	public ResponseEntity<UserResponse> create(@RequestBody @Validated final UserRegister userRegister) {
		User user = userMapper.requestToModel(userRegister);
		User createdUser = userService.create(user);
		UserResponse userResponse = userMapper.modelToResponse(createdUser);
		
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable("id") final long id,
											   @RequestBody @Validated final User user) {
		User updatedUser = userService.update(id, user);
		UserResponse userResponse = userMapper.modelToResponse(updatedUser);
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Collection<UserResponse>> getAll() {
		Collection<User> users = userService.findAll();
		Collection<UserResponse> userResponses = userMapper.modelToResponse(users);
		
		return new ResponseEntity<>(userResponses, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getById(@PathVariable("id") final long id) {
		User user = userService.findById(id);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		UserResponse userResponse = userMapper.modelToResponse(user);
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final long id) {
		User user = userService.findById(id);
		
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
