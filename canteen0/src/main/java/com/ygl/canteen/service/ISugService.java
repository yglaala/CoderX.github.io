package com.ygl.canteen.service;

import com.ygl.canteen.model.Suggestion;

import java.util.List;

public interface ISugService {

    List<Suggestion> getSugs();
    int add(Suggestion suggestion);
}
