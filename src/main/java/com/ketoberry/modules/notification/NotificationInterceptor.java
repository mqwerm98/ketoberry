package com.ketoberry.modules.notification;

import com.ketoberry.modules.account.entity.Account;
import com.ketoberry.modules.account.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
// 컨트롤러에 들어오는 요청(HttpRequest)와 응답(HttpResponse)를 가로채는 역할
// preHandle : 컨트롤러 호출 전
// postHandle : 컨트롤러 호출 후(Spring MVC의 Dispatcher Servlet이 화면을 처리하기 전에 동작)
// afterCompletion : 뷰 등 모든 일 완료 후(Dispatcher Servlet의 화면 처리가 완료된 이후)
public class NotificationInterceptor implements HandlerInterceptor {

    private final NotificationRepository notificationRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // modelAndView가 있고, redirect가 아니고(redirect는 다시 호출할때 처리하면 되므로), 인증 상태 일 때
        // 보여줄 알람이 있으면 modelAndView에 param을 추가해준다
        if (modelAndView != null && !isRedirectView(modelAndView) && authentication != null && authentication.getPrincipal() instanceof UserAccount) {
            Account account = ((UserAccount) authentication.getPrincipal()).getAccount();
            long count = notificationRepository.countByAccountAndChecked(account, false);
            modelAndView.addObject("hasNotification", count < 0);
        }
    }

    private boolean isRedirectView(ModelAndView modelAndView) {
        return modelAndView.getViewName().startsWith("redirect:") || modelAndView.getView() instanceof RedirectView;
    }
}
