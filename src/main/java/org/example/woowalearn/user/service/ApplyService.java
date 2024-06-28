package org.example.woowalearn.user.service;

import lombok.AllArgsConstructor;
import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.domain.ApplyForm;
import org.example.woowalearn.user.domain.FormStatus;
import org.example.woowalearn.user.domain.User;
import org.example.woowalearn.user.dto.ApplyChangeRequest;
import org.example.woowalearn.user.dto.ApplyResponse;
import org.example.woowalearn.user.dto.ApplyTeacherRequest;
import org.example.woowalearn.user.repository.ApplyFormRepository;
import org.example.woowalearn.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplyService {
    private final ApplyFormRepository applyFormRepository;
    private final UserRepository userRepository;

    public void applyForm(final long userId, final ApplyTeacherRequest request) {
        if (applyFormRepository.existsByUserId(userId)) {
            throw new WoowaLearnException(HttpStatus.BAD_REQUEST, "이미 등록한 지원서가 있습니다.");
        }
        final ApplyForm applyForm = toEntity(userId, request);
        applyFormRepository.save(applyForm);
    }

    public ApplyResponse findApplyFormWithUserId(final long userId) {
        return toResponse(applyFormRepository.findByUserId(userId)
                .orElseThrow(() -> new WoowaLearnException(HttpStatus.BAD_REQUEST, "지원서가 없습니다.")));
    }

    public ApplyResponse findApplyFormWithId(final long id) {
        return toResponse(applyFormRepository.getByIdOrThrow(id));
    }

    public List<ApplyResponse> getApplyForm(final int page, final int size) {
        return applyFormRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ApplyResponse approveApply(final long adminId, final ApplyChangeRequest request) {
        validateAdmin(adminId);
        final ApplyForm form = applyFormRepository.getByIdOrThrow(request.applyId());
        form.approve(request.judgeDetail(), adminId);
        return toResponse(form);
    }

    @Transactional
    public ApplyResponse denyApply(final long adminId, final ApplyChangeRequest request) {
        validateAdmin(adminId);
        final ApplyForm form = applyFormRepository.getByIdOrThrow(request.applyId());
        form.deny(request.judgeDetail(), adminId);
        return toResponse(form);
    }

    private void validateAdmin(final long userId) {
        final User user = userRepository.getByIdOrThrow(userId);
        if (user.isMember()) {
            throw new WoowaLearnException(HttpStatus.BAD_REQUEST, "운영자만 승인 가능합니다.");
        }
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
                applyForm.getId(),
                applyForm.getApplyReason(),
                applyForm.getPhoneNumber()
                        .phoneNumber(),
                applyForm.getExplainLink(),
                applyForm.getContractName(),
                applyForm.getContactEmail()
                        .address(),
                applyForm.getFormStatus()
                        .name()
        );
    }
}
