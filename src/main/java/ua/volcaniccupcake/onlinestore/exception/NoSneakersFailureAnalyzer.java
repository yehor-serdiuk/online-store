package ua.volcaniccupcake.onlinestore.exception;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import ua.volcaniccupcake.onlinestore.exception.NoSneakersException;

public class NoSneakersFailureAnalyzer extends AbstractFailureAnalyzer<NoSneakersException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, NoSneakersException cause) {
        return new FailureAnalysis("No sneakers detected in the repository", "Add sneakers to the repository", cause);
    }
}
