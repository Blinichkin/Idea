package ru.urfu.idea.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttachmentResponse implements Serializable {

	private long id;

	private String name;

	private LocalDateTime createdDate;

}
