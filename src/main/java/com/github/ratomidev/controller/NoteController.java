package com.github.ratomidev.controller;

import com.github.ratomidev.entity.Note;
import com.github.ratomidev.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}/lesson/{lessonId}")
    public List<Note> getNotesByCourseAndLesson(
            @PathVariable Long courseId,
            @PathVariable Long lessonId) {
        return noteService.getNotesByCourseAndLesson(courseId, lessonId);
    }

    @PostMapping("/course/{courseId}/lesson/{lessonId}")
    public ResponseEntity<Note> createNote(
            @PathVariable Long courseId,
            @PathVariable Long lessonId,
            @RequestBody Note note) {
        return ResponseEntity.ok(noteService.createNote(courseId, lessonId, note));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long id,
            @RequestBody Note note) {
        return noteService.getNoteById(id)
                .map(existingNote -> {
                    note.setId(id);
                    note.setCourse(existingNote.getCourse());
                    note.setLesson(existingNote.getLesson());
                    return ResponseEntity.ok(noteService.updateNote(note));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(note -> {
                    noteService.deleteNote(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
