package codelabz.mapper;

import codelabz.dto.TopicDetailResponse;
import codelabz.entity.TopicEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getDtoFromTopic(TopicEntity entity, @MappingTarget TopicDetailResponse dto);
}
