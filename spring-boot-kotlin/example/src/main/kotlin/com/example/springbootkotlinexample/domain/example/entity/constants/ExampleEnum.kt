package com.example.springbootkotlinexample.domain.example.entity.constants

enum class ExampleEnum {
    /**
     * * 해당 권한 아이디는 한개만 갖는다.
     * * 아래 레벨 권한을 핸들링 할 수 있다.
     * @property MASTER 최고 관리자 권한
     */
    MASTER,

    /**
     * * 해당 권한 아이디는 관리자만 갖는다.
     * * 아래 레벨 권한을 핸들링 할 수 있다.
     * @property ADMIN 관리자 권한
     */
    ADMIN,

    /**
     * * 해당 권한 아이디는 매니저만 갖는다.
     * * 매니저는 각 부서 팀장 급이다.
     * * 아래 레벨 권한을 핸들링 할 수 있다.
     * @property MANAGER 매니저 권한
     */
    MANAGER,

    /**
     * @property NORMAL 기본 권한
     */
    NORMAL,
}
