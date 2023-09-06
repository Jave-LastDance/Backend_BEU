package com.example.eventmicroservice.service;

import com.example.eventmicroservice.entity.commentevent;
import com.example.eventmicroservice.repository.commenteventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commenteventService {

    @Autowired
    commenteventRepository commenteventRepository;

    /**
     * This method retrieves all comments associated with a specific eventDB. It takes
     * the eventDB's ID as a parameter and returns a list of comments related to that eventDB.
     * @param idEvent
     * @return
     */
    public List<commentevent> getAllCommentsPerEvent(Integer idEvent){
        return commenteventRepository.findAllByEventId(idEvent);
    }

    /**
     * This method updates an existing comment. It takes a commentevent object with the updated
     * comment data and saves it to the repository.
     * @param commenteventUpdate
     */
    public void updateComment(commentevent commenteventUpdate){
        commenteventRepository.save(commenteventUpdate);
    }

    /**
     * This method adds a new comment if no previous comment exists from the same user for the same eventDB.
     * It takes a commentevent object representing the new comment. If the comment is unique (not already
     * posted by the user for the same eventDB), it will be saved to the repository.
     * @param commenteventNew
     * @return
     */
    public boolean newComment(commentevent commenteventNew){
        boolean operationSuccess=true;
        for(commentevent aux:commenteventRepository.findAll()){
            if(commenteventNew.getIdUser()==aux.getIdUser() && commenteventNew.getEventId()==aux.getEventId()){
                operationSuccess=false;
            }
        }
        if(operationSuccess){
            commenteventRepository.save(commenteventNew);
        }
        return operationSuccess;
    }

    /**
     * This method deletes a comment associated with a specific eventDB and user. It takes the IDs of
     * the eventDB and the user whose comment needs to be deleted, and removes the corresponding comment
     * from the repository.
     * @param idEvent
     * @param idUser
     */
    public void deleteComment(Integer idEvent, Integer idUser){
        commenteventRepository.deleteByIdUserAndEventId(idUser,idEvent);
    }

    /**
     * This method retrieves all comments
     * @return
     */
    public List<commentevent> getAll(){
        return commenteventRepository.findAll();
    }
}
