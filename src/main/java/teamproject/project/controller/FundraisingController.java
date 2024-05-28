package teamproject.project.controller;

import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.annotation.*;
import teamproject.project.dto.fundraising.*;
import teamproject.project.model.*;
import teamproject.project.service.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fundraisings")
public class FundraisingController {
    private final FundraisingService fundraisingService;

    @GetMapping
    public List<FundraisingDto> getActiveFundraisings(@RequestParam(required = false) boolean isActive,
                                                      @RequestParam(required = false) String category,
                                                      Pageable pageable) {
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
