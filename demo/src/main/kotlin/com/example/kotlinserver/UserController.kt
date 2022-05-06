package com.example.kotlinserver

import com.example.entity.UserInfo
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

@RestController
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class UserController {
    private val userMap: MutableMap<String, UserInfo> = mutableMapOf()

    @PostConstruct
    fun init() {
        userMap["1"] = UserInfo("1", "한승연", "123-1234", "서울")
        userMap["2"] = UserInfo("2", "승연", "1234-2345", "공릉")
        userMap["3"] = UserInfo("3", "승연2", "1234-23456", "공릉2")
        userMap["4"] = UserInfo("4", "승연3", "1234-23457", "공릉3")
    }

    @GetMapping(path = ["/user/{id}"])
    fun getUserInfo(@PathVariable("id") id: String) = userMap[id]

    @GetMapping(path = ["user/all"])
    fun getUserInfoAll() = ArrayList<UserInfo>(userMap.values)

//    @PostMapping(path = ["/user/{id}"])
//    fun postUserInfo(
//        @PathVariable("id") id: String,
//        @RequestParam("name") name: String,
//        @RequestParam("phone") phone: String,
//        @RequestParam("address") address: String
//    ) {
//        val userInfo = userMap[id]
//        userInfo?.name = name
//        userInfo?.phone = phone
//        userInfo?.address = address
//    }
}