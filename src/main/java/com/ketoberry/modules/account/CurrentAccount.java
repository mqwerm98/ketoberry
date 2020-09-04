package com.ketoberry.modules.account;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // runtime까지도 유지가 돼야함
@Target(ElementType.PARAMETER) // parameter에만 붙일 수 있게
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
//해당 param 값이 anonymouseUser인 경우 null을 넣는다
public @interface CurrentAccount {
}
