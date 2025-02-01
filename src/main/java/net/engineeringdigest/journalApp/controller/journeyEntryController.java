package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/journal")
public class journeyEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/add")
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        journalEntryService.saveEntry(myEntry);
        System.out.println("Create Request received");
        return myEntry;
    }

    @GetMapping("/all")
    public List<JournalEntry> getAllEntries(){
        return journalEntryService.getAllEntries();
    }

    @GetMapping("/entry/{id}")
    public Optional<JournalEntry> getEntryById(@PathVariable String id){
        return journalEntryService.getEntryById(getObjectId(id));
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry(@PathVariable String id){
        journalEntryService.deleteEntry(getObjectId(id));
        return true;

    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable String id, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getEntryById(getObjectId(id)).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null  ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null  ? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.updateEntry(oldEntry);
        return oldEntry;
    }

    public static ObjectId getObjectId(String id){
        return new ObjectId(id);
    }
}
