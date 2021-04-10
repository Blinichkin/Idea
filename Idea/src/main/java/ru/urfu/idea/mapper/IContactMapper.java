package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.entity.Contact;
import ru.urfu.idea.mapper.request.ContactRequest;

@Mapper(componentModel = "spring")
public interface IContactMapper {
	
	Contact requestToModel(ContactRequest contactRequest);
	
}
