package teamproject.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Fundraising management", description = "Endpoints for managing fundraisings")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fundraisings")
public class FundraisingController {
    private final FundraisingService fundraisingService;

    @GetMapping
    @Operation(summary = "Get all active fundraisings in pages",
            description = "Get all active fundraisings in pages")
    public List<FundraisingDto> getActiveFundraisings(
            @RequestParam(required = false) boolean isActive,
            @RequestParam(required = false) String category,
            Pageable pageable
    ) {
        return fundraisingService.getFundraisings(isActive, category, pageable);
    }

    @GetMapping("/{fundraisingId}")
    @Operation(summary = "Get a fundraising by id",
            description = "Get a fundraising by id")
    public FundraisingDto getFundraisingById(@PathVariable long fundraisingId) {
        return fundraisingService.getFundraisingById(fundraisingId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "Create a new fundraising",
            description = "Create a new fundraising",
            security = @SecurityRequirement(name = "bearerAuth"))
    public FundraisingDto createFundraising(@RequestBody CreateFundraisingRequestDto requestDto,
                                            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return fundraisingService.createFundraising(requestDto, user);
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{fundraisingId}")
    @Operation(summary = "Close a fundraising by id",
            description = "Close a fundraising by id",
            security = @SecurityRequirement(name = "bearerAuth"))
    public FundraisingDto closeFundraisingById(@PathVariable long fundraisingId,
                                               Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return fundraisingService.closeFundraisingById(fundraisingId, user);
    }
}
