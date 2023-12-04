package com.sparta.soloproject2.todo;

import com.sparta.soloproject2.user.CommonResponseDto;
import com.sparta.soloproject2.user.UserDTO;
import com.sparta.soloproject2.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@PostMapping
	public ResponseEntity<TodoResponseDTO> postTodo(@RequestBody TodoRequestDTO todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		TodoResponseDTO responseDTO = todoService.createTodo(todoRequestDTO, userDetails.getUser());

		return ResponseEntity.status(201).body(responseDTO);
	}

	@GetMapping("/{todoId}")
	public ResponseEntity<CommonResponseDto> getTodo(@PathVariable Long todoId) {
		try {
			TodoResponseDTO responseDTO = todoService.getTodoDto(todoId);
			return ResponseEntity.ok().body(responseDTO);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

	@GetMapping
	public ResponseEntity<List<TodoListResponseDTO>> getTodoList() {
		List<TodoListResponseDTO> response = new ArrayList<>();

		Map<UserDTO, List<TodoResponseDTO>> responseDTOMap = todoService.getUserTodoMap();

		responseDTOMap.forEach((key, value) -> response.add(new TodoListResponseDTO(key, value)));

		return ResponseEntity.ok().body(response);
	}


	@PutMapping("/{todoId}")
	public ResponseEntity<CommonResponseDto> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		try {
			TodoResponseDTO responseDTO = todoService.updateTodo(todoId, todoRequestDTO, userDetails.getUser());
			return ResponseEntity.ok().body(responseDTO);
		} catch (RejectedExecutionException | IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}


	@PatchMapping("/{todoId}/complete")
	public ResponseEntity<CommonResponseDto> patchTodo(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		try {
			TodoResponseDTO responseDTO = todoService.competeTodo(todoId, userDetails.getUser());
			return ResponseEntity.ok().body(responseDTO);
		} catch (RejectedExecutionException | IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}
}
