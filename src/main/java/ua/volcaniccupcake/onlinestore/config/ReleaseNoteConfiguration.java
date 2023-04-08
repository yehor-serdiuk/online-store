package ua.volcaniccupcake.onlinestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.volcaniccupcake.onlinestore.model.ReleaseNote;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
public class ReleaseNoteConfiguration {

    @Bean
    public Collection<ReleaseNote> loadReleaseNotes() {
        ReleaseNote note1 = ReleaseNote.builder()
                .version("1.0.0")
                .description("first version of the application")
                .build();
        ReleaseNote note2 = ReleaseNote.builder()
                .version("1.0.1")
                .description("minor bug fixes")
                .build();
        return new LinkedHashSet<>(Set.of(note1, note2));
    }

}
