package org.example.woowalearn.user.service;

import lombok.AllArgsConstructor;
import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.domain.ApplyForm;
import org.example.woowalearn.user.domain.FormStatus;
import org.example.woowalearn.user.dto.ApplyResponse;
import org.example.woowalearn.user.dto.ApplyTeacherRequest;
import org.example.woowalearn.user.repository.ApplyFormRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplyService {
    private final ApplyFormRepository applyFormRepository;

    public void applyForm(final long userId, final ApplyTeacherRequest request) {
        if (applyFormRepository.existsByUserId(userId)) {
            throw new WoowaLearnException(HttpStatus.BAD_REQUEST, "이미 등록한 지원서가 있습니다.");
        }
        final ApplyForm applyForm = toEntity(userId, request);
        applyFormRepository.save(applyForm);
    }

    public ApplyResponse findMyApplyForm(final long userId) {
        return toResponse(applyFormRepository.findByUserId(userId)
                .orElseThrow(()-> new WoowaLearnException(HttpStatus.BAD_REQUEST,"지원서가 없습니다.")));
    }

    private ApplyForm toEntity(final long userId, final ApplyTeacherRequest request) {
        return new ApplyForm(
                userId,
                request.contractName(),
                request.phoneNumber(),
                request.explainLink(),
                request.contactEmail(),
                request.applyReason(),
                FormStatus.PENDING
        );
    }

    private ApplyResponse toResponse(final ApplyForm applyForm) {
        return new ApplyResponse(
                applyForm.getApplyReason(),
                applyForm.getPhoneNumber().phoneNumber(),
                applyForm.getExplainLink(),
                applyForm.getContractName(),
                applyForm.getContactEmail().address(),
                applyForm.getFormStatus()
                        .name()
        );
    }
}
