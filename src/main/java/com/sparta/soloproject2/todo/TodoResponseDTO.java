package com.sparta.soloproject2.todo;

import com.sparta.soloproject2.CommonResponseDTO;
import com.sparta.soloproject2.user.UserDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TodoResponseDTO extends CommonResponseDTO {
	private Long id;
	private String title;
	private String content;
	private Boolean isCompleted;
	private UserDTO user;
	private LocalDateTime createDate;

	public TodoResponseDTO(Todo todo) {
		this.id = todo.getId();
		this.title = todo.getTitle();
		this.content = todo.getContent();
		this.isCompleted = todo.getIsCompleted();
		this.user = new UserDTO(todo.getUser());
		this.createDate = todo.getCreateDate();
	}
}
