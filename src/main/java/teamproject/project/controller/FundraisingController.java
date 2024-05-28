package teamproject.project.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamproject.project.dto.fundraising.CreateFundraisingRequestDto;
import teamproject.project.dto.fundraising.FundraisingDto;
import teamproject.project.model.User;
import teamproject.project.service.FundraisingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fundraisings")
public class FundraisingController {
    private final FundraisingService fundraisingService;

    @GetMapping
    public List<FundraisingDto> getActiveFundraisings(
            @RequestParam(required = false) boolean isActive,
            @RequestParam(required = false) String category,
            Pageable pageable
    ) {
        return fundraisingService.getFundraisings(isActive, category, pageable);
    }

    @GetMapping("/{fundraisingId}")
    public FundraisingDto getFundraisingById(@PathVariable long fundraisingId) {
        return fundraisingService.getFundraisingById(fundraisingId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public FundraisingDto createFundraising(@RequestBody CreateFundraisingRequestDto requestDto,
                                            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return fundraisingService.createFundraising(requestDto, user);
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{fundraisingId}")
    public FundraisingDto closeFundraisingById(@PathVariable long fundraisingId,
                                               Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return fundraisingService.closeFundraisingById(fundraisingId, user);
    }
}
