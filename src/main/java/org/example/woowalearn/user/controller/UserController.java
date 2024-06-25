package org.example.woowalearn.user.controller;

import lombok.AllArgsConstructor;
import org.example.woowalearn.user.domain.UserPrincipal;
import org.example.woowalearn.user.dto.ApplyTeacherRequest;
import org.example.woowalearn.user.service.ApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Controller
@AllArgsConstructor
public class UserController {
    ApplyService applyService;

    @PostMapping("/user/apply")
    public ResponseEntity<Void> applyTeacher(@AuthenticationPrincipal final UserPrincipal userPrincipal,
                                             @RequestBody final ApplyTeacherRequest request) {
        applyService.applyForm(userPrincipal.getUserId(), request);
        return ResponseEntity.created(URI.create("/user/apply"))
                .build();
    }
}
