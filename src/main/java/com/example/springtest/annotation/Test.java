package com.example.springtest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target : 어느테이션이 생성될 수 있는 위치를 지정하는 어노테이션
//PACKAGE : 패키시 선언시 / TYPE : 타입 선언시 / CONSTRUCTOR : 생성자 선언시
//FIELD : enum상수를 포함한 멤버변수 선언시 / METHOD : 메소드 선언시
//ANNOTATION_TYPE : 어노테이션 타입 선언시 / LOCAL_VARIABLE : 지역변수 선언시
// PARAMETER : 파라미터 선언시 / TYPE_PARAMETER : 파라미터 타입 선언시
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
}

/** @Target
 * 설명 : 어느테이션이 생성될 수 있는 위치를 지정하는 어노테이션
 * PACKAGE : 패키시 선언시 / TYPE : 타입 선언시 / CONSTRUCTOR : 생성자 선언시
 * FIELD : enum상수를 포함한 멤버변수 선언시 / METHOD : 메소드 선언시
 * ANNOTATION_TYPE : 어노테이션 타입 선언시 / LOCAL_VARIABLE : 지역변수 선언시
 * PARAMETER : 파라미터 선언시 / TYPE_PARAMETER : 파라미터 타입 선언시
 */
/**
 * @Retantion
 * 설명 : 어노트에시녀이 언제까지 유효할지 정하는 어노테이션 입니다.(어노테이션 지속시간)
 * RUNTIME : 컴파일 이후에도 참조 가능(실행 동안은 계속 유효함)
 * CLASS : 클래스 참조할 때까지 유효
 * SOURCE : 컴파일 이후 어노테이션 정보 소멸
 * */
/**
 * @Documented
 * 설명 : Java doc에 문서화 여부를 결정합니다.
 * */
