package com.example.eventmicroservice.controller;

import com.example.eventmicroservice.entity.commentevent;
import com.example.eventmicroservice.service.commenteventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarioPUJ")
public class commentController {

    @Autowired
    commenteventService commenteventService;

    /**
     * This method handles a GET request to retrieve all comments associated with a specific event.
     * It takes the idEvent path variable, retrieves comments using the commenteventService, and
     * returns a response entity containing the list of comments if they exist. If no comments are found,
     * it returns a BAD REQUEST response.
     * @param idEvent
     * @return
     */
    @GetMapping("/comentarios/evento/{idEvent}")
    public ResponseEntity<List<commentevent>> getAllComentsPerEvent(@PathVariable Integer idEvent){
        List<commentevent> auxComment=commenteventService.getAllCommentsPerEvent(idEvent);
        if(auxComment.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(auxComment,HttpStatus.OK);
    }

    /**
     * This method handles a POST request to add a new comment related to an event. It takes a
     * commentevent object in the request body, calls the newComment method from commenteventService,
     * and returns a response entity indicating whether the comment was successfully added. If a comment
     * from the same user for the same event already exists, it returns a BAD REQUEST response.
     * @param commenteventNew
     * @return
     */
    @PostMapping("/comentario/evento")
    public ResponseEntity<String> commentNew(@RequestBody commentevent commenteventNew){
        boolean operationSuccess=commenteventService.newComment(commenteventNew);
        if(!operationSuccess){
            return  new ResponseEntity<>("Ya has ingresado un comentario. Modificalo en su lugar", HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Comentario agregado", HttpStatus.OK);
    }

    /**
     * This method handles a PUT request to update an existing comment. It takes a commentevent object
     * in the request body, calls the updateComment method from commenteventService, and returns a
     * response entity indicating that the comment was successfully updated.
     * @param commenteventUpdate
     * @return
     */
    @PutMapping("/comentario/evento")
    public ResponseEntity<String> commentUpdate(@RequestBody commentevent commenteventUpdate){
        commenteventService.updateComment(commenteventUpdate);
        return new ResponseEntity<>("Comentario modificado correctamente", HttpStatus.OK);
    }

    /**
     * This method handles a DELETE request to delete a comment associated with a specific event and
     * user. It takes idEvent and idUser as path variables, calls the deleteComment method from
     * commenteventService, and returns a response entity indicating that the comment was successfully
     * deleted.
     * @param idEvent
     * @param idUser
     * @return
     */
    @DeleteMapping("/comentario/evento/{idEvent}/{idUser}")
    public ResponseEntity<String> commentDelete(@PathVariable Integer idEvent, @PathVariable Integer idUser){
        commenteventService.deleteComment(idEvent,idUser);
        return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
    }
}
