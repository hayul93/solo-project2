package com.sparta.soloproject2.comment;

import com.sparta.soloproject2.CommonResponseDto;
import com.sparta.soloproject2.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentResponseDTO extends CommonResponseDto {
	private Long id;
	private String text;
	private UserDTO user;
	private LocalDateTime createDate;

	public CommentResponseDTO(Comment comment) {
		this.id = comment.getId();
		this.text = comment.getText();
		this.user = new UserDTO(comment.getUser());
		this.createDate = comment.getCreateDate();
	}
}