package ua.volcaniccupcake.onlinestore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReleaseNote {
    private String version;
    private String description;
}
