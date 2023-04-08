package ua.volcaniccupcake.onlinestore.util;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.model.ReleaseNote;

import java.util.Collection;
import java.util.Optional;

@Component
@Endpoint(id = "releaseNotes")
@AllArgsConstructor
public class ReleaseNotesEndpoint {
    private final Collection<ReleaseNote> releaseNotes;

    @ReadOperation
    public Iterable<ReleaseNote> releaseNotes() {
        return releaseNotes;
    }

    public Object selectReleaseNote(@Selector String version) {
        Optional<ReleaseNote> releaseNoteOptional = releaseNotes
                .stream()
                .filter(releaseNote -> version.equals(releaseNote.getVersion()))
                .findFirst();
        if (releaseNoteOptional.isPresent()) {
            return releaseNoteOptional.get();
        }
        return String.format("No such release version exists: %s", version);
    }
    @DeleteOperation
    public void removeReleaseNote(@Selector String version) {
        Optional<ReleaseNote> releaseNoteOptional = releaseNotes
                .stream()
                .filter(releaseNote -> version.equals(releaseNote.getVersion()))
                .findFirst();
        if (releaseNoteOptional.isPresent()) {
            releaseNotes.remove(releaseNoteOptional.get());
        }
    }

}
