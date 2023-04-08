package ua.volcaniccupcake.onlinestore.exception;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import ua.volcaniccupcake.onlinestore.exception.NoProductException;

public class NoProductFailureAnalyzer extends AbstractFailureAnalyzer<NoProductException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, NoProductException cause) {
        return new FailureAnalysis("No product detected in the repository", "Add product to the repository", cause);
    }
}
