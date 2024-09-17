package com.ashfaq.example.criteria.example;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gym-members")
public class GymMemberController {

    private final GymMemberService gymMemberService;

    public GymMemberController(GymMemberService gymMemberService) {
        this.gymMemberService = gymMemberService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<GymMember>> searchGymMembers(@RequestBody GymMemberSearchRequest request) {
        List<GymMember> gymMembers = gymMemberService.findGymMembersByCriteria(request);
        return ResponseEntity.ok(gymMembers);
    }
    
}
