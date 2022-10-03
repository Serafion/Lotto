//package pl.lotto.infrastructure.database.migration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//@Configuration
//public class MongockConfiguration {
//
//    @Bean
//    public MongoDbFactory mongoDbFactory() {
//        return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.uri")));
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//
//        return mongoTemplate;
//
//    }
//
//
//}
