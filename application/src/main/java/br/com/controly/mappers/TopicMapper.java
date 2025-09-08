package br.com.controly.mappers;

import br.com.controly.viewmodels.TopicDetailViewModel;
import br.com.controly.domain.entities.Topics;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getDtoFromTopic(Topics entity, @MappingTarget TopicDetailViewModel dto);
}
