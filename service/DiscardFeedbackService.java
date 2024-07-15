package com.service;

import com.DAO.DiscardFeedbackDAO;
import com.DTO.DiscardFeedbackDTO;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiscardFeedbackService {
    private final DiscardFeedbackDAO discardFeedbackDAO;

    public DiscardFeedbackService(){
        discardFeedbackDAO = new DiscardFeedbackDAO();
    }

    public void submitDiscardFeedback(String questions, String menuId) throws SQLException {
        DiscardFeedbackDTO discardFeedbackDTO = new DiscardFeedbackDTO();
        discardFeedbackDTO.setQuestion(questions);
        discardFeedbackDTO.setMenuId(Integer.parseInt(menuId));
        this.discardFeedbackDAO.insertDiscardFeedback(discardFeedbackDTO);
    }

    public Map<Integer, String> getTodayFeedbackDetails() throws SQLException{
        List<DiscardFeedbackDTO> discardFeedbackDTODetails = discardFeedbackDAO.getTodayDiscardFeedbackDetails();
        return preparemenuIdWithQuestionMap(discardFeedbackDTODetails);
    }

    private Map<Integer, String> preparemenuIdWithQuestionMap(List<DiscardFeedbackDTO> discardFeedbackDTODetails){
        Map<Integer, String> menuIdWithQuestionMap = new LinkedHashMap<Integer, String>();
        for(DiscardFeedbackDTO record : discardFeedbackDTODetails){
           menuIdWithQuestionMap.put(record.getMenuId(),record.getQuestion());
        }
        return menuIdWithQuestionMap;
    }
}
