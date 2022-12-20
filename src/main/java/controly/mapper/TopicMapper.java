package controly.mapper;

import controly.dto.SimplifiedTopicResponse;
import controly.dto.TopicDetailResponse;
import controly.dto.UpdateUsersInfoRequest;
import controly.entity.TopicEntity;
import controly.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void getDtoFromTopic(TopicEntity entity, @MappingTarget TopicDetailResponse dto);
}
