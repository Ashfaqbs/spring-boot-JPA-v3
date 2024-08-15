package com.ashfaq.example.criteria;


import java.util.List;

import lombok.Data;

@Data
public class StudentFilterRequest {
    private List<Long> ids;
    private List<String> names;
    private List<String> departments;
}