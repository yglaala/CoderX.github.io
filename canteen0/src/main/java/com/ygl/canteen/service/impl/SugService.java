package com.ygl.canteen.service.impl;

import com.ygl.canteen.mapper.SugMapper;
import com.ygl.canteen.model.Suggestion;
import com.ygl.canteen.service.ISugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SugService implements ISugService {

    @Autowired
    private SugMapper sugMapper;

    @Override
    public List<Suggestion> getSugs() {
        return sugMapper.getSugs();
    }

    @Override
    public int add(Suggestion suggestion) {
        return sugMapper.add(suggestion);
    }
}
