package com.example.common

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.sql.SQLException
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class DatabaseConfiguration{
    @PersistenceContext
    private val entitiyManager: EntityManager? = null

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory? {
        return JPAQueryFactory(entitiyManager)
    }
}

@Configuration
class H2ServerConfig{
    /**
    *  H2 외부 원격 접속을 위한 세팅
     *  @return
     *  @throws SQLException
    * */
    @Bean
    @Throws(SQLException::class)
    fun h2TcpServer(): org.h2.tools.Server? {
        return org.h2.tools.Server.createTcpServer().start()
    }
}

/**
 * JWT 설정값
 */
@Component
@Configuration
class SercurityProperties(
    @Value("\${security.jwt.secret-key}")
    var secretKey: String?= null,
    @Value("\${security.jwt.expire-time.access-token}")
    val expireTime: Long? = null,
    val authKey: String? = null
)