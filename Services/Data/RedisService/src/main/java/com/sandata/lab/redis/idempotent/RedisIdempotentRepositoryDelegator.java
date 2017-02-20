//package com.sandata.lab.redis.idempotent;
//
//import org.apache.camel.component.redis.processor.idempotent.RedisIdempotentRepository;
//import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * A wrapper class for {@link RedisIdempotentRepository} to have a new
// * constructor accepting RedisTemplate without type arguments as we will get
// * below error if we inject an instance of {@link RedisTemplate} to
// * {@link RedisIdempotentRepository}'s constructor in blueprint.xml
// *
// * </p>
// * Error: org.osgi.service.blueprint.container.ComponentDefinitionException:
// * Unable to find a matching constructor on class
// * org.apache.camel.component.redis.processor.idempotent.
// * RedisIdempotentRepository for arguments
// *
// */
//public class RedisIdempotentRepositoryDelegator extends RedisIdempotentRepository {
//
//    @SuppressWarnings("unchecked")
//    public RedisIdempotentRepositoryDelegator(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate, String processorName) {
//        super(redisTemplate, processorName);
//    }
//
//}
