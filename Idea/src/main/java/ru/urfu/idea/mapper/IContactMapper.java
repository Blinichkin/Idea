package ru.urfu.idea.mapper;

import org.mapstruct.Mapper;
import ru.urfu.idea.model.Contact;
import ru.urfu.idea.request.ContactRequest;

@Mapper(componentModel = "spring")
public interface IContactMapper {
	
	Contact requestToModel(ContactRequest contactRequest);
	
}
