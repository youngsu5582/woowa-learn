package org.example.woowalearn.user.controller;

import lombok.AllArgsConstructor;
import org.example.woowalearn.user.domain.UserPrincipal;
import org.example.woowalearn.user.dto.ApplyChangeRequest;
import org.example.woowalearn.user.service.ApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final ApplyService applyService;

    @PostMapping("/apply/approve")
    public ResponseEntity<Void> approveApply(@AuthenticationPrincipal final UserPrincipal userPrincipal, @RequestBody final ApplyChangeRequest request) {
        applyService.approveApply(userPrincipal.getUserId(), request);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/apply/deny")
    public ResponseEntity<Void> approveDeny(@AuthenticationPrincipal final UserPrincipal userPrincipal, @RequestBody final ApplyChangeRequest request) {
        applyService.denyApply(userPrincipal.getUserId(), request);
        return ResponseEntity.ok()
                .build();
    }
}
