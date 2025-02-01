package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/journal")
public class journeyEntryController {

    private Map< Long ,JournalEntry> journalentries = new HashMap<>();

    @GetMapping("/all")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalentries.values());
    }

    @PostMapping("/add")
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalentries.put(myEntry.getId(), myEntry);
        return true;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getJournalById(@PathVariable Long myId){
        return journalentries.get(myId);
    }

}
