package com.example.project

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification

@SpringBootTest
abstract class AbstractIntegrationContainerBaseTest extends Specification{

    static final GenericContainer MY_REDIS_CONTAINER  // static을 이용해 test가 실행될 때 한번만 실행되도록 함.

    static {
        MY_REDIS_CONTAINER = new GenericContainer<>("redis:6")
            .withExposedPorts(6379) // 6379 -> docker의 포트 exposed ports -> testcontainer가 충돌되지 않는 임의의 포트를 생성해 해당 포트와 매핑

        MY_REDIS_CONTAINER.start()

        System.setProperty("spring.redis.host", MY_REDIS_CONTAINER.getHost())
        System.setProperty("spring.redis.port", MY_REDIS_CONTAINER.getMappedPort(6379).toString()) // 스프링이 통신할 수 있도록 정보전달
    }

}
