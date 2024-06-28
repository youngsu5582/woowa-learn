package org.example.woowalearn.user.controller;

import lombok.AllArgsConstructor;
import org.example.woowalearn.user.domain.UserPrincipal;
import org.example.woowalearn.user.dto.ApplyChangeRequest;
import org.example.woowalearn.user.dto.ApplyResponse;
import org.example.woowalearn.user.service.ApplyService;
import org.example.woowalearn.user.service.EmailSender;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final ApplyService applyService;
    private final EmailSender emailSender;

    @PostMapping("/apply/approve")
    public ResponseEntity<Void> approveApply(@AuthenticationPrincipal final UserPrincipal userPrincipal, @RequestBody final ApplyChangeRequest request) {
        final ApplyResponse result = applyService.approveApply(userPrincipal.getUserId(), request);
        emailSender.sendApplyJudgeMail(result.contractName(),result.contactEmail(),result.applyStatus());
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/apply/deny")
    public ResponseEntity<Void> approveDeny(@AuthenticationPrincipal final UserPrincipal userPrincipal, @RequestBody final ApplyChangeRequest request) {
        final ApplyResponse result = applyService.denyApply(userPrincipal.getUserId(), request);
        emailSender.sendApplyJudgeMail(result.contractName(),result.contactEmail(),result.applyStatus());
        return ResponseEntity.ok()
                .build();
    }
}
