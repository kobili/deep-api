package com.deep.deep.controller;

import com.deep.deep.dto.AddDeepBulkRequestDto;
import com.deep.deep.dto.AddDeepRequestDto;
import com.deep.deep.dto.DeepResponseDto;
import com.deep.deep.entity.Deep;
import com.deep.deep.service.DeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deep")
public class DeepController {

    @Autowired
    private DeepService deepService;

    @GetMapping
    public DeepResponseDto getRandomDeep() {
        return deepService.getRandomDeep();
    }

    @PostMapping
    public DeepResponseDto addNewDeep(@RequestBody AddDeepRequestDto request) {
        return deepService.addDeep(request);
    }

    @PostMapping("/bulk")
    public List<DeepResponseDto> addDeeps(@RequestBody AddDeepBulkRequestDto request) {
        return deepService.addDeeps(request);
    }
}
